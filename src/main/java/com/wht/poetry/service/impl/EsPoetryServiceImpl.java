package com.wht.poetry.service.impl;

import com.wht.poetry.dao.EsPoetryDao;
import com.wht.poetry.domain.EsPoetry;
import com.wht.poetry.repository.EsPoetryRepository;
import com.wht.poetry.service.EsPoetryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *  诗词搜索管理SService实现类
 * @author wht
 * @since 2020-04-29 19:31
 */
@Service
public class EsPoetryServiceImpl implements EsPoetryService {

    @Resource
    private EsPoetryDao esPoetryDao;
    @Resource
    private EsPoetryRepository esPoetryRepository;
    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;
    private static final String PERSON_INDEX_NAME = "culture";
    private static final String PERSON_INDEX_TYPE = "poetry";
    @Override
    public int importAll() {
        List<EsPoetry> esPoetryList = esPoetryDao.getAllEsPoetryList(null);
        int counter = 0;
        try {
            if (!elasticsearchTemplate.indexExists(PERSON_INDEX_NAME)) {
                elasticsearchTemplate.createIndex(PERSON_INDEX_TYPE);
            }
            List<IndexQuery> queries = new ArrayList<>();
            for (EsPoetry esPoetry : esPoetryList) {
                IndexQuery indexQuery = new IndexQuery();
                indexQuery.setId(esPoetry.getId() + "");
                indexQuery.setObject(esPoetry);
                indexQuery.setIndexName(PERSON_INDEX_NAME);
                indexQuery.setType(PERSON_INDEX_TYPE);

                queries.add(indexQuery);
                if (counter % 500 == 0) {
                    elasticsearchTemplate.bulkIndex(queries);
                    queries.clear();
                    System.out.println("bulkIndex counter : " + counter);
                }
                counter++;
            }
            if (queries.size() > 0) {
                elasticsearchTemplate.bulkIndex(queries);
            }
            System.out.println("bulkIndex completed.");
        } catch (Exception e) {
            System.out.println("IndexerService.bulkIndex e;" + e.getMessage());
            throw e;
        }
        return counter;
    }

    @Override
    public void delete(Long id) {
        esPoetryRepository.deleteById(id);
    }

    @Override
    public EsPoetry create(Long id) {
        EsPoetry result = null;
        List<EsPoetry> esPoetryList = esPoetryDao.getAllEsPoetryList(id);
        if (esPoetryList.size() > 0) {
            EsPoetry esPoetry = esPoetryList.get(0);
            result = esPoetryRepository.save(esPoetry);
        }
        return result;
    }

    @Override
    public void delete(List<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<EsPoetry> esProductList = new ArrayList<>();
            for (Long id : ids) {
                EsPoetry esProduct = new EsPoetry();
                esProduct.setId(id);
                esProductList.add(esProduct);
            }
            esPoetryRepository.deleteAll(esProductList);
        }
    }

    @Override
    public Page<EsPoetry> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return esPoetryRepository.findByTitleOrDynastyOrAuthorOrContent(keyword, keyword, keyword, keyword, pageable);
    }

    @Override
    public Page<EsPoetry> search(String title, String dynasty, String author, String content, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return esPoetryRepository.findByTitleOrDynastyOrAuthorOrContent(title, dynasty, author, content, pageable);
    }

}

package com.wht.poetry.service.impl;

import com.wht.poetry.dao.EsPoetryDao;
import com.wht.poetry.domain.EsPoetry;
import com.wht.poetry.repository.EsPoetryRepository;
import com.wht.poetry.service.EsPoetryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Override
    public int importAll() {
        List<EsPoetry> esPoetryList = esPoetryDao.getAllEsPoetryList(null);
        Iterable<EsPoetry> esPoetryIterable = esPoetryRepository.saveAll(esPoetryList);
        Iterator<EsPoetry> iterator = esPoetryIterable.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
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

}

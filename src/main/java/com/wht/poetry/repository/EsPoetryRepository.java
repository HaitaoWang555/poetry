package com.wht.poetry.repository;

import com.wht.poetry.domain.EsPoetry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 诗词ES操作类
 * @author wht
 * @since 2020-04-29 19:14
 */
public interface EsPoetryRepository extends ElasticsearchRepository<EsPoetry, Long> {
    /**
     * 搜索查询
     * @param title 诗词名称
     * @param dynasty 朝代
     * @param author 作者
     * @param content 内容
     * @param page 分页
     * @return Page<EsPoetry>
     */
    Page<EsPoetry> findByTitleOrDynastyOrAuthorOrContent(String title, String dynasty, String author, String content, Pageable page);
}

package com.wht.poetry.service;

import com.wht.poetry.domain.EsPoetry;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 诗词搜索管理Service
 *
 * @author wht
 * @since 2020-04-29 19:27
 */
public interface EsPoetryService {
    /**
     * 从数据库中导入所有商品到ES
     */
    int importAll();

    /**
     * 根据id删除商品
     */
    void delete(Long id);

    /**
     * 根据id创建商品
     */
    EsPoetry create(Long id);

    /**
     * 批量删除商品
     */
    void delete(List<Long> ids);

    /**
     * 根据关键字搜索名称或者副标题
     */
    Page<EsPoetry> search(String keyword, Integer pageNum, Integer pageSize);


}

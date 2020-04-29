package com.wht.poetry.dao;

import com.wht.poetry.domain.EsPoetry;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 搜索系统中的诗词管理自定义Dao
 * @author wht
 * @since 2020-04-29 19:11
 */
public interface EsPoetryDao {
    List<EsPoetry> getAllEsPoetryList(@Param("id") Long id);
}

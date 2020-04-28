package com.wht.poetry.mapper;

import com.wht.poetry.model.Poetry;
import com.wht.poetry.model.PoetryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PoetryMapper {
    long countByExample(PoetryExample example);

    int deleteByExample(PoetryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Poetry record);

    int insertSelective(Poetry record);

    List<Poetry> selectByExampleWithBLOBs(PoetryExample example);

    List<Poetry> selectByExample(PoetryExample example);

    Poetry selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Poetry record, @Param("example") PoetryExample example);

    int updateByExampleWithBLOBs(@Param("record") Poetry record, @Param("example") PoetryExample example);

    int updateByExample(@Param("record") Poetry record, @Param("example") PoetryExample example);

    int updateByPrimaryKeySelective(Poetry record);

    int updateByPrimaryKeyWithBLOBs(Poetry record);

    int updateByPrimaryKey(Poetry record);
}
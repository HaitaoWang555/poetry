package com.wht.poetry.service.impl;

import com.github.pagehelper.PageHelper;
import com.wht.poetry.dto.PmsPoetryDto;
import com.wht.poetry.mapper.PoetryMapper;
import com.wht.poetry.model.Poetry;
import com.wht.poetry.model.PoetryExample;
import com.wht.poetry.service.PoetryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PoetryServiceImpl implements PoetryService {
    @Resource
    private PoetryMapper poetryMapper;

    @Override
    public List<Poetry> listAllPoetry() {
        return poetryMapper.selectByExample(new PoetryExample());
    }

    @Override
    public int createPoetry(PmsPoetryDto pmsPoetryDto) {
        return 0;
    }

    @Override
    public int updatePoetry(int id, PmsPoetryDto pmsPoetryDto) {
        return 0;
    }

    @Override
    public int deletePoetry(int id) {
        return 0;
    }

    @Override
    public List<Poetry> listPoetry(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return poetryMapper.selectByExample(new PoetryExample());
    }

    @Override
    public List<Poetry> listPoetryWithBLOBs(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return poetryMapper.selectByExampleWithBLOBs(new PoetryExample());
    }

    @Override
    public Poetry getPoetry(int id) {
        return poetryMapper.selectByPrimaryKey(id);
    }
}

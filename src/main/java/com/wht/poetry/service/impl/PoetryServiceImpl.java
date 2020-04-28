package com.wht.poetry.service.impl;

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
}

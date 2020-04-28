package com.wht.poetry.service;

import com.wht.poetry.dto.PmsPoetryDto;
import com.wht.poetry.model.Poetry;

import java.util.List;

public interface PoetryService {
    List<Poetry> listAllPoetry();

    int createPoetry(PmsPoetryDto pmsPoetryDto);

    int updatePoetry(Long id, PmsPoetryDto pmsPoetryDto);

    int deletePoetry(Long id);

    List<Poetry> listPoetry(int pageNum, int pageSize);

    Poetry getPoetry(Long id);
}

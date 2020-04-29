package com.wht.poetry.controller;

import com.wht.poetry.api.CommonPage;
import com.wht.poetry.api.CommonResult;
import com.wht.poetry.dto.PmsPoetryDto;
import com.wht.poetry.model.Poetry;
import com.wht.poetry.service.PoetryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 诗词管理controller
 */
@Api(tags = "PoetryController", description = "诗词管理接口")
@RequestMapping(value = "/api/poetry")
@Controller
public class PoetryController {

    @Resource
    private PoetryService poetryService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PoetryController.class);

    @ApiOperation(value = "获取全部诗词列表")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Poetry>> getPoetryListALL() {
        return CommonResult.success(poetryService.listAllPoetry());
    }

    @ApiOperation(value = "获取诗词分页列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getPoetryList(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize
    ) {
        List<Poetry> poetryList = poetryService.listPoetryWithBLOBs(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(poetryList));
    }

    @ApiOperation(value = "根据ID获取诗词")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getPoetry( @PathVariable("id") int id) {
        Poetry poetry = poetryService.getPoetry(id);
        return CommonResult.success(poetry);
    }

    @ApiOperation(value = "新增诗词")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createPoetry (
            @Validated @RequestBody PmsPoetryDto pmsPoetryDto,
            BindingResult result
    ) {
        if(result.hasErrors()){
            return CommonResult.validateFailed(result.getFieldError().getDefaultMessage());
        }
        CommonResult commonResult;
        int count = poetryService.createPoetry(pmsPoetryDto);
        if (count == 1) {
            commonResult = CommonResult.success(null);
            LOGGER.info("createPoetry success:{}", pmsPoetryDto);
        } else {
            commonResult = CommonResult.failed("添加失败");
            LOGGER.error("createPoetry failed:{}", pmsPoetryDto);
        }
        return commonResult;
    }

    @ApiOperation(value = "根据ID更新诗词")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePoetry(
            @PathVariable("id") int id,
            @RequestBody PmsPoetryDto pmsPoetryDto,
            BindingResult result
    ) {
        if(result.hasErrors()){
            return CommonResult.validateFailed(result.getFieldError().getDefaultMessage());
        }
        CommonResult commonResult;
        int count = poetryService.updatePoetry(id, pmsPoetryDto);
        if (count == 1) {
            commonResult = CommonResult.success(null);
            LOGGER.info("updatePoetry success:{}", pmsPoetryDto);
        } else {
            commonResult = CommonResult.failed("修改失败");
            LOGGER.error("updatePoetry failed:{}", pmsPoetryDto);
        }
        return commonResult;
    }

    @ApiOperation(value = "根据ID删除诗词")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResult delPoetry( @PathVariable("id") int id) {
        int count = poetryService.deletePoetry(id);
        if (count == 1) {
            LOGGER.info("deletePoetry success :id={}", id);
            return CommonResult.success(null);
        } else {
            LOGGER.error("deletePoetry failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }
}

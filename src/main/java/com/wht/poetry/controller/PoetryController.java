package com.wht.poetry.controller;

import com.wht.poetry.api.CommonPage;
import com.wht.poetry.api.CommonResult;
import com.wht.poetry.model.Poetry;
import com.wht.poetry.service.PoetryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 品牌管理示例controller
 */
@Api(tags = "PoetryController", description = "诗词管理接口")
@Controller
public class PoetryController {

    @Resource
    private PoetryService poetryService;

    @ApiOperation(value = "获取全部诗词列表")
    @RequestMapping(value = "/poetry/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Poetry>> getPoetryListALL() {
        return CommonResult.success(poetryService.listAllPoetry());
    }

    @ApiOperation(value = "获取诗词分页列表")
    @RequestMapping(value = "/poetry/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getPoetryList(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize
    ) {
        List<Poetry> poetryList = poetryService.listPoetryWithBLOBs(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(poetryList));
    }

    @ApiOperation(value = "根据ID获取诗词")
    @RequestMapping(value = "/poetry/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getPoetry(
            @PathVariable("id") int id
    ) {
        Poetry poetry = poetryService.getPoetry(id);
        return CommonResult.success(poetry);
    }
}

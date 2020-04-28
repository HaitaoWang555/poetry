package com.wht.poetry.controller;

import com.wht.poetry.api.CommonResult;
import com.wht.poetry.model.Poetry;
import com.wht.poetry.service.PoetryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ApiOperation(value = "获取全部品牌列表")
    @RequestMapping(value = "/poetry/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Poetry>> getPoetryList() {
        return CommonResult.success(poetryService.listAllPoetry());
    }
}

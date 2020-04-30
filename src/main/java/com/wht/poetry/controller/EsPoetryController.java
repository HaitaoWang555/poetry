package com.wht.poetry.controller;

import com.wht.poetry.api.CommonPage;
import com.wht.poetry.api.CommonResult;
import com.wht.poetry.domain.EsPoetry;
import com.wht.poetry.service.EsPoetryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 诗词搜索管理
 * @author wht
 * @since 2020-04-29 20:14
 */
@Api(tags = "EsPoetryController", description = "诗词搜索管理接口")
@RequestMapping(value = "/api/esPoetry")
@Controller
public class EsPoetryController {
    @Resource
    private EsPoetryService esPoetryService;

    @ApiOperation(value = "导入所有数据库中商品到ES")
    @RequestMapping(value = "/importAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> importAllList() {
        int count = esPoetryService.importAll();
        return CommonResult.success(count);
    }

    @ApiOperation(value = "根据id删除商品")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> delete(@PathVariable Long id) {
        esPoetryService.delete(id);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "根据id批量删除商品")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> delete(@RequestParam("ids") List<Long> ids) {
        esPoetryService.delete(ids);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "根据id创建商品")
    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<EsPoetry> create(@PathVariable Long id) {
        EsPoetry esPoetry = esPoetryService.create(id);
        if (esPoetry != null) {
            return CommonResult.success(esPoetry);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "简单搜索")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<EsPoetry>> search(@RequestParam(required = false) String keyword,
                                                     @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                     @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        Page<EsPoetry> esProductPage = esPoetryService.search(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(esProductPage));
    }
}

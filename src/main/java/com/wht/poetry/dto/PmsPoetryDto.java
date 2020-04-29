package com.wht.poetry.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 诗词传递参数
 */
public class PmsPoetryDto {

    @ApiModelProperty(value = "诗词名称", required = true)
    @NotNull(message = "诗词名称不能为空")
    private String title;

    @ApiModelProperty(value = "朝代", required = true)
    @NotNull(message = "朝代不能为空")
    private String dynasty;

    @ApiModelProperty(value = "作者", required = true)
    @NotNull(message = "作者不能为空")
    private String author;

    @ApiModelProperty(value = "内容", required = true)
    @NotNull(message = "内容不能为空")
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

package com.sr.SRArticle.controller;


import com.sr.SRArticle.entity.Category;
import com.sr.SRArticle.service.CategoryService;
import com.sr.common.utils.RS;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author caiwenlong
 * @since 2020-12-23
 */
@RestController
@RequestMapping("/siri-article/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("获取全部分类")
    @GetMapping("/getAllCategories/{idAuthor}")
    public RS getAllArticles(
            @ApiParam(name = "idAuthor", value = "作者id")
            @PathVariable String idAuthor
    ) {
        List<Category> allCategories = categoryService.getAllCategories(idAuthor);
        return RS.success().data("categories", allCategories);
    }
}


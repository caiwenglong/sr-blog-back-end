package com.sr.SRArticle.controller;


import com.sr.SRArticle.entity.Articles;
import com.sr.SRArticle.entity.Category;
import com.sr.SRArticle.service.CategoryService;
import com.sr.common.utils.RS;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        List<Category> allCategories = categoryService.getAllArticleCategories(idAuthor);
        return RS.success().data("categories", allCategories);
    }

    @ApiOperation("添加分类")
    @PostMapping("/addArticleCategory")
    public RS addArticleCategory(
            @ApiParam(name = "category", value = "分类信息")
            @RequestBody Category category
    ) {
        Integer integer = categoryService.addArticleCategory(category);
        return RS.success().data("insertNum", integer);
    }

    @ApiOperation("修改分类")
    @PostMapping("/modifyArticleCategory")
    public RS modifyArticleCategory(
            @ApiParam(name = "category", value = "分类信息")
            @RequestBody Category category
    ) {
        Integer integer = categoryService.modifyArticleCategory(category);
        return RS.success().data("modifiedNum", integer);
    }

    @ApiOperation("删除分类")
    @DeleteMapping("/deleteArticleCategory/{idCategory}")
    public RS deleteArticleCategoryById(
            @ApiParam(name = "idCategory", value = "分类信息",  required = true)
            @PathVariable("idCategory") String idCategory
    ) {
        Integer integer = categoryService.deleteArticleCategory(idCategory);
        return RS.success().data("deleteNum", integer);
    }

    @ApiOperation("批量删除分类")
    @DeleteMapping("/batchDeleteArticleCategory")
    public RS batchDeleteArticleCategory(
            @ApiParam(name = "idCategoryList", value = "分类ID列表",  required = true)
            @RequestBody ArrayList<String> idCategoryList
    ) {
        Integer integer = categoryService.batchDeleteArticleCategory(idCategoryList);
        return RS.success().data("deleteNum", integer);
    }
}


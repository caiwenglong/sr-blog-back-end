package com.sr.SRArticle.controller;


import com.sr.SRArticle.entity.Category;
import com.sr.SRArticle.service.CategoryService;
import com.sr.common.utils.RS;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @ApiOperation("获取分类的子分类")
    @GetMapping("/getCategoryChildren/{idCategory}")
    public RS getCategoryChildren(
            @ApiParam(name = "idCategory", value = "分类ID")
            @PathVariable String idCategory
    ) {
        List<Category> allCategories = categoryService.getCategoryChildren(idCategory);
        return RS.success().data("categoryChildren", allCategories);
    }

    @ApiOperation("添加分类")
    @PostMapping("/addArticleCategory")
    public RS addArticleCategory(
            @ApiParam(name = "category", value = "分类信息")
            @RequestBody Category category
    ) {
        Category categoryEntity = categoryService.addArticleCategory(category);
        return RS.success().data("category", categoryEntity);
    }

    @ApiOperation("修改分类")
    @PostMapping("/modifyArticleCategory")
    public RS modifyArticleCategory(
            @ApiParam(name = "category", value = "分类信息")
            @RequestBody Category category
    ) {
        Category categoryEntity = categoryService.modifyArticleCategory(category);
        return RS.success().data("category", categoryEntity);
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
            @ApiParam(name = "idCategoryMap", value = "包含分类ID列表和最顶层分类的ID",  required = true)
            @RequestBody Map<String, ArrayList<String>> idCategoryMap
    ) {
        ArrayList<String> categoryIdList = idCategoryMap.get("categoryIdList");
        ArrayList<String> categoryParentId = idCategoryMap.get("categoryParentId");
        Integer integer = categoryService.batchDeleteArticleCategory(categoryIdList, categoryParentId);
        return RS.success().data("deleteNum", integer);
    }
}


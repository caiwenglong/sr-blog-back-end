package com.sr.SRArticle.controller;


import com.sr.SRArticle.entity.Articles;
import com.sr.SRArticle.service.ArticlesService;
import com.sr.common.utils.RS;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author caiwenlong
 * @since 2020-11-17
 */
@RestController
@RequestMapping("/siri-article")
public class ArticlesController {

    @Autowired
    private ArticlesService articlesService;

    @ApiOperation("添加文章")
    @PostMapping("/add")
    public RS addArticle(
            @ApiParam(name = "article", value = "文章信息")
            @RequestBody Articles article) {
        Integer integer = articlesService.addArticle(article);
        return RS.success().data("insertNum", integer);
    }

    @ApiOperation("通过ID获取文章")
    @GetMapping("/getArticle/{id}")
    public RS getArticle(
            @ApiParam(name = "id", value = "文章ID")
            @PathVariable String id
    ) {
        Articles article = articlesService.getArticle(id);
        return RS.success().data("article", article);
    }

    @ApiOperation("获取全部文章")
    @GetMapping("/getAll/{idAuthor}/{category}/{pageNum}/{pageSize}")
    public RS getAllArticles(
            @ApiParam(name = "idAuthor", value = "作者id")
            @PathVariable String idAuthor,
            @PathVariable String category,
            @PathVariable Integer pageNum,
            @PathVariable Integer pageSize
    ) {
        HashMap<Object, Object> allArticles = articlesService.getAllArticles(idAuthor, category, pageNum, pageSize);
        return RS.success().data("result", allArticles);
    }

    @ApiOperation("通过ID删除文章")
    @DeleteMapping("/deleteArticleById/{idArticle}")
    public RS deleteArticleById(
            @ApiParam(name = "idArticle", value = "作者ID",  required = true)
            @PathVariable("idArticle") String idArticle
    ) {
        Integer integer = articlesService.deleteArticleById(idArticle);
        return RS.success().data("deleteNum", integer) ;
    }

    @ApiOperation("通过分类ID删除文章")
    @DeleteMapping("/deleteArticleByCategoryIdList")
    public RS deleteArticleByCategoryId(
            @ApiParam(name = "idCategoryList", value = "分类ID列表",  required = true)
            @RequestBody ArrayList<String> idCategoryList
    ) {
        Integer integer = articlesService.deleteArticleByCategoryId(idCategoryList);
        return RS.success().data("deleteNum", integer) ;
    }

}


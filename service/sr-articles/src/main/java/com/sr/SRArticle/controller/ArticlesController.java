package com.sr.SRArticle.controller;


import com.sr.SRArticle.entity.Articles;
import com.sr.SRArticle.service.ArticlesService;
import com.sr.common.utils.RS;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("获取全部文章")
    @GetMapping("/getAll/{idAuthor}/{pageNum}/{pageSize}")
    public RS getAllArticles(
            @ApiParam(name = "idAuthor", value = "作者id")
            @PathVariable String idAuthor,
            @PathVariable Integer pageNum,
            @PathVariable Integer pageSize
    ) {
        HashMap<Object, Object> allArticles = articlesService.getAllArticles(idAuthor, pageNum, pageSize);
        return RS.success().data("articleList", allArticles);
    }

}


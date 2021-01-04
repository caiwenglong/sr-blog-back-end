package com.sr.SRArticle.service;

import com.sr.SRArticle.entity.Articles;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sr.common.utils.RS;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caiwenlong
 * @since 2020-11-17
 */
public interface ArticlesService extends IService<Articles> {


    // 获取全部的文章
    HashMap<Object, Object> getAllArticles(String idAuthor, String idCategory, Integer pageNum, Integer pageSize);

    Articles getArticle(String artId);

    // 添加文章
    Integer addArticle(Articles article);

    // 通过文章ID删除文章
    Integer deleteArticleById(String idArticle);

    // 通过文章ID删除文章
    Integer deleteArticleByCategoryId(String idCategory);

    // 修改文章
    RS updateArticle();
}

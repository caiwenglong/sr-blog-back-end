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
    public HashMap<Object, Object> getAllArticles(String idAuthor, String idCategory, Integer pageNum, Integer pageSize);

    public Articles getArticle(String artId);

    // 添加文章
    public Integer addArticle(Articles article);

    // 删除文章
    public RS deleteArticle();

    // 修改文章
    public RS updateArticle();
}

package com.sr.SRArticle.service;

import com.sr.SRArticle.entity.Articles;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sr.common.utils.RS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caiwenlong
 * @since 2020-11-17
 */
public interface ArticlesService extends IService<Articles> {

    /**
     * 分页获取文章
     * @param idAuthor： 用户ID
     * @param idCategory：分类ID
     * @return 返回文章列表数据
     */
    List<Articles> getArticlesByCategory(String idAuthor, String idCategory);


    /**
     * 分页获取文章
     * @param idAuthor： 用户ID
     * @param idCategory：分类ID
     * @param pageNum：当前页
     * @param pageSize：一页几条数据
     * @return 返回文章列表数据
     */
    HashMap<Object, Object> getPagingArticles(String idAuthor, String idCategory, Integer pageNum, Integer pageSize);

    /**
     * 通过文章ID获取文章ID
     * @param artId：文章ID
     * @return 返回文章
     */
    Articles getArticle(String artId);

    /**
     * 添加文章
     * @param article 文章对象
     * @return 返回添加的条数
     */
    Integer addArticle(Articles article);

    /**
     * 修改文章
     * @param article 修改后的文章信息
     * @return 返回修改的条数
     */
    Integer modifyArticle(Articles article);

    /**
     * 通过文章ID删除文章
     * @param idArticle 文章ID
     * @return 返回删除的条数
     */
    Integer deleteArticleById(String idArticle);

    /**
     * 通过分类ID列表批量删除文章
     * @param idCategory：分类ID列表
     * @return 返回删除的条数
     */
    Integer deleteArticleByCategoryId(ArrayList<String> idCategory);
}

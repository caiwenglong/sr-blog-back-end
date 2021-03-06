package com.sr.SRArticle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sr.SRArticle.entity.Articles;
import com.sr.SRArticle.mapper.ArticlesMapper;
import com.sr.SRArticle.service.ArticlesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sr.common.utils.RS;
import com.sr.service.base.exception.CustomException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caiwenlong
 * @since 2020-11-17
 */
@Service
public class ArticlesServiceImpl extends ServiceImpl<ArticlesMapper, Articles> implements ArticlesService {

    public Boolean isEmpty(Articles article) {
        return StringUtils.isEmpty(article.getTitle())
                || StringUtils.isEmpty(article.getContent());
    }

    @Override
    public List<Articles> getArticlesByCategory(String idCategory) {
        QueryWrapper<Articles> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category", idCategory);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public HashMap<Object, Object> getPagingArticles(String idAuthor, String idCategory, Integer pageNum, Integer pageSize) {
        // 创建page对象
        Page<Articles> ArticlesPage = new Page<Articles>(pageNum, pageSize);
        // 构造查询条件
        QueryWrapper<Articles> ArticlesQueryWrapper = new QueryWrapper<>();
        if(idCategory.isEmpty()) {
            ArticlesQueryWrapper.eq("id_author", idAuthor);
        } else {
            ArticlesQueryWrapper.eq("id_author", idAuthor).and(i -> i.eq("category", idCategory));
        };

        // 分页查询
        IPage<Articles> ArticlesIPage = baseMapper.selectPage(ArticlesPage, ArticlesQueryWrapper);
        List<Articles> list = ArticlesIPage.getRecords();

        // 查询总条数
        Integer total = baseMapper.selectCount(ArticlesQueryWrapper);

        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("total", total);
        hashMap.put("articleList", new ArrayList<>(list));
        return hashMap;
    }

    @Override
    public Articles getArticle(String id) {
        QueryWrapper<Articles> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public Integer addArticle(Articles article) {

        // 非空判断
        if(isEmpty(article)) {
            throw new CustomException("SR20002", "添加失败！文章标题或内容不能为空！");
        }
        return baseMapper.insert(article);
    }

    @Override
    public Integer modifyArticle(Articles article) {
        if(isEmpty(article)) {
            throw new CustomException("SR20002", "添加失败！文章标题或内容不能为空！");
        }
        UpdateWrapper<Articles> articlesUpdateWrapper = new UpdateWrapper<>();
        String articleId = article.getId();
        articlesUpdateWrapper.eq("id", articleId);
        Articles articleEntity = getArticle(articleId);
        articleEntity.setTitle(article.getTitle());
        articleEntity.setContent(article.getContent());
        articleEntity.setCategory(article.getCategory());
        articleEntity.setTags(article.getTags());
        return baseMapper.update(articleEntity, articlesUpdateWrapper);
    }

    @Override
    public Integer deleteArticleById(String idArticle) {
        return baseMapper.deleteById(idArticle);
    }

    @Override
    public Integer deleteArticleByCategoryId(ArrayList<String> idCategoryList) {
        int totalNum = 0;
        for (String idCategory : idCategoryList) {
            QueryWrapper<Articles> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("category", idCategory);
            int integer = baseMapper.delete(queryWrapper);
            totalNum = totalNum + integer;
        }
        return totalNum;
    }

    @Override
    public void moveArticleToNewCategory(String categoryId, String categoryParentId) {
        UpdateWrapper<Articles> articlesUpdateWrapper = new UpdateWrapper<>();
        articlesUpdateWrapper.eq("category", categoryParentId);
        List<Articles> articleList = getArticlesByCategory(categoryParentId);
        for(Articles article: articleList) {
            article.setCategory(categoryId);
            baseMapper.update(article, articlesUpdateWrapper);
        }
    }
}

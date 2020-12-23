package com.sr.SRArticle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    @Override
    public HashMap<Object, Object> getAllArticles(String idAuthor, Integer pageNum, Integer pageSize) {
        // 创建page对象
        Page<Articles> ArticlesPage = new Page<Articles>(pageNum, pageSize);
        // 构造查询条件
        QueryWrapper<Articles> ArticlesQueryWrapper = new QueryWrapper<>();
        ArticlesQueryWrapper.eq("id_author", idAuthor);
        // 分页查询
        IPage<Articles> ArticlesIPage = baseMapper.selectPage(ArticlesPage, ArticlesQueryWrapper);
        List<Articles> list = ArticlesIPage.getRecords();

        // 查询总条数
        Integer total = baseMapper.selectCount(ArticlesQueryWrapper);
        System.out.println(total);

        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("total", total);
        hashMap.put("ArticlesList", new ArrayList<>(list));
        return hashMap;
    }

    @Override
    public Integer addArticle(Articles article) {

        // 非空判断
        if(StringUtils.isEmpty(article.getTitle())
                || StringUtils.isEmpty(article.getContent())) {
            throw new CustomException("SR20002", "添加失败！文章标题或内容不能为空！");
        }
        return baseMapper.insert(article);
    }

    @Override
    public RS deleteArticle() {
        return null;
    }

    @Override
    public RS updateArticle() {
        return null;
    }
}

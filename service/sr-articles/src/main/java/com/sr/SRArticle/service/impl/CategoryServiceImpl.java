package com.sr.SRArticle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sr.SRArticle.entity.Category;
import com.sr.SRArticle.mapper.CategoryMapper;
import com.sr.SRArticle.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sr.service.base.exception.CustomException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caiwenlong
 * @since 2020-12-23
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public List<Category> getAllCategories(String userId) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id_user", userId);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public Integer AddCategory(Category category) {
        // 非空判断
        if(StringUtils.isEmpty(category.getName())) {
            throw new CustomException("SR20002", "添加失败！菜单名称不能为空！");
        }
        return baseMapper.insert(category);
    }
}

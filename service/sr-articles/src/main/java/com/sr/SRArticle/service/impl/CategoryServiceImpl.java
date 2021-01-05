package com.sr.SRArticle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sr.SRArticle.entity.Category;
import com.sr.SRArticle.mapper.CategoryMapper;
import com.sr.SRArticle.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sr.service.base.exception.CustomException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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

    // 判断网站是否存在
    public Boolean isExitCategory(String keyword) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", keyword);
        Integer count = baseMapper.selectCount(queryWrapper);
        return count > 0;
    }

    public Boolean isValidated(Category category) {
        // 非空判断
        if(StringUtils.isEmpty(category.getName())) {
            throw new CustomException("SR20002", "添加失败！菜单名称不能为空！");
        }

        // 判断是否已经存在分类名称
        if(isExitCategory(category.getName())) {
            throw new CustomException("SR20007", "添加失败！菜单名称已经存在！");
        }

        return true;
    }

    @Override
    public List<Category> getAllCategories(String userId) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id_user", userId);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public Category selectCategoryById(String id) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public Integer addCategory(Category category) {
        if(isValidated(category)) {
            return baseMapper.insert(category);
        } else {
            return 0;
        }
    }

    @Override
    public Integer modifyCategory(Category category) {
        if(isValidated(category)) {
            UpdateWrapper<Category> updateWrapper = new UpdateWrapper<>();
            String categoryId = category.getId();
            updateWrapper.eq("id", categoryId);
            Category categoryEntity = selectCategoryById(categoryId);
            categoryEntity.setName(category.getName());
            categoryEntity.setIcon(category.getIcon());
            return baseMapper.update(categoryEntity, updateWrapper);
        } else {
            return 0;
        }
    }

    @Override
    public Integer deleteArticleCategory(String categoryId) {
                    return baseMapper.deleteById(categoryId);
    }

    @Override
    public Integer batchDeleteArticleCategory(ArrayList<String> idCategoryList) {
        return baseMapper.deleteBatchIds(idCategoryList);
    }
}

package com.sr.SRArticle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sr.SRArticle.entity.Articles;
import com.sr.SRArticle.entity.Category;
import com.sr.SRArticle.mapper.CategoryMapper;
import com.sr.SRArticle.service.ArticlesService;
import com.sr.SRArticle.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sr.service.base.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private ArticlesService articlesService;

    /**
     * 判断网站是否存在
     * @param category：网站分类对象
     * @return true or false
     */
    public Boolean isExitCategory(Category category) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        Map<String , Object> map = new HashMap<>();
        map.put("name" , category.getName());
        map.put("id_user" , category.getIdUser());
        queryWrapper.allEq(map);
        Integer count = baseMapper.selectCount(queryWrapper);
        return count > 0;
    }

    /**
     * 是否通过验证
     * @param category：分类对象
     * @return true or false
     */
    public Boolean isValidated(Category category) {
        // 非空判断
        if(StringUtils.isEmpty(category.getName())) {
            throw new CustomException("SR20002", "添加失败！菜单名称不能为空！");
        }

        // 判断是否已经存在分类名称
        if(isExitCategory(category)) {
            throw new CustomException("SR20007", "添加失败！菜单名称已经存在！");
        }

        return true;
    }

    /**
     * 设置菜单为父元素
     * @param id 分类ID
     */
    public void setCategoryIsParent(String id, Boolean flag) {
        UpdateWrapper<Category> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        Category categoryEntity = selectArticleCategoryById(id);
        categoryEntity.setIsParent(flag);
        baseMapper.update(categoryEntity, updateWrapper);
    }

    /**
     * 通过用户ID获取用户所有的分类
     * @param userId：用户ID
     * @return 返回分类列表
     */
    @Override
    public List<Category> getAllArticleCategories(String userId) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id_user", userId);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Category> getCategoryChildren(String categoryId) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id_parent", categoryId);
        return baseMapper.selectList(queryWrapper);
    }

    /**
     * 通过分类ID获取分类
     * @param id：分类ID
     * @return 分类对象
     */
    @Override
    public Category selectArticleCategoryById(String id) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return baseMapper.selectOne(queryWrapper);
    }

    /**
     * 通过分类名称获取分类
     * @param name：分类名称
     * @return 分类对象
     */
    @Override
    public Category selectArticleCategoryByName(String name) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        return baseMapper.selectOne(queryWrapper);
    }

    /**
     * 添加分类
     * @param category：提交过来的分类数据
     * @return 添加的条数
     */
    @Override
    public Category addArticleCategory(Category category) {
        if(isValidated(category)) {
            baseMapper.insert(category);
            if(!category.getIdParent().equals("0")) {
                Category categoryEntity = selectArticleCategoryById(category.getId());
                setCategoryIsParent(category.getIdParent(), true);
                String categoryId = categoryEntity.getId();
                articlesService.moveArticleToNewCategory(categoryId, category.getIdParent());
                return categoryEntity;
            } else {
                return selectArticleCategoryById(category.getId());
            }
        } else {
            return new Category();
        }
    }

    /**
     * 修改分类
     * @param category：提交过来的分类数据
     * @return 被修改的条数
     */
    @Override
    public Category modifyArticleCategory(Category category) {
        if(StringUtils.isEmpty(category.getName())) {
            throw new CustomException("SR20002", "添加失败！菜单名称不能为空！");
        }
        UpdateWrapper<Category> updateWrapper = new UpdateWrapper<>();
        String categoryId = category.getId();
        updateWrapper.eq("id", categoryId);
        Category categoryEntity = selectArticleCategoryById(categoryId);
        categoryEntity.setIdParent(category.getIdParent());
        categoryEntity.setName(category.getName());
        categoryEntity.setIcon(category.getIcon());
        baseMapper.update(categoryEntity, updateWrapper);
        return categoryEntity;
    }

    /**
     * 删除分类
     * @param categoryId：分类ID
     * @return 删除的条数
     */
    @Override
    public Integer deleteArticleCategory(String categoryId) {
                    return baseMapper.deleteById(categoryId);
    }

    /**
     * 通过分类ID列表批量删除分类
     * @param idCategoryList：分类ID列表
     * @param categoryParentId：被删除最顶层分类的父级ID，用来判断父级是否还有子分类
     * @return 返回被删除的条数
     */
    @Override
    public Integer batchDeleteArticleCategory(ArrayList<String> idCategoryList, ArrayList<String> categoryParentId) {
        int i = baseMapper.deleteBatchIds(idCategoryList);
        String parentId = categoryParentId.get(0);
        List<Category> categoryChildren = getCategoryChildren(parentId);
        // 如果父级ID没有子分类了，将父级分类的isParent设置为false
        if(categoryChildren.size()==0) {
            setCategoryIsParent(parentId, false);
        }
        return i;
    }
}

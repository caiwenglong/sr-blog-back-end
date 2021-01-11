package com.sr.SRArticle.service;

import com.sr.SRArticle.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caiwenlong
 * @since 2020-12-23
 */
public interface CategoryService extends IService<Category> {

    /**
     * 获取全部分类
     * @param userId：用户ID
     * @return 返回所有的分类
     */
    List<Category> getAllArticleCategories(String userId);

    /**
     * 获取全部分类
     * @param categoryId：分类ID
     * @return 返回分类的所有子分类
     */
    List<Category> getCategoryChildren(String categoryId);

    /**
     * 通过分类ID获取分类
     * @param id：分类ID
     * @return 返回分类信息
     */
    Category selectArticleCategoryById(String id);

    /**
     * 通过分类名称获取分类
     * @param name：分类名称
     * @return 返回分类信息
     */
    Category selectArticleCategoryByName(String name);


    /**
     * 添加分类
     * @param category：提交过来的分类数据
     * @return 返回添加的条数
     */
    Category addArticleCategory(Category category);


    /**
     * 添加分类
     * @param category：提交过来的分类数据
     * @return 返回修改的条数
     */
    Category modifyArticleCategory(Category category);

    /**
     * 删除分类
     * @param categoryId：分类ID
     * @return 返回被删除的条数
     */
    Integer deleteArticleCategory(String categoryId);


    /**
     * 批量删除分类
     * @param idCategoryList：分类ID列表
     * @param categoryParentId：被删除最顶层分类的父级ID，用来判断父级是否还有子分类
     * @return 返回被删除的条数
     */
    Integer batchDeleteArticleCategory(ArrayList<String> idCategoryList, ArrayList<String> categoryParentId);

}

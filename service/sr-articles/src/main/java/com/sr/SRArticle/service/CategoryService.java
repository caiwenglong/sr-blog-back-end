package com.sr.SRArticle.service;

import com.sr.SRArticle.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

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
     * @return
     */
    List<Category> getAllCategories(String userId);

    /**
     * 获取全部分类
     * @param id：分类ID
     * @return
     */
    Category selectCategoryById(String id);

    /**
     * 添加分类
     * @param category：提交过来的分类数据
     * @return
     */
    Integer addCategory(Category category);


    /**
     * 添加分类
     * @param category：提交过来的分类数据
     */
    void modifyCategory(Category category);

    /**
     * 删除分类
     * @param categoryId：分类ID
     * @return
     */
    Integer deleteArticleCategory(String categoryId);

}

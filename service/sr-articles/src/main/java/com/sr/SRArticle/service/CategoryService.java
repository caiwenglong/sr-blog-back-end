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
     * @param id：分类ID
     * @return 返回分类信息
     */
    Category selectArticleCategoryById(String id);

    /**
     * 添加分类
     * @param category：提交过来的分类数据
     * @return 返回添加的条数
     */
    Integer addArticleCategory(Category category);


    /**
     * 添加分类
     * @param category：提交过来的分类数据
     * @return 返回修改的条数
     */
    Integer modifyArticleCategory(Category category);

    /**
     * 删除分类
     * @param categoryId：分类ID
     * @return 返回被删除的条数
     */
    Integer deleteArticleCategory(String categoryId);


    /**
     * 批量删除分类
     * @param idCategoryList：分类ID列表
     * @return 返回被删除的条数
     */
    Integer batchDeleteArticleCategory(ArrayList<String> idCategoryList);

}

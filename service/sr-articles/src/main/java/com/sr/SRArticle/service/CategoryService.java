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
     * 添加文章
     * @param category：提交过来的分类数据
     * @return
     */
    Integer AddCategory(Category category);

}

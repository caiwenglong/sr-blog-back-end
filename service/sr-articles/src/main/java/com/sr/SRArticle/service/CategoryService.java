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

    List<Category> getAllCategories(String userId);

}

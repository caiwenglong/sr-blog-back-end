package com.sr.SRArticle.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.sql.Blob;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author caiwenlong
 * @since 2020-11-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Articles对象", description="")
public class Articles implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "文章ID")
      @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "文章分类")
    private String category;

    @ApiModelProperty(value = "文章内容")
    private String content;

    @ApiModelProperty(value = "文章内容")
    private String tags;

    @ApiModelProperty(value = "文章作者")
    private String author;

    @ApiModelProperty(value = "文章作者")
    private String idAuthor;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT) // 自动填充
    private Date gmtCreate;
    @TableField(fill = FieldFill.INSERT_UPDATE) // 自动填充
    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;


}

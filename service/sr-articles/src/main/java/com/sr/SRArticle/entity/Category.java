package com.sr.SRArticle.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2020-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Category对象", description="")
public class Category implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "分类ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "分类父ID")
    private String idParent;

    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "生成路由后的路径")
    private String path;

    @ApiModelProperty(value = "分类图标")
    private String icon;

    @ApiModelProperty(value = "创建者ID")
    @TableField("id_user")
    private String idUser;

    @ApiModelProperty(value = "是否禁用")
    @TableField(fill = FieldFill.INSERT)
    private Boolean isParent;

    @ApiModelProperty(value = "是否禁用")
    @TableField(fill = FieldFill.INSERT)
    private Boolean isDisabled;

    @ApiModelProperty(value = "分类创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "分类修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE) // 自动填充
    private Date gmtModified;


}

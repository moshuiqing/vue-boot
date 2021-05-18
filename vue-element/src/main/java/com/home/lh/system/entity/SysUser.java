package com.home.lh.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author 刘浩
 * @since 2021-05-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
@ApiModel(value="SysUser对象", description="系统用户")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "用户名")
    @TableField("userName")
    private String userName;

    @ApiModelProperty(value = "密码")
    @TableField("passWord")
    private String passWord;

    @ApiModelProperty(value = "盐")
    @TableField("salt")
    private String salt;

    @ApiModelProperty(value = "是否单点登录 0 否 1 是 默认0")
    @TableField("isSingle")
    private String isSingle;

    @ApiModelProperty(value = "是否删除 0 否 1是")
    @TableField("isdel")
    @TableLogic
    private String isdel;

    @ApiModelProperty(value = "是否禁用 0 否1是")
    @TableField("isDisable")
    private String isDisable;

    @ApiModelProperty(value = "角色id")
    @TableField("rouleId")
    private String rouleId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

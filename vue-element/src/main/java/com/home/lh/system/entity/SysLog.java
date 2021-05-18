package com.home.lh.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * <p>
 * 
 * </p>
 *
 * @author 刘浩
 * @since 2021-05-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_log")
@ApiModel(value="SysLog对象", description="")
public class SysLog extends Model<SysLog> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "logId", type = IdType.ASSIGN_UUID)
    private String logId;

    @ApiModelProperty(value = "请求地址")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "请求方法")
    @TableField("method")
    private String method;

    @ApiModelProperty(value = "操作id")
    @TableField("ip")
    private String ip;

    @ApiModelProperty(value = "耗时")
    @TableField("timeConsuming")
    private String timeConsuming;

    @ApiModelProperty(value = "是否异常")
    @TableField("isAbnormal")
    private String isAbnormal;

    @ApiModelProperty(value = "操作人")
    @TableField("operator")
    private String operator;

    @ApiModelProperty(value = "操作时间")
    @TableField("operatingTime")
    private String operatingTime;


    @Override
    protected Serializable pkVal() {
        return this.logId;
    }

}

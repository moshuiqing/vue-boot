package com.home.lh.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.util.List;

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
 * 系统菜单
 * </p>
 *
 * @author 刘浩
 * @since 2021-05-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_menu")
@ApiModel(value = "SysMenu对象", description = "系统菜单")
public class SysMenu extends Model<SysMenu> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	@TableId(value = "id", type = IdType.ASSIGN_UUID)
	private String id;

	@ApiModelProperty(value = "父级id")
	@TableField("parentId")
	private String parentId;

	@ApiModelProperty(value = "路径")
	@TableField("path")
	private String path;

	@ApiModelProperty(value = "组件")
	@TableField("component")
	private String component;

	@ApiModelProperty(value = "是否在测边栏显示")
	@TableField("hidden")
	private boolean hidden;

	@ApiModelProperty(value = "是否显示根路由")
	@TableField("alwaysShow")
	private boolean alwaysShow;

	@ApiModelProperty(value = "标题")
	@TableField("title")
	private String title;

	@ApiModelProperty(value = "图标")
	@TableField("icon")
	private String icon;

	@ApiModelProperty(value = "排序")
	@TableField("sort")
	private Integer sort;

	@ApiModelProperty(value = "是否删除 0 未删除 1 已删除")
	@TableField("isdel")
	@TableLogic
	private String isdel;

	@ApiModelProperty(value = "创建时间")
	@TableField("createTime")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime createTime;

	@ApiModelProperty(value = "子级菜单")
	@TableField(exist = false)
	private List<SysMenu> children;

	@TableField(exist = false)
	private SysMenuMeta meta;

	public SysMenuMeta getMeta() {
		return Meta(this.getTitle(), this.getIcon());
	}

	// 为meta构建JSONObejct对象值，前端用以图标和面包屑
	public SysMenuMeta Meta(String title, String icon) {
		SysMenuMeta menuMeta = new SysMenuMeta();
		menuMeta.setIcon(icon);
		menuMeta.setTitle(title);
		return menuMeta;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}

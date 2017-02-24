package com.sharebo.emp.web.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.sharebo.emp.web.entity.Menu;
/**
 * 菜单类的mapper封装接口
 * @author zhuhaiyuan
 *
 */
@Mapper
public interface MenuMapper {
	@Insert("insert into menu VALUE(UUID_SHORT(),#{menuname},#{url},#{icon},#{authority})")
	int addMenu(Menu menu);
}

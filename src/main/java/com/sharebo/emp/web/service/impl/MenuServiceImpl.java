package com.sharebo.emp.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sharebo.emp.web.entity.Menu;
import com.sharebo.emp.web.mapper.MenuMapper;
import com.sharebo.emp.web.service.MenuService;
@Service
@Transactional(rollbackFor = Exception.class)
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	MenuMapper menuMapper;

	@Override
	public int addMenu(Menu menu) {
		return menuMapper.addMenu(menu);
	}
	
}

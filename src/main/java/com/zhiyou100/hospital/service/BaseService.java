package com.zhiyou100.hospital.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyou100.hospital.pojo.Registration;

import java.util.List;


public interface BaseService<E>{
	/**
	 * 表关联的分页查询
	 * @param page 分页方式
	 * @param wrapper 查询条件
	 * @return 返还查询结果
	 */
	IPage<E> queryPage(Page<E> page, QueryWrapper<E> wrapper);

	/**
	 * 单挑数据的查询全部信息
	 * @return 返还查询结果
	 */
	List<E> queryAll();

	/**
	 * 单挑数据的根据id查询
	 * @param id id
	 * @return 返还查询结果
	 */
	E queryById(Integer id);

	/**
	 * 单挑数据的添加
	 * @param e 泛型
	 */
	void add(E e);

	/**
	 * 单挑数据的删除
	 * @param id id
	 */
	void delete(Integer id);

	/**
	 * 单挑数据的修改,根据泛型包含的id
	 * @param e 泛型
	 */
	void update(E e);
}

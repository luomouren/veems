package com.weisi.veems.services.base;

import tk.mybatis.mapper.common.Mapper;

import java.io.Serializable;
import java.util.List;

/**
 * @author:luomouren
 * @description:BaseService，能够根据泛型自动注入dao，并定义常用的CURD方法，每一个service再继承它
 * @dateTime: created in  2018-04-11 16:54
 * @modified by:
 **/
public interface BaseService<M extends Mapper<T>, T extends Serializable> {
	/**
	 * 通过id查询 实体
	 *
	 * @param id id
	 * @return 实体
	 */
	T selectByPrimaryKey(String id);

	/**
	 * 保存
	 *
	 * @param t
	 */
	void insert(T t);


	/**
	 * 根据主键保存更新
	 *
	 * @param t
	 */
	void updateByPrimaryKey(T t);

	/**
	 * 查询所有
	 *
	 * @return
	 */
	List<T> selectAll();


	/**
	 * 通过id删除实体
	 *
	 * @param id id
	 * @return 提示信息
	 */
	Integer deleteById(String id);

	/**
	 * 删除实体
	 *
	 * @param t
	 */
	void delete(T t);
}
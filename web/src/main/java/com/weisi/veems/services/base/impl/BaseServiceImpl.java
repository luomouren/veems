package com.weisi.veems.services.base.impl;

import com.weisi.veems.services.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.io.Serializable;
import java.util.List;

/**
 * @author:luomouren
 * @description:BaseService，能够根据泛型自动注入dao，并定义常用的CURD方法，每一个service再继承它
 * @dateTime: created in  2018-04-11 16:54
 * @modified by:
 **/
public abstract class BaseServiceImpl<M extends Mapper<T>, T extends Serializable> implements BaseService<M, T> {
	@Autowired
	protected M mapper;

	/**
	 * 通过id查询 实体
	 *
	 * @param id id
	 * @return 实体
	 */
	@Override
	public T selectByPrimaryKey(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	/**
	 * 保存
	 *
	 * @param t
	 */
	@Override
	public void insert(T t) {
		mapper.insert(t);
	}


	/**
	 * 根据主键保存更新
	 *
	 * @param t
	 */
	@Override
	public void updateByPrimaryKey(T t) {
		mapper.updateByPrimaryKey(t);
	}

	/**
	 * 查询所有
	 *
	 * @return
	 */
	@Override
	public List<T> selectAll() {
		return mapper.selectAll();
	}


	/**
	 * 通过id删除实体
	 *
	 * @param id id
	 * @return 提示信息
	 */
	@Override
	public Integer deleteById(String id) {
		return mapper.deleteByPrimaryKey(id);
	}

	/**
	 * 删除实体
	 *
	 * @param t
	 */
	@Override
	public void delete(T t) {
		mapper.delete(t);
	}


	public M getMapper() {
		return mapper;
	}

	public void setMapper(M mapper) {
		this.mapper = mapper;
	}
}
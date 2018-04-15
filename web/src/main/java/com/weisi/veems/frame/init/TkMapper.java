package com.weisi.veems.frame.init;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author:luomouren
 * @description:通用Mapper接口
 * @dateTime: created in  2018-04-09 17:06
 * @modified by:
 **/
public interface TkMapper<T> extends Mapper<T>, MySqlMapper<T> {
	//TODO
	//FIXME 特别注意，该接口不能被扫描到，否则会出错
}

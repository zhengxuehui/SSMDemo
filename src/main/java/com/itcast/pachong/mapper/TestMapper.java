package com.itcast.pachong.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TestMapper {

    /**
     * 返回数据库当前时间
     * @return
     */
    @Select("select now();")
    public String queryDate();

}

package com.example.keycloakdemo.dao.mapper;

import com.example.keycloakdemo.dao.model.SpaceSet;
import com.example.keycloakdemo.form.SpaceListForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : ex_yi.chen
 * @ClassName : SpaceSetMapper
 * @Description : 空间集信息mapper
 * @Date: 2022-07-15 14:14
 */
public interface SpaceSetMapper {


    /**
     * 添加空间集信息
     *
     * @param spaceSet 空间集对象
     */
    void add(SpaceSet spaceSet);

    /**
     * 修改空间集信息
     *
     * @param spaceSet 修改空间集对象
     */
    void update(SpaceSet spaceSet);

    /**
     * 根据id删除空间集信息
     *
     * @param id 空间集主键id
     */
    void delete(@Param(value = "id") Long id);

    /**
     * 空间集列表信息
     *
     * @param spaceListForm 空间集列表入参
     * @return 空间集列表数组
     */
    List<SpaceSet> list(SpaceListForm spaceListForm);

    /**
     * 空间集列表信息
     *
     * @param spaceListForm 空间集列表入参
     * @return 空间集列表数组
     */
    List<SpaceSet> listByName(SpaceListForm spaceListForm);

    /**
     * 空间集合总数
     *
     * @param spaceListForm 空间集合入参
     * @return 返回查询的空间集总数
     */
    int count(SpaceListForm spaceListForm);

    /**
     * 根据主键id获取空间集信息
     *
     * @param id 主键id
     * @return 空间集对象
     */
    SpaceSet detail(Long id);

}

package com.example.keycloakdemo.dao;


import com.example.keycloakdemo.dao.mapper.SpaceSetMapper;
import com.example.keycloakdemo.dao.model.SpaceSet;
import com.example.keycloakdemo.form.SpaceListForm;
import com.example.keycloakdemo.vo.SpaceSetVO;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : ex_yi.chen
 * @ClassName : SpaceSetDao
 * @Description : 空间集信息Dao
 * @Date: 2022-07-15 14:12
 */
@Repository
public class SpaceSetDao {

    @Resource
    private SpaceSetMapper spaceSetMapper;

    /**
     * 添加空间信息
     * @param spaceSet 空间集对象
     */
    public Long addSpaceSet(SpaceSet spaceSet){
        spaceSetMapper.add(spaceSet);
        return spaceSet.getId();
    }

    /**
     * 修改空间集信息
     * @param spaceSet 空间集对象
     */
    public void updateSpaceSet(SpaceSet spaceSet){
        spaceSetMapper.update(spaceSet);
    }

    /**
     * 根据主键id删除空间集信息
     * @param id 空间集主键id
     */
    public void deleteSpaceSet(Long id){
        spaceSetMapper.delete(id);
    }

    /**
     * 查询空间集合总数
     * @param spaceListForm 空间集合入参
     * @return 空间集合总数
     */
    public int count(SpaceListForm spaceListForm){
        return spaceSetMapper.count(spaceListForm);
    }

    /**
     * 获取空间集列表信息
     * @param spaceListForm 空间集入参
     * @return 空间集列表
     */
    public List<SpaceSet> list(SpaceListForm spaceListForm){
        return spaceSetMapper.list(spaceListForm);
    }

    /**
     * 根据空间集名称获取空间集列表信息
     * @param spaceListForm 空间集入参
     * @return 空间集列表
     */
    public List<SpaceSet> listByName(SpaceListForm spaceListForm){
        return spaceSetMapper.listByName(spaceListForm);
    }

    /**
     * 根据主键id获取空间集信息
     * @param id 主键id
     * @return 空间集对象
     */
    public SpaceSetVO spaceSetDetail(Long id) {
        SpaceSet spaceSet = spaceSetMapper.detail(id);
        Assert.notNull(spaceSet,"空间信息为空");
        SpaceSetVO spaceSetVO = SpaceSetVO.builder()
                .id(spaceSet.getId())
                .createTime(spaceSet.getCreateTime())
                .phone(spaceSet.getPhone())
                .createUser(spaceSet.getCreateUser())
                .eidName(spaceSet.getEidName())
                .eid(spaceSet.getEid())
                .name(spaceSet.getName())
                .address(spaceSet.getAddress())
                .code(spaceSet.getLabel())
                .build();
        return spaceSetVO;
    }
}

package com.example.keycloakdemo.dao;

import com.example.keycloakdemo.dao.mapper.ProjectEngineerMappingMapper;
import com.example.keycloakdemo.dao.model.ProjectEngineerMapping;
import com.example.keycloakdemo.form.EngineerListForm;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : ex_wen.fei
 * @ClassName : ProjectEngineerMappingDao
 * @Description : 项目施工人员关系
 * @Date: 2022年9月8日
 */
@Repository
public class ProjectEngineerMappingDao {

    @Resource
    private ProjectEngineerMappingMapper projectEngineerMappingMapper;

    /**
     * 根据项目id删除施工人员相关信息
     *
     * @param projectEngineerMapping 项目Id
     */
    public Integer deleteEngineer(ProjectEngineerMapping projectEngineerMapping) {
      return   projectEngineerMappingMapper.deleteEngineer(projectEngineerMapping);
    }

    /**
     * 查询项目施工人员施工人员列表
     *
     * @param personnelListForm
     * @return
     */
    public List<ProjectEngineerMapping> engineerList(EngineerListForm personnelListForm) {
        return projectEngineerMappingMapper.engineerList(personnelListForm);
    }

    /**
     * 分配施工人员
     * @param projectEngineerMapping
     */
    public Integer addEngineer(ProjectEngineerMapping projectEngineerMapping) {
        return projectEngineerMappingMapper.addEngineer(projectEngineerMapping);
    }

    /**
     * 根据项目id与施工人员id 查询施工人员是否添加
     * @param projectEngineerMapping
     * @return
     */
    public Integer countByProjectIdAndEngineerId(ProjectEngineerMapping projectEngineerMapping) {
        return projectEngineerMappingMapper.countByProjectIdAndEngineerId(projectEngineerMapping);
    }
}

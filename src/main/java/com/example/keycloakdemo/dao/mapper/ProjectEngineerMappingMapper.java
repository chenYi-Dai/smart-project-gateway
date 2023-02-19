package com.example.keycloakdemo.dao.mapper;


import com.example.keycloakdemo.dao.model.ProjectEngineerMapping;
import com.example.keycloakdemo.form.EngineerListForm;

import java.util.List;

/**
 * @author : ex_wen.fei
 * @ClassName : ProjectEngineerMappingMapper
 * @Description : 项目施工人员关系表mapper
 * @Date: 2022年9月8日
 */
public interface ProjectEngineerMappingMapper {

    /**
     * 根据项目id删除施工人员相关信息
     *
     * @param projectEngineerMapping 项目Id
     */
    Integer deleteEngineer(ProjectEngineerMapping projectEngineerMapping);
    /**
     * 查询项目施工人员施工人员列表
     * @param personnelListForm
     * @return
     */
    List<ProjectEngineerMapping> engineerList(EngineerListForm personnelListForm);

    /**
     * 添加施工人员
     * @param projectEngineerMapping
     */
    Integer addEngineer(ProjectEngineerMapping projectEngineerMapping);
    /**
     * 根据项目id与施工人员id 查询施工人员是否添加
     * @param projectEngineerMapping
     * @return
     */
    Integer countByProjectIdAndEngineerId(ProjectEngineerMapping projectEngineerMapping);
}

package com.example.clock.dao.mapper;

import com.example.clock.dao.model.Project;
import com.example.clock.form.ProjectAddForm;
import com.example.clock.form.ProjectListForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : ex_wen.fei
 * @ClassName : ProjectMapper
 * @Description : 项目信息mapper
 * @Date: 2022年9月8日 09点59分
 */
public interface ProjectMapper {

    /**
     * 查询项目总数
     *
     * @param projectListForm 空间集合入参
     * @return 项目总数
     */
    int count(ProjectListForm projectListForm);

    /**
     * 项目列表查询
     *
     * @param projectListForm 项目列表入参
     * @return 项目列表
     */
    List<Project> list(ProjectListForm projectListForm);

    /**
     * 查询是否存在同企业相同项目名
     *
     * @param projectAddForm 添加项目入参
     * @return 统计数量
     */
    Integer getProjectNameCount(ProjectAddForm projectAddForm);

    /**
     * 添加项目信息
     *
     * @param project 项目对象
     */
    void add(Project project);

    /**
     * 修改项目详情
     *
     * @param project 项目对象
     */
    void update(Project project);

    /**
     * 根据项目id删除项目相关信息
     * @param id 项目id
     * @return
     */
    int delete(@Param("id") Long id);
    /**
     * 获取项目详情信息
     * @param id 项目id
     * @return 项目信息
     */
    Project detail(Long id);

    List<Project> projectBySpaceSetId(Long spaceSetId);

    List<Project> projectManageList(ProjectListForm condition);

    int projectManageCount(ProjectListForm condition);
}

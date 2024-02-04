package com.example.clock.dao;


import com.example.clock.dao.mapper.ProjectMapper;
import com.example.clock.dao.model.Project;
import com.example.clock.form.ProjectAddForm;
import com.example.clock.form.ProjectListForm;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : ex_wen.fei
 * @ClassName : ProjectDao
 * @Description : 项目信息Dao
 * @Date: 2022-09-08 14:12
 */
@Repository
public class ProjectDao {

    @Resource
    private ProjectMapper projectMapper;

    /**
     * 查询项目总数
     *
     * @param projectListForm 项目列表入参
     * @return 项目总数
     */
    public int count(ProjectListForm projectListForm) {
        return projectMapper.count(projectListForm);
    }

    /**
     * 项目列表查询
     *
     * @param projectListForm 项目列表入参
     * @return 项目列表
     */
    public List<Project> list(ProjectListForm projectListForm) {
        return projectMapper.list(projectListForm);
    }

    /**
     * 查询是否存在同企业相同项目名
     *
     * @param projectAddForm 添加项目入参
     * @return 统计数量
     */
    public Integer getProjectNameCount(ProjectAddForm projectAddForm) {
        return projectMapper.getProjectNameCount(projectAddForm);
    }

    /**
     * 添加项目信息
     *
     * @param project 项目对象
     */
    public Long add(Project project) {
        projectMapper.add(project);
        return project.getId();
    }

    /**
     * 添加项目信息
     *
     * @param project 项目对象
     */
    public Long findAdd(Project project) {
        projectMapper.add(project);
        return project.getId();
    }

    /**
     * 修改项目信息
     *
     * @param project 项目对象
     */
    public void update(Project project) {
        projectMapper.update(project);
    }

    /**
     * 根据项目id删除项目相关信息
     *
     * @param id 项目id
     * @return
     */
    public int delete(Long id) {
        return projectMapper.delete(id);
    }

    /**
     * 获取项目详情信息
     *
     * @param id 项目id
     * @return 项目信息
     */
    public Project detail(Long id) {
        return projectMapper.detail(id);
    }

    public List<Project> projectBySpaceSetId(Long spaceSetId) {
        return projectMapper.projectBySpaceSetId(spaceSetId);
    }

    public List<Project> projectManageList(ProjectListForm condition) {
            return projectMapper.projectManageList(condition);
    }

    public int projectManageCount(ProjectListForm condition) {
        return projectMapper.projectManageCount(condition);
    }
}

package com.cts.fsd.userTask.service;


import com.cts.fsd.userTask.dao.ProjectMgrDao;
import com.cts.fsd.userTask.dao.TaskMgrDao;
import com.cts.fsd.userTask.dao.UserMgrDao;
import com.cts.fsd.userTask.domain.Project;
import com.cts.fsd.userTask.domain.Task;
import com.cts.fsd.userTask.domain.User;
import com.cts.fsd.userTask.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectMgrService {
    @Autowired
    ProjectMgrDao projectMgrDao;
    @Autowired
    UserMgrDao userMgrDao;
    @Autowired
    TaskMgrDao taskMgrDao;


    @Transactional
    public void addProject(Project project) throws NotFoundException {

        updateParentObject(project);
        projectMgrDao.addProject(project);
    }

    private void updateParentObject(Project project) throws NotFoundException {
        if (project.getMgrId() != null){
            User user=userMgrDao.viewUser(project.getMgrId());
            if (user != null){
                project.setManager(user);
            }
            else {
                throw new NotFoundException("Parent Task Not Found");
            }
        }
    }

    @Transactional
    public void updateProject(Project project)  throws NotFoundException  {
        updateParentObject(project);
        projectMgrDao.updateProject(project);
    }

    @Transactional
    public void deleteProject(Integer projectId){

        projectMgrDao.deleteProject(projectId);
    }


    @Transactional
    public Project viewProject(Integer projectId){
        return projectMgrDao.viewProject(projectId);
    }

    @Transactional
    public List<Project> listProject(){
        List<Project> projectList =projectMgrDao.listProject();
        for (Project project: projectList){
            List<Task> taskList = taskMgrDao.getTasksForProject(project.getId());
            if (taskList.size() > 0){
                project.setCountOfTasks(taskList.size());

                project.setCountOfCompletedTasks(Math.toIntExact(taskList.stream().filter(
                        task->!("completed".equals(task.getStatus()))).count()));
            }
            else
            {
                project.setCountOfTasks(0);
                project.setCountOfCompletedTasks(0);
            }
        }
        return projectList;
    }
}

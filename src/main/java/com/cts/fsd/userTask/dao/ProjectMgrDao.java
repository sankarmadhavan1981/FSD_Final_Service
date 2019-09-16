package com.cts.fsd.userTask.dao;


import com.cts.fsd.userTask.domain.Project;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
public class ProjectMgrDao {

    @PersistenceContext
    private EntityManager em;
    @Transactional
    public void addProject(Project project){

        em.merge(project);
    }

    @Transactional
    public void updateProject(Project project) {

        em.merge(project);
    }

    @Transactional
    public void deleteProject(Integer projectId){
        Project project=viewProject(projectId);
        em.remove(project);
    }

    @Transactional
    public List<Project> listProject(){
        String queryString= "from Project a";

        Query query= em.createQuery(queryString);
        List<Project> queryResult=query.getResultList();

       return queryResult;

    }


    @Transactional
    public Project viewProject(Integer projectId) {
        List<Project> queryResult=null;
        Query query= em.createQuery("from Project where id = :projectId");
        query.setParameter("projectId",projectId);
        queryResult =query.getResultList();
        if (queryResult.size() >0){
            return queryResult.get(0);
        }
        return null;
    }
}


package com.cts.fsd.userTask.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.cts.fsd.userTask.config.ParseDeserializer;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="Task")
public class Task {
    @Id
    @Column(name = "TASK_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String task;

    @Column
    private String priority;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(name="PARENT_TASK_ID")
    private ParentTask parentTaskEntity;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = ParseDeserializer.class)
    // @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column
    private LocalDateTime startDate;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = ParseDeserializer.class)
    // @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column
    private LocalDateTime endDate;

    @Transient
    private String parentTask;

    @Column
    private String status;


    @Transient
    private Integer userId;


    @Transient
    private Integer projectId;



    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(name="USER_ID")
    private User user;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(name="PROJECT_ID")
    private Project project;

    public Integer getId() {
        return id;
    }

    public String getTask() {
        return task;
    }

    public String getPriority() {
        return priority;
    }


    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }


    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @JsonIgnore
    public ParentTask getParentTaskEntity() {
        return parentTaskEntity;
    }

    @PostLoad
    public void updateParentTask(){
        if (this.parentTaskEntity != null){
            parentTask=parentTaskEntity.getTask();
        }
        if (this.user != null){
            userId=user.getId();
        }
        if (this.project != null){
            projectId=project.getId();
        }
    }

    public void setParentTaskEntity(ParentTask parentTaskEntity) {
        this.parentTaskEntity = parentTaskEntity;
        if (this.parentTaskEntity != null){
            parentTask=parentTaskEntity.getTask();
        }
    }


    public String getParentTask() {
        /*if (parentTaskEntity != null){
            parentTask=parentTaskEntity.getTask();
        }
        else
        {
            parentTask=null;
        }*/
        return parentTask;
    }


    public void setParentTask(String parentTask) {
        this.parentTask = parentTask;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        if (this.user!=null){
            userId=user.getId();
        }
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
        if (this.project!=null){
            projectId=project.getId();
        }
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
	
	public String getStatus() {
        return status;
    }
	
	public void setStatus(String status) {
        this.status = status;
    }
}


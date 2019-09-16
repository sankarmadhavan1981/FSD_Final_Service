package com.cts.fsd.userTask.domain;


import com.cts.fsd.userTask.config.ParseDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="PROJECT")

public class Project {

    @Id
    @Column(name = "PROJECT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "PROJECT")
    private String project;


    @Column(name="PRIORITY")
    private String priority;

	
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = ParseDeserializer.class)
    @Column(name = "STARTDATE")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = ParseDeserializer.class)
    @Column(name = "ENDDATE")
    private LocalDateTime endDate;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(name="MANAGER_ID")
    private User manager;
    @Transient
    private Integer mgrId;
    @Transient
    private Integer countOfTasks;
    @Transient
    private Integer countOfCompletedTasks;

    @PostLoad
    public void updateMgr(){
        if (this.manager != null){
            mgrId=manager.getId();
        }

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @JsonIgnore
    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public Integer getMgrId() {
        return mgrId;
    }

    public void setMgrId(Integer mgrId) {
        this.mgrId = mgrId;
    }

    public Integer getCountOfTasks() {
        return countOfTasks;
    }

    public void setCountOfTasks(Integer countOfTasks) {
        this.countOfTasks = countOfTasks;
    }

    public Integer getCountOfCompletedTasks() {
        return countOfCompletedTasks;
    }

    public void setCountOfCompletedTasks(Integer countOfCompletedTasks) {
        this.countOfCompletedTasks = countOfCompletedTasks;
    }
}

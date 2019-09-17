package com.cts.fsd.userTask.controller;


import com.cts.fsd.userTask.domain.SearchTask;
import com.cts.fsd.userTask.domain.Task;
import com.cts.fsd.userTask.exception.NotFoundException;
import com.cts.fsd.userTask.service.TaskMgrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/taskMgr")
public class TaskMgrController {

    @Autowired
    private TaskMgrService taskMgrService;

    @RequestMapping(value= "/**", method= RequestMethod.OPTIONS)
    public void corsHeaders(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with");
        response.addHeader("Access-Control-Max-Age", "3600");
    }

    
    @RequestMapping(value = "/addTask", method = RequestMethod.POST,headers = "Accept=application/json",produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Boolean> addTask(@RequestBody Task task) throws NotFoundException {

        taskMgrService.addTask(task);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }


    
    @RequestMapping(value = "/updateTask", method = RequestMethod.PUT ,produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Boolean> updateTask(@RequestBody Task task) throws NotFoundException {

        taskMgrService.updateTask(task);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    
    @RequestMapping(value = "/viewTask/{TaskTitle}", method = RequestMethod.GET,produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Task> viewTask(@PathVariable String TaskTitle){
        Task task= null;
        task = taskMgrService.viewTask(TaskTitle);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    
    @RequestMapping(value = "/listTasks/", method = RequestMethod.GET,produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Task>> listTasks(@RequestParam String task, @RequestParam String parentTask, @RequestParam String startPriority,
                                                @RequestParam String endPriority, @RequestParam String startDate, @RequestParam String endDate) {
        SearchTask searchTask =new SearchTask();
        searchTask.setTask(task);
        searchTask.setParentTask(parentTask);
        searchTask.setStartPriority(startPriority);
        searchTask.setEndPriority(endPriority);
        if (!StringUtils.isEmpty(startDate))
            searchTask.setStartDate(LocalDateTime.parse(startDate));
        if (!StringUtils.isEmpty(endDate))
            searchTask.setEndDate(LocalDateTime.parse(endDate));

        List<Task> taskList=taskMgrService.listTask(searchTask);
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    
    @RequestMapping(value = "/deleteTask/{taskName}", method = RequestMethod.DELETE,produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Boolean> deleteTask(@PathVariable String taskName) {

        taskMgrService.deleteTask(taskName);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}

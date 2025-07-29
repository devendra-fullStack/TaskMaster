package com.taskmaster.controller;


import com.taskmaster.model.Task;
import com.taskmaster.model.network.ResponseModel;
import com.taskmaster.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseModel<Task> createTask(@RequestBody Task task) {
        Task created = taskService.createTask(task);
        return ResponseModel.success("Task created successfully", created, HttpStatus.CREATED.value());
    }

    @GetMapping
    public ResponseModel<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseModel.success("Fetched all tasks", tasks,HttpStatus.OK.value());
    }

    @PutMapping("/{id}")
    public ResponseModel<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        Task task = taskService.updateTask(id, updatedTask);
        return ResponseModel.success("Task updated successfully", task,HttpStatus.ACCEPTED.value());
    }

    @DeleteMapping("/{id}")
    public ResponseModel<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseModel.success("Task deleted successfully", null,HttpStatus.ACCEPTED.value());
    }
}

package com.taskmaster.service;

import com.taskmaster.exceptions.TaskCreationException;
import com.taskmaster.exceptions.TaskDeleteException;
import com.taskmaster.exceptions.TaskNotFoundException;
import com.taskmaster.exceptions.TaskUpdateException;
import com.taskmaster.model.Task;
import com.taskmaster.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;

    // Constructor injection (cleaner than @Autowired)
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        try {
            return taskRepository.save(task);
        } catch (DataAccessException ex) {
            logger.error("Failed to create task: {}", ex.getMessage(), ex);
            throw new TaskCreationException("Could not create task", ex);
        }
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task updateTask(Long id, Task update) {
        // First, check if task exists
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with ID " + id + " not found"));

        // Apply updates
        task.setTitle(update.getTitle());
        task.setDescription(update.getDescription());
        task.setCompleted(update.isCompleted());

        try {
            return taskRepository.save(task);
        } catch (DataAccessException ex) {
            logger.error("Failed to update task with ID {}: {}", id, ex.getMessage(), ex);
            throw new TaskUpdateException("Could not update task with ID " + id, ex);
        }
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("Task with ID " + id + " not found");
        }

        try {
            taskRepository.deleteById(id);
        } catch (DataAccessException ex) {
            logger.error("Failed to delete task with ID {}: {}", id, ex.getMessage(), ex);
            throw new TaskDeleteException("Could not delete task with ID " + id, ex);
        }
    }
}

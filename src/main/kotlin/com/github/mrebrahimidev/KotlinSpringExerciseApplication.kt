package com.github.mrebrahimidev

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@SpringBootApplication
class KotlinSpringExerciseApplication

fun main(args: Array<String>) {
    runApplication<KotlinSpringExerciseApplication>(*args)
}

@Table("TASKS")
data class Task(@Id val id: String?, val title: String)

@Repository
interface TaskRepository : CrudRepository<Task, String>

@Service
class TaskService(private val taskRepository: TaskRepository) {
    fun findAll() = taskRepository.findAll()
    fun findById(id: String) = taskRepository.findById(id)

    @Transactional
    fun save(task: Task) = taskRepository.save(task)

    @Transactional
    fun update(id: String, task: Task): Task {
        return taskRepository.save(task.copy(id = id))
    }

    @Transactional
    fun deleteById(id: String) = taskRepository.deleteById(id)
}

@RestController
@RequestMapping("/tasks")
class TaskController(private val taskService: TaskService) {
    @GetMapping("all")
    fun findAll() = taskService.findAll()

    @GetMapping
    fun findById(@RequestParam("id") id: String) = taskService.findById(id)

    @PostMapping
    fun createTask(@RequestBody task: Task) = taskService.save(task)

    @PutMapping
    fun updateTask(@RequestParam("id") id: String, @RequestBody task: Task) = taskService.update(id, task)

    @DeleteMapping
    fun deleteTask(@RequestParam("id") id: String) = taskService.deleteById(id)
}
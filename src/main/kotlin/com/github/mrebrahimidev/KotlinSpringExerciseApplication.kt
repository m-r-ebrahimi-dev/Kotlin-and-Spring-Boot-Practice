package com.github.mrebrahimidev

import com.github.mrebrahimidev.sample.uuid
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import java.util.*

@SpringBootApplication
class KotlinSpringExerciseApplication

fun main(args: Array<String>) {
    runApplication<KotlinSpringExerciseApplication>(*args)
}


data class Task(val id: String? = UUID.randomUUID().toString(), val title: String)


@Service
class TaskService(private val db: JdbcTemplate) {
    fun findAll() = db.query("select * from tasks") { rs, _ ->
        Task(rs.getString("id"), rs.getString("title"))
    }

    fun findTaskById(id: String) = db.query("select * from tasks t where t.id=?", id) { rs, _ ->
        Task(rs.getString("id"), rs.getString("title"))
    }

    fun save(task: Task) {
        db.update("insert into tasks values (?,?)", task.id ?: task.title.uuid(), task.title)
    }
}


@RestController
@RequestMapping("/tasks")
class TaskController(private val taskService: TaskService) {
    @GetMapping("all")
    fun findAll() = taskService.findAll()

    @PostMapping
    fun createTask(@RequestBody task: Task) = taskService.save(task)
}
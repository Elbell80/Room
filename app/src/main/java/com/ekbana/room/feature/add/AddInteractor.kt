package com.ekbana.room.feature.add

import com.ekbana.room.database.AppDatabase
import com.ekbana.room.shared.model.task.Task
import io.reactivex.Single

class AddInteractor {
    private val addTaskRepository = AddTaskRepository()

    fun saveToDB(task: Task, appDatabase: AppDatabase?): Single<Task> {
      //  val task = Task(taskName, description)
        return addTaskRepository.saveToDb(task, appDatabase)
    }

    fun updateToDb(task: Task, appDatabase: AppDatabase?): Single<Task> {
      //  val taskModel = Task(taskName, taskDescription, taskId)
        return addTaskRepository.updateTask(appDatabase, task)
    }
}
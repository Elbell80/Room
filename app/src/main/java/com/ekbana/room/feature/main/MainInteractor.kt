package com.ekbana.room.feature.main

import com.ekbana.room.database.AppDatabase
import com.ekbana.room.shared.model.task.Task

class MainInteractor {
    private val mainRepository = MainRepository()

    fun getTaskList(appDatabase: AppDatabase?) =
        mainRepository.getTaskList(appDatabase)

    fun deleteTask(appDatabase: AppDatabase?, id : Int) = mainRepository.deleteTask(appDatabase, id)
}
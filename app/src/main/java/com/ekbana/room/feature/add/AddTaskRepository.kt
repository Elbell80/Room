package com.ekbana.room.feature.add

import com.ekbana.room.database.AppDatabase
import com.ekbana.room.shared.model.task.Task
import io.reactivex.Completable
import  io.reactivex.Single

class AddTaskRepository {
    fun saveToDb(task: Task, appDatabase: AppDatabase?): Single<Task> =
        Completable.fromAction {
            appDatabase?.appDao()?.insert(task)
        }.toSingleDefault(task)
    /* Single.create { e->
         appDatabase.appDao().insert(task)
     }*/

    fun updateTask(appDatabase: AppDatabase?, task: Task) =
        Completable.fromAction {
            appDatabase?.appDao()?.updateTask(task)
        }.toSingleDefault(task)
}
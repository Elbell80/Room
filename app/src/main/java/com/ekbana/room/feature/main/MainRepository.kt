package com.ekbana.room.feature.main

import android.util.Log
import com.ekbana.room.database.AppDatabase
import com.ekbana.room.shared.model.task.Task
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainRepository {

    fun getTaskList(appDatabase: AppDatabase?): Single<List<Task>> =
        Single.create { e ->
            appDatabase?.appDao()?.getAll()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    e.onSuccess(it)
                    Log.d("sizeOfList", it.size.toString())
                }, {
                    Log.d("errorMsg", it.localizedMessage)
                    e.onError(Throwable(it.localizedMessage))
                })

        }

    fun deleteTask(appDatabase: AppDatabase?, userId: Int): Single<Boolean> =
        Completable.fromAction {
            appDatabase?.appDao()?.delete(userId)
        }.toSingleDefault(true)
}
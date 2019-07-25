package com.ekbana.room.feature.add

import android.util.Log
import com.ekbana.room.feature.main.MainInteractor
import com.ekbana.room.shared.model.task.Task
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AddPresenter : MvpBasePresenter<AddView>() {
    private val addInteractor = AddInteractor()
    private val mainInteractor = MainInteractor()

    fun saveToDB(task: Task) {
        ifViewAttached { view ->
            addInteractor.saveToDB(task, view?.getAppDatabase())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.navigateToMainActivity()
                }, {
                    view?.showErrorMessage(it.localizedMessage)
                })
        }
    }

    fun getTaskByTaskID(taskId: Int) {
        ifViewAttached { view ->
            mainInteractor.getTaskList(view?.getAppDatabase())
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ tasksList ->
                    for (i in 0 until tasksList.count()) {
                        if (tasksList[i].id == taskId) {
                            val task = tasksList[i]
                            view?.populateField(task)
                        }
                    }
                }, {
                    view?.showErrorMessage(it.localizedMessage)
                })
        }
    }

    fun updateTask(task: Task) {
        ifViewAttached { view ->
            addInteractor?.updateToDb(task, view?.getAppDatabase())
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    Log.d("abcdefghi", it.id.toString())
                    Log.d("abcdefghitask", it.task.toString())
                    Log.d("abcdefghidesc", it.description.toString())
                    view?.navigateToMainActivity()
                }, {
                    view?.showErrorMessage(it.localizedMessage)
                })
        }


    }


}
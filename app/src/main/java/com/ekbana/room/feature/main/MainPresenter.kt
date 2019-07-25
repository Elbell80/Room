package com.ekbana.room.feature.main

import android.util.Log
import android.widget.Toast
import com.ekbana.room.shared.model.task.Task
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter : MvpBasePresenter<MainView>() {
    private val mainInteractor = MainInteractor()

    fun getTaskList() {
        ifViewAttached { view ->
            mainInteractor.getTaskList(view.getAppDatabase())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.populateListToView(it)
                    Log.d("check", it.size.toString())
                },{
                    Log.e("error", it.localizedMessage)
                })
        }

    }

    fun deleteTask(id: Int) {
        ifViewAttached { view ->
            mainInteractor.deleteTask(view?.getAppDatabase(), id)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    view?.refreshView()
                    view?.showToast("Delete successful")
                },{
                    view?.showToast("Something went wrong,")
                })
        }
    }

    private fun addTaskToList(task : Task) : List<Task>{
        val tasksLists = mutableListOf<Task>()
        tasksLists.add(task)
        return  tasksLists
    }
}
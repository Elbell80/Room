package com.ekbana.room.feature.main

import com.ekbana.room.database.AppDatabase
import com.ekbana.room.shared.model.task.Task
import com.hannesdorfmann.mosby3.mvp.MvpView

interface MainView  : MvpView {

    fun getAppDatabase(): AppDatabase?

    fun populateListToView(taskList: List<Task>?)

    fun showToast(msg: String)

    fun refreshView()
}
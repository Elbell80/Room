package com.ekbana.room.feature.add

import com.ekbana.room.database.AppDatabase
import com.ekbana.room.shared.model.task.Task
import com.hannesdorfmann.mosby3.mvp.MvpView

interface AddView : MvpView {

    fun getAppDatabase(): AppDatabase?

    fun navigateToMainActivity()

    fun showErrorMessage(errorMessage: String?)

    fun populateField(task: Task)
}
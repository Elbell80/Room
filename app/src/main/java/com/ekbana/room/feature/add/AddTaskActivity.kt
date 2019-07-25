package com.ekbana.room.feature.add

import android.os.Bundle
import android.widget.Toast
import com.ekbana.room.R
import com.ekbana.room.database.AppDatabase
import com.ekbana.room.database.TaskDetails
import com.ekbana.room.shared.model.task.Task
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import kotlinx.android.synthetic.main.activity_add_task_activityy.*

class AddTaskActivity : MvpActivity<AddView, AddPresenter>(), AddView {

    private var isEdit = false
    private lateinit var taskToUpdate : Task
    private var tasksId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task_activityy)
        checkIsAddOREdit()

        btnSave.setOnClickListener {
            val taskName = edtTAsk.text.toString()
            val taskDescription = edtDescription.text.toString()
            if (taskName == "" || taskDescription == "") {
                when {
                    taskName == "" -> Toast.makeText(this, "Task name cannot be empty.", Toast.LENGTH_LONG).show()
                    taskDescription == "" -> Toast.makeText(this, "Task description cannot be empty.", Toast.LENGTH_LONG).show()
                    else -> Toast.makeText(this, "Task Name and task description cannot be empty.", Toast.LENGTH_LONG).show()
                }
            } else {
                val taskLists = mutableListOf<TaskDetails>()
                val taskDetails1 = TaskDetails("Sanepa","2019/07/11", 3)
                val taskDetails2 = TaskDetails("Banepa","2019/07/11", 4)
                taskLists.add(taskDetails1)
                taskLists.add(taskDetails2)
                if (isEdit) {
                    val task = Task(id = tasksId, task = taskName, description = taskDescription, otherTasks = taskLists)
                    presenter?.updateTask(task)
                } else {

                    val task = Task(task = taskName, description = taskDescription, otherTasks = taskLists)
                    presenter?.saveToDB(task)
                }
            }
        }
    }

    private fun checkIsAddOREdit() {
        isEdit = intent.getBooleanExtra("isEdit", false)
        if (isEdit) {
            val taskId = intent.getIntExtra("taskId", 1)
            presenter?.getTaskByTaskID(taskId)
        }
    }

    override fun populateField(task: Task) {
        taskToUpdate = task
        tasksId = task.id
        edtTAsk.setText(task.task)
        edtDescription.setText(task.description)
    }

    override fun navigateToMainActivity() {
        onBackPressed()
    }

    override fun showErrorMessage(errorMessage: String?) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

    override fun createPresenter() = AddPresenter()

    override fun getAppDatabase(): AppDatabase? = AppDatabase.getAppDatabase(applicationContext)
}

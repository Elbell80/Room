package com.ekbana.room.feature.main

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.ekbana.room.R
import com.ekbana.room.database.AppDatabase
import com.ekbana.room.feature.add.AddTaskActivity
import com.ekbana.room.shared.model.task.Task
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MvpActivity<MainView, MainPresenter>(), MainView, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var taskAdapter: MainAdapter
    private lateinit var tasksList: MutableList<Task>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        presenter.getTaskList()

        srlTaskList?.setOnRefreshListener(this)
        imgAdd.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }
    }

    override fun createPresenter() = MainPresenter()

    override fun getAppDatabase(): AppDatabase? = AppDatabase.getAppDatabase(applicationContext)

    override fun populateListToView(taskList: List<Task>?) {
        if (taskList != null) {
            tasksList.clear()
            tasksList.addAll(taskList)
        }
        //  listOfTask = taskList as MutableList<Task>

        taskAdapter.notifyDataSetChanged()
    }


    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun onRefresh() {
        if (srlTaskList.isRefreshing) {
            presenter?.getTaskList()
            srlTaskList.isRefreshing = false
        }
    }

    override fun refreshView() {
        srlTaskList.isRefreshing = true
        presenter.getTaskList()
        srlTaskList.isRefreshing = false
    }

    private fun initRecyclerView() {
        rcvList?.layoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        rcvList?.addItemDecoration(dividerItemDecoration)
        tasksList = ArrayList()
        taskAdapter = MainAdapter(tasksList, object : OnEditDeleteListener {
            override fun onEdit(position: Int) {
                val taskId = tasksList[position].id
                val intent = Intent(this@MainActivity, AddTaskActivity::class.java)
                intent.putExtra("isEdit", true)
                intent.putExtra("taskId", taskId)
                startActivity(intent)
            }

            override fun onDelete(position: Int) {
                Log.d("test", "test")
                val taskId = tasksList[position].id
                presenter?.deleteTask(taskId)
            }
        })
        rcvList?.adapter = taskAdapter
    }

    override fun onResume() {
        super.onResume()
        presenter.getTaskList()
    }

}

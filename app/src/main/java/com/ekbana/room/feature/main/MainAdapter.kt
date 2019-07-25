package com.ekbana.room.feature.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ekbana.room.R
import com.ekbana.room.shared.model.task.Task
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.task_list_item.view.*

class MainAdapter(private val taskList:MutableList<Task>, private var listener : OnEditDeleteListener)
    : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int) =
        MainViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(R.layout.task_list_item, viewGroup, false)
        )

    override fun getItemCount(): Int = taskList.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        var taskDetails = ""
        var listOfTask= taskList[position]
        holder.taskTitle.text =listOfTask.task
        holder.taskDescription.text =listOfTask.description
        for (i in listOfTask.otherTasks){
            taskDetails = "$taskDetails ${i.location} , ${i.date} , ${i.hours} hours"
        }
        holder.txvTaskDetails.text = taskDetails

        holder.imgEdit?.setOnClickListener{
            listener.onEdit(position)
        }

        holder.imgDel?.setOnClickListener{
            listener.onDelete(position)
        }
    }

    class MainViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val recyclerView= view.rcvList
        val taskTitle= view.txvTaskTitle
        val taskDescription= view.txvTaskDesc
        val imgEdit = view.imvEdit
        val imgDel = view.imvDelete
        val txvTaskDetails = view.txvTaskDetails
    }

}
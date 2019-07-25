package com.ekbana.room.shared.model.task

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.ekbana.room.database.TaskDetails
import com.ekbana.room.utils.DatabaseConstants


@Entity(tableName = DatabaseConstants.databaseName)
data class Task(
    @ColumnInfo(name = "task")
    var task: String? = null,
    @ColumnInfo(name = "desciption")
    var description: String? = null,
    @ColumnInfo(name = "status")
    var status: String? = null,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var otherTasks: List<TaskDetails>
)


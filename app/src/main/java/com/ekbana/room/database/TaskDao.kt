package com.ekbana.room.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.ekbana.room.shared.model.task.Task
import com.ekbana.room.utils.DatabaseConstants
import io.reactivex.Single

@Dao
interface TaskDao {
    @Query("SELECT * FROM ${DatabaseConstants.databaseName}")
    fun getAll(): Single<List<Task>>

    @Insert
    fun insert(task: Task)

    @Query("DELETE FROM ${DatabaseConstants.databaseName} WHERE id = :userId")
    fun delete(userId: Int)

    @Update(onConflict = REPLACE)
    fun updateTask(task: Task)
}
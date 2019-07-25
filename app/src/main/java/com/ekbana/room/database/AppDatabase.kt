package com.ekbana.room.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.arch.persistence.room.migration.Migration
import android.content.Context
import com.ekbana.room.shared.model.task.Task
import com.ekbana.room.utils.DatabaseConstants


@Database(
    entities = [Task::class],
    version = 4
)

@TypeConverters(TaskDetailsConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): TaskDao

    companion object {
        private var appDatabase: AppDatabase? = null

        val MIGRATION_3_4: Migration = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE task "
                        + " ADD COLUMN status TEXT")
            }
        }

        fun getAppDatabase(context: Context?) =
            if (appDatabase != null) {
                appDatabase
            } else {
                if (context != null) {
                    Room.databaseBuilder(context, AppDatabase::class.java, DatabaseConstants.databaseName)
                         .addMigrations(MIGRATION_3_4)
                        .build()
                } else {
                    null
                }
            }
    }
}
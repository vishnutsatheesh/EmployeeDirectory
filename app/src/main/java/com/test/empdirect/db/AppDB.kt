package com.test.empdirect.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.test.empdirect.model.Employee


@Database(entities = [Employee::class], version = 6, exportSchema = false)
@TypeConverters(Converter::class)
abstract class AppDB : RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object {
        private var instance: AppDB? = null

        fun getInstance(context: Context): AppDB? {
            if (instance == null) {
                synchronized(AppDB::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDB::class.java, "db_employedirectory"
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }

    }

}

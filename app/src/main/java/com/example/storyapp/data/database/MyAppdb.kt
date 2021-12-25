package com.example.storyapp.data.database

import android.content.Context
import android.content.Entity
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Stories::class], version = 1)
abstract class MyAppDb : RoomDatabase() {
    abstract fun storiesDao(): MyAppDao
    companion object {
        @Volatile
        private var INSTANCE: MyAppDb? = null
        fun getDatabase(context: Context): MyAppDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    MyAppDb::class.java,
                    "StoryTeller")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}
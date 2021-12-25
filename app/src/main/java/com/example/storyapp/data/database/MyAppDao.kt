package com.example.storyapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MyAppDao {

    @Insert
    suspend fun insertData(stories : Stories)

    @Update
    suspend fun updateData(stories: Stories)

    @Delete
    suspend fun deleteData(stories: Stories)

    @Query("select * from stories order by id")
    fun getAll():LiveData<List<Stories>>

    @Query("select * from stories where story_title = :storyTitle")
    fun getByTitle(storyTitle : String) : LiveData<Stories>
}
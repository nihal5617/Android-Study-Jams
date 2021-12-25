package com.example.storyapp.viewmodel

import com.example.storyapp.data.database.MyAppDao
import com.example.storyapp.data.database.Stories

class StoriesRepository(private val storiesDao: MyAppDao) {

    val allStories = storiesDao.getAll()

    suspend fun insert(stories : Stories){
        storiesDao.insertData(stories)
    }
    suspend fun update(stories : Stories){
        storiesDao.updateData(stories)
    }
    suspend fun delete(stories : Stories){
        storiesDao.deleteData(stories)
    }
    fun currentStory(title : String){
        storiesDao.getByTitle(title)
    }
}
package com.example.storyapp.data.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Stories(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @NonNull @ColumnInfo(name = "story_title") val storytitle: String,
    @NonNull @ColumnInfo(name = "content") val content: String
)
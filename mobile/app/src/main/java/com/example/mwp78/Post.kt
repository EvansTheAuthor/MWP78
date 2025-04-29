package com.example.mwp78

import com.example.mwp78.Comment
import java.util.Date

data class Post(
    val id: Int,
    val username: String,
    val content: String,
    val timestamp: Date,
    val profileImageResId: Int,
    val comment: MutableList<Comment> = mutableListOf()
)
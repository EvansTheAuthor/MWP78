package com.example.mwp78

import java.util.Date

data class Comment(
    val id: Int,
    val username: String,
    val content: String,
    val timestamp: Date,
    val profileImageResId: Int
)
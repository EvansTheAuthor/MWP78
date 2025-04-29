package com.example.mwp78

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import java.util.*


class CommentActivity : AppCompatActivity() {
    private lateinit var imgProfile: ShapeableImageView
    private lateinit var tvUsername: TextView
    private lateinit var tvTimestamp: TextView
    private lateinit var tvPostContent: TextView
    private lateinit var rvComments: RecyclerView
    private lateinit var etComment: EditText
    private lateinit var btnSend: Button
    private lateinit var btnBack: ImageButton

    private val commentList = mutableListOf<Comment>()
    private lateinit var commentAdapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        imgProfile = findViewById(R.id.imgProfile)
        tvUsername = findViewById(R.id.tvUsername)
        tvTimestamp = findViewById(R.id.tvTimestamp)
        tvPostContent = findViewById(R.id.tvPostContent)
        rvComments = findViewById(R.id.rvComments)
        etComment = findViewById(R.id.etComment)
        btnSend = findViewById(R.id.btnSend)
        btnBack = findViewById(R.id.btnBackComment)

        tvUsername.text = "johndoe"
        tvTimestamp.text = TimeUtils.formatTimeAgo(Date().time - 10 * 60 * 1000)
        tvPostContent.text = "This is the post content."
        imgProfile.setImageResource(R.drawable.reiner_braun)

        setupRecyclerView()

        btnSend.setOnClickListener {
            val content = etComment.text.toString().trim()
            if (content.isNotEmpty()) {
                val newComment = Comment(
                    id = commentList.size + 1,
                    username = "you",
                    content = content,
                    timestamp = Date(),
                    profileImageResId = R.drawable.ic_user_placeholder
                )
                commentList.add(0, newComment)
                commentAdapter.notifyItemInserted(0)
                rvComments.scrollToPosition(0)
                etComment.text.clear()
            } else {
                Toast.makeText(this, "Comment cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecyclerView() {
        commentList.addAll(
            listOf(
                Comment(
                    id = 1,
                    username = "alice",
                    content = "meh",
                    timestamp = Date(System.currentTimeMillis() - 5 * 60 * 1000),
                    profileImageResId = R.drawable.ic_user_placeholder
                ),
                Comment(
                    id = 2,
                    username = "bob",
                    content = "Thanks for sharing!",
                    timestamp = Date(System.currentTimeMillis() - 15 * 60 * 1000),
                    profileImageResId = R.drawable.ic_user_placeholder
                )
            )
        )

        commentAdapter = CommentAdapter(commentList)
        rvComments.layoutManager = LinearLayoutManager(this)
        rvComments.adapter = commentAdapter
    }
}
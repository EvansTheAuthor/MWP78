package com.example.mwp78

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Timestamp
import java.util.Date

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var fabPost: FloatingActionButton
    val postList = mutableListOf<Post>()
    private lateinit var adapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvPosts)
        fabPost = findViewById(R.id.fabPost)

        adapter = PostAdapter(postList) { post ->
            val intent = Intent(this, CommentActivity::class.java)
            intent.putExtra("postId", post.id)
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        fabPost.setOnClickListener {
            val intent = Intent(this, PostActivity::class.java)
            startActivityForResult(intent, REQUEST_POST_CREATED)
        }

        loadPostsFromDatabases()
    }

    private fun loadPostsFromDatabases() {
        GlobalScope.launch(Dispatchers.IO) {
            val connection = Connection.connection()
            val newPosts = mutableListOf<Post>()
            try {
                val stmt = connection?.prepareStatement("SELECT * FROM posts ORDER BY id DESC")
                val rs = stmt?.executeQuery()
                while (rs != null && rs.next()) {
                    val id = rs.getInt("id")
                    val username = rs.getString("username")
                    val content = rs.getString("content")
                    val timestamp = rs.getTimestamp("timestamp")
                    val profileImageResId = rs.getInt("profile_img_res_id")

                    newPosts.add(
                        Post(
                            id = id,
                            username = username,
                            content = content,
                            timestamp = Date(timestamp.time),
                            profileImageResId = profileImageResId
                        )
                    )
                }
                withContext(Dispatchers.Main) {
                    postList.clear()
                    postList.addAll(newPosts)
                    adapter.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_POST_CREATED && resultCode == Activity.RESULT_OK) {
            loadPostsFromDatabases()
        }
    }

    companion object {
        private const val REQUEST_POST_CREATED = 1
    }
}
package com.example.mwp78

import android.media.Image
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PostActivity : AppCompatActivity() {
    private lateinit var btnClose: ImageButton
    private lateinit var btnPost: Button
    private lateinit var etPostContent: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        btnClose = findViewById(R.id.btnClose)
        btnPost = findViewById(R.id.btnPost)
        etPostContent = findViewById(R.id.etPostContent)

        btnClose.setOnClickListener { finish() }

        btnPost.setOnClickListener {
            val content = etPostContent.text.toString().trim()
            if (content.isNotEmpty()) {
                GlobalScope.launch(Dispatchers.IO) {
                    val conn = Connection.connection()
                    try {
                        val stmt = conn?.prepareStatement("INSERT INTO posts (username, content, timestamp, profile_image_res_id) VALUES (?, ?, ?, ?)\n")
                        stmt?.setString(1, "User123")
                        stmt?.setString(2, content)
                        stmt?.setTimestamp(3, java.sql.Timestamp(System.currentTimeMillis()))
                        stmt?.setInt(4, R.drawable.ic_user_placeholder)
                        stmt?.executeUpdate()

                        runOnUiThread {
                            Toast.makeText(this@PostActivity, "Post created!", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }catch (e: Exception) {
                        runOnUiThread {
                            Toast.makeText(this@PostActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Post cannot be empty!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
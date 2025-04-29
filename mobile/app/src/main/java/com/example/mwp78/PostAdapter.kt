package com.example.mwp78

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostAdapter (
    private val postList: List<Post>,
    private val onItemClick: (Post) -> Unit
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivProfile: ImageView = itemView.findViewById(R.id.ivProfile)
        private val tvUsername: TextView = itemView.findViewById(R.id.tvUsernamePost)
        private val tvTimestamp: TextView = itemView.findViewById(R.id.tvTimestampPost)
        private val tvContent: TextView = itemView.findViewById(R.id.tvContent)

        fun bind(post: Post) {
            tvUsername.text = post.username
            tvTimestamp.text = TimeUtils.formatTimeAgo(post.timestamp.time)
            tvContent.text = post.content
            ivProfile.setImageResource(post.profileImageResId)

            itemView.setOnClickListener {
                onItemClick(post)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int = postList.size
}
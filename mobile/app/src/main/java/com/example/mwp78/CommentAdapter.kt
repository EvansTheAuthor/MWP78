package com.example.mwp78


import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView

class CommentAdapter (
    private val commentList: List<Comment>
) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivProfile: ImageView = itemView.findViewById(R.id.imgProfileComment)
        private val tvUsername: TextView = itemView.findViewById(R.id.tvUsernameComment)
        private val tvTimestamp: TextView = itemView.findViewById(R.id.tvCommentTimePost)
        private val tvContent: TextView = itemView.findViewById(R.id.tvCommentContent)

        fun bind(comment: Comment) {
            tvUsername.text = comment.username
            tvTimestamp.text = TimeUtils.formatTimeAgo(comment.timestamp.time)
            tvContent.text = comment.content
            ivProfile.setImageResource(comment.profileImageResId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_comment, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(commentList[position])
    }

    override fun getItemCount(): Int = commentList.size
}
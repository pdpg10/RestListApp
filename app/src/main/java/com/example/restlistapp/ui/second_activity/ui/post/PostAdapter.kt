package com.example.restlistapp.ui.second_activity.ui.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restlistapp.R
import com.example.restlistapp.model.Post
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_post.*

class PostAdapter(private val action: (Post) -> Unit) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    private val list: ArrayList<Post> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_post, parent, false)
        return ViewHolder(view, action)
    }

    fun updateData(posts: Array<Post>) {
        list.clear()
        list.addAll(posts)
        notifyItemRangeInserted(0, posts.size)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.onBind(item)
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(
        override val containerView: View,
        action: (Post) -> Unit
    ) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        private var lastPost: Post? = null

        init {
            containerView.setOnClickListener {
                //                lastPost?.let { action(it) }
                if (lastPost != null)
                    action(lastPost!!)
            }
        }

        fun onBind(item: Post) {
            this.lastPost = item
//            itemView.setOnClickListener { action(item) }
            tv_title.text = item.title
            tv_body.text = item.body
        }
    }
}

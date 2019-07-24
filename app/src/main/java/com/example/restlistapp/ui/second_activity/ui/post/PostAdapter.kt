package com.example.restlistapp.ui.second_activity.ui.post


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restlistapp.R
import com.example.restlistapp.model.Post
import kotlinx.android.synthetic.main.fragment_post.view.*

class PostAdapter() : RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    private val list: ArrayList<Post> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_post, parent, false)
        return ViewHolder(view)
    }

    fun updateData(posts: Array<Post>) {
        list.clear()
        list.addAll(posts)
        notifyItemRangeInserted(0, posts.size)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.onBind(item)


        with(holder.mView) {
            tag = item
        }
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val tv_title: TextView = mView.tv_title
        val tv_body: TextView = mView.tv_body

        fun onBind(item: Post) {
            tv_title.text = item.title
            tv_body.text = item.body
        }
    }
}

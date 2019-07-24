package com.example.restlistapp.ui.user_list


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restlistapp.R
import com.example.restlistapp.model.User
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.*

class UserAdapter(
    context: Context,
    private val onSelectAction: (User) -> Unit
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private val list = mutableListOf<User>()
    private val inflater: LayoutInflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater
            .inflate(R.layout.item_user, parent, false)
        return ViewHolder(view, onSelectAction)
    }

    fun updateData(posts: Array<User>) {
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
        private val onSelectAction: (User) -> Unit
    ) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        private var selectedUser: User? = null

        init {
            containerView.setOnClickListener {
                selectedUser?.let { onSelectAction(it) }
            }
        }

        fun onBind(item: User) {
            this.selectedUser = item
            tv_name.text = item.name
            tv_email.text = item.email
            tv_address.text = item.address.toString()
        }
    }
}

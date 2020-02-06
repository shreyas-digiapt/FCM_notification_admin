package com.shreyas.fcmtestingadmin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shreyas.fcmtestingadmin.model.Model
import com.shreyas.fcmtestingadmin.utiels.OnClickItem
import com.shreyas.fcmtestingadmin.R
import kotlinx.android.synthetic.main.single_snippet.view.*

class AdminAdpater( private val context: Context) :
    RecyclerView.Adapter<AdminAdpater.CardHolder>() {

    private var items  = mutableListOf<Model>()
    private lateinit var listener: OnClickItem

    fun setData(items: MutableList<Model>) {
        listener = context as OnClickItem
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_snippet, parent, false)
        return CardHolder(view)
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.onBind(holder, items, position)
    }

    inner class CardHolder(view: View) : RecyclerView.ViewHolder(view) {

        val mCVCard = view.cv_card
        val mTvName = view.tv_name

        fun onBind(holder: CardHolder, items: MutableList<Model>, position: Int) {
            holder.mTvName.setText(items.get(position).name)

            holder.mCVCard.setOnClickListener(View.OnClickListener {
//                Toast.makeText(context, items.get(position).name,Toast.LENGTH_SHORT).show()
////                val frag = context.applicationContext.su
                listener.onclick(items.get(position))

            })
        }


    }
}
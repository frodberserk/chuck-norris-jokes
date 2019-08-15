package com.frodberserk.chucknorrisjokes.view.adapters

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.frodberserk.chucknorrisjokes.R
import com.frodberserk.chucknorrisjokes.entity.Joke
import kotlinx.android.synthetic.main.card_view_custom_layout.view.*

class JokesListAdapter(private var listener: (Joke?) -> Unit, private var dataList: List<Joke>?) :
    RecyclerView.Adapter<JokesListAdapter.ViewHolder>() {

    // Creating card_view_custom_layout ViewHolder to speed up the performance
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvId: TextView? = itemView.tv_id_card_view_custom_layout
        val tvJoke: TextView? = itemView.tv_joke_card_view_custom_layout
    }

    override fun getItemCount() = dataList?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // create a new view
        val viewRow = LayoutInflater.from(parent.context).inflate(R.layout.card_view_custom_layout, parent, false)
        return ViewHolder(viewRow)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvId?.text = dataList?.let { it[position].id.toString() }
        holder.tvJoke?.text = dataList?.let { Html.fromHtml(it[position].text) }
        holder.itemView.setOnClickListener { listener(dataList?.get(position)) }
    }

    fun updateData(list: List<Joke>) {
        this.dataList = list
        this.notifyDataSetChanged()
    }
}
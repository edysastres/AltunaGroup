package com.altunagroup.AltunaGroup

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.settings_list_item.view.*

class SettingsAdapter(val items: ArrayList<String>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.settings_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtSettings.text = items.get(position)
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val txtSettings = view.txtSettings
}
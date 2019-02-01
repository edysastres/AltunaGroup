package com.altunagroup.AltunaGroup

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.settings_list_item.view.*

class InvoicesAdapter(val items: ArrayList<String>, val context: Context) : RecyclerView.Adapter<InvoicesViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvoicesViewHolder {
        return InvoicesViewHolder(LayoutInflater.from(context).inflate(R.layout.invoices_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: InvoicesViewHolder, position: Int) {
        //holder.txtSettings.text = items.get(position)
    }
}

class InvoicesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    //val txtSettings = view.txtSettings
}
package com.altunagroup.AltunaGroup

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.address_list_item.view.*
import kotlinx.android.synthetic.main.settings_list_item.view.*

class AddressAdapter(val items: ArrayList<String>, val context: Context) : RecyclerView.Adapter<AddressViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        return AddressViewHolder(LayoutInflater.from(context).inflate(R.layout.address_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.txtAddresses.text = items.get(position)
        holder.txtNumAddress.text = "" + position
    }
}

class AddressViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val txtAddresses = view.txtAddressItem
    val txtNumAddress = view.txtNumberItem
}
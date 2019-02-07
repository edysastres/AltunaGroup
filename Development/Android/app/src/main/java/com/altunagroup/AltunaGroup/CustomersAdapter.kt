package com.altunagroup.AltunaGroup

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.customer_list_item.view.*

class CustomersAdapter (val items: List<Customer>, val context: Context) : RecyclerView.Adapter<CustomersAdapter.CustomersViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomersViewHolder {
        var view = CustomersViewHolder(LayoutInflater.from(context).inflate(R.layout.customer_list_item, parent, false))

        return view
    }

    override fun onBindViewHolder(holder: CustomersViewHolder, position: Int) {
        holder.txtName.text = items.get(position).name
        holder.txtAddress.text = items.get(position).address

    }


    class CustomersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtName = view.txtReferenceItem
        var txtAddress = view.txtCustomerAddressItem
    }

}

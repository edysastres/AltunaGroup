package com.altunagroup.AltunaGroup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_collection_list.*
import kotlinx.android.synthetic.main.activity_product_list.*

class ProductListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        title = "Inventario"

        var references = arrayListOf("","","","","","","","","","","","")

        rv_products.layoutManager = LinearLayoutManager(this)
        rv_products.adapter = ReferencesAdapter(references, this)

        rv_products.addOnItemClickListener(object: OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                // Your logic
                Snackbar.make(view, "" + position, Snackbar.LENGTH_SHORT).show()
            }
        })
    }
}

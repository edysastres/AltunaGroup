package com.altunagroup.AltunaGroup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.Toast

class ReferenceDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reference_detail)



        val reference = intent.getIntExtra("idReference", 0)
        title = "KWI-1D Cajita " +reference

                Toast.makeText(this, "Agregado " + reference, Toast.LENGTH_LONG).show()


    }
}

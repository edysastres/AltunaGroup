package com.altunagroup.AltunaGroup

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.*
import java.util.*

class EventsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)
        setTitle("Eventos")

        var btnInviteMore : Button = findViewById(R.id.btnInviteMore) //Crea referencia al botón de agregar

        btnInviteMore.setOnClickListener { //Le da su acción al botón para lanzar la actividad de invitar cerrajeros
            var i : Intent = Intent(this, InviteLocksmithActivity::class.java) //Crea el intent para dirigir a la actividad para invitar cerrajeros
            startActivity(i) //Lanza la nueva actividad al tocar el botón
        }

        //Obtiene los eventos disponibles según el usuario loggeado y los agrega al RecyclerView de los eventos
        RequestHelper(this).getEvents(findViewById(R.id.eventsActivity), this, findViewById<ProgressBar>(R.id.eventsProgress), findViewById<ProgressBar>(R.id.locksmithsProgress))
    }

    //TODO: Implementar onActivityResult para refrescar los cerrajeros invitados al regresar de la actividad de invitar cerrajeros


}

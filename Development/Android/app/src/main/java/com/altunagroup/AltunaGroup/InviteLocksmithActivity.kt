package com.altunagroup.AltunaGroup

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView

class InviteLocksmithActivity : AppCompatActivity() {

    //Actividad para invitar cerrajeros al evento

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invite_locksmith)
        setTitle(getString(R.string.activity_invite_locksmith))

        var lv : ListView = findViewById(R.id.lvLocksmiths) //Hace referencia al listView para enviarlo a la solicitud y así poder asignarle su adapter
        RequestHelper(this).getLocksmithsFromCustomer(this, lv) //Lanza la solicitud para obtener los cerrajeros asignados a ese customer y mostrarlos en el ListView
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean { //Crea el menú de acciones en el Toolbar
        menuInflater.inflate(R.menu.locksmith_menu, menu) //Especifica qué menú utilizar
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean { //Maneja las acciones del menú
        if(item?.itemId == R.id.action_ok){ //Cuando se selecciona aceptar
            //TODO: Implementar método/solicitud para enviar los cerrajeros invitados a la base de datos y así mostrarlos en la lista de invitados por evento
            Snackbar.make(findViewById(R.id.action_ok) as View, "Por implementar función de aceptar", Snackbar.LENGTH_LONG).setAction("Aceptar", View.OnClickListener {  }).show()
        } else if(item?.itemId == R.id.add_locksmith){
            //Re-dirige a la actividad creada para agregar un cerrajero
            var i : Intent = Intent(this, AddLocksmithActivity::class.java)
            startActivity(i)
        }
        return super.onOptionsItemSelected(item)
    }

}

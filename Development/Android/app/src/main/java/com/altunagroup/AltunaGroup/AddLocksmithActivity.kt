package com.altunagroup.AltunaGroup
import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView

class AddLocksmithActivity : AppCompatActivity() {

    //Actividad utilizada para crear y registrar un cerrajero a la base de datos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_locksmith)
        setTitle(getString(R.string.activity_add_locksmith))

        var avatarContainer : ImageView = findViewById(R.id.imgLocksmithPic) //ImageView de la imagen del cerrajero

        avatarContainer.setOnClickListener(View.OnClickListener { //Define la acción del ImageView
            var picIntent : Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE) //Crea un intent para tomar una imagen del cerrajero
            startActivityForResult(picIntent, 1) //Lanza el intent para capturar la fotografía del cerrajero
        })

        var btnAddAddress : Button = findViewById<Button>(R.id.btnAddAddress) //Botón para añadir direcciones al cerrajero

        btnAddAddress.setOnClickListener(View.OnClickListener {
            var i : Intent = Intent(this, AddAddressActivity::class.java) //Crea un intent para lanzar la actividad de añadir direcciones
            startActivity(i) //Cambiar a startActivityForResult probablemente para retornar las direcciones
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_locksmith_menu, menu) //Infla el menú en el toolbar con 2 acciones: guardar y cancelar
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        //Valida que opción del menú se seleccionó
        if(item?.itemId == R.id.action_add){ //Si es acción de agregar, guarda el cerrajero
            Snackbar.make(findViewById<View>(R.id.action_add), "Por implementar función de guardar", Snackbar.LENGTH_LONG).setAction("Aceptar", View.OnClickListener {  }).show()
        } else if(item?.itemId == R.id.action_cancel){//Si es acción de cancelar, destruye la actividad
            finishActivity(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, imageReturnedIntent: Intent?) {
        //Obtiene el activityresult del intent de tomar fotografía
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent)
        var avatarContainer : ImageView = findViewById(R.id.imgLocksmithPic) //Obtiene una referencia al contenedor de la imagen del cerrajero para asignar la fotografía tomada al ImageView
        when (requestCode) {
            0 -> if (resultCode == Activity.RESULT_OK) {
                val selectedImage = imageReturnedIntent!!.data
                avatarContainer.setImageURI(selectedImage)
            }
            1 -> if (resultCode == Activity.RESULT_OK) {
                val selectedImage = imageReturnedIntent!!.data
                avatarContainer.setImageURI(selectedImage)
            }
        }
    }

}

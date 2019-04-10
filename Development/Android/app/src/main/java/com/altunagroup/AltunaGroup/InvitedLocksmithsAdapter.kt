package com.altunagroup.AltunaGroup

import android.app.Activity
import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*

class InvitedLocksmithsAdapter : BaseAdapter {

    private var locksmiths : ArrayList<LocksmithModel> = ArrayList<LocksmithModel>() //ArrayList que almacena la información de los cerrajeros
    private var layoutInflater : LayoutInflater

    constructor(locksmiths: ArrayList<LocksmithModel>, activity: Activity) : super() {
        this.locksmiths = locksmiths
        layoutInflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View { //Retorna el elemento visual ya inflado con la información de los objetos
        var v : View //Auxiliar para generar la vista
        var viewHolder : ViewHolder
        v = layoutInflater.inflate(R.layout.layout_locksmith_listitem, null) //establece la vista al modelo generado para ese ListView
        //Establece la información de cada cerrajero a los elementos visuales
        viewHolder = ViewHolder(v)
        viewHolder.locksmithName.text = locksmiths.get(position).locksmithDesc //Nombre del cerrajero
        viewHolder.locksmithMail.text = locksmiths.get(position).locksmithAddresses.get(0).eMail //Email del cerrajero
        viewHolder.ticket.text = if(locksmiths.get(position).ticket.length > 0) locksmiths.get(position).ticket else "N/E" //Ticket generado para el cerrajero
        viewHolder.sentDate.text = if(locksmiths.get(position).ticket.length > 0) Date().date.toString() else "N/E" //Placeholder para fecha de envío
        viewHolder.mailSent.setBackgroundColor(if(locksmiths.get(position).ticketSent) ContextCompat.getColor(v.context, R.color.activeBlue) else ContextCompat.getColor(v.context, R.color.grayBg)) //Establece el color de 'View' a gris o azul en función de si el mail ya fue enviado o no
        viewHolder.locksmithAvatar.setImageBitmap(locksmiths.get(position).image) //Establece la foto del cerrajero/cerrajería al elemento
        viewHolder.mailSender.setOnClickListener(View.OnClickListener { //Acción que realizará el botón de cada item en la lista
            Snackbar.make(v, "Correo enviado", Snackbar.LENGTH_LONG).setAction("Aceptar", View.OnClickListener {  }).show() //Alerta como feedback visual
            viewHolder.mailSent.setBackgroundColor(ContextCompat.getColor(v.context, R.color.activeBlue))//Cambia el color del view a azul para marcarlo como enviado
            viewHolder.sentDate.text = Date().toString() //Establece la fecha de envío al momento en que se presionó el botón
        })
        return v //Retorna la vista (elemento visual)
    }

    override fun getItem(position: Int): LocksmithModel {
        return locksmiths.get(position) //Función para obtener el item en un índice especificado
    }

    override fun getItemId(position: Int): Long {
        return position.toLong() //Función para retornar el id de un item de la lista
    }

    override fun getCount(): Int {
        return this.locksmiths.size //Retorna la cantidad de items generados, si es 0 no infla el ListView
    }


    //Clase auxiliar para mantener la información del elemento visual y llenar sus propiedades
    class ViewHolder(view: View) {
        var locksmithName : TextView = view.findViewById(R.id.txtLocksmithName)
        var locksmithMail : TextView = view.findViewById(R.id.txtLocksmithMail)
        var ticket : TextView = view.findViewById(R.id.txtTicket)
        var sentDate : TextView = view.findViewById(R.id.txtSentDate)
        var mailSender : ImageButton = view.findViewById(R.id.btnSendMail)
        var mailSent : View = view.findViewById(R.id.chkMailSent)
        var locksmithAvatar : CircleImageView = view.findViewById(R.id.imgLocksmithAvatar)
    }

}
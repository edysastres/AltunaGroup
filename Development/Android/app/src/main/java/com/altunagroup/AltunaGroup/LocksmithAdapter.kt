package com.altunagroup.AltunaGroup

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Switch
import android.widget.TextView

//Adapter utilizado en el ListView de Cerrajeros por cliente (el que se utiliza para invitar cerrajeros a un evento)

class LocksmithAdapter : BaseAdapter {

    private var locksmiths : ArrayList<LocksmithModel> = ArrayList<LocksmithModel>() //Listado de cerrajeros
    private var layoutInflater : LayoutInflater //Auxiliar para mostrar vistas

    constructor(locksmiths: ArrayList<LocksmithModel>, activity: Activity) : super() {
        this.locksmiths = locksmiths
        layoutInflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View { //Obtiene el elemento visual con los datos de cada cerrajero
        var v : View //Vista a retornar
        var viewHolder : ViewHolder //ViewHolder con la información necesaria para la vista
        v = layoutInflater.inflate(R.layout.layout_locksmith_selection, null) //Infla la vista para poderla llenar y mostrar
        viewHolder = ViewHolder(v) //Obtiene el ViewHolder de la vista
        v.tag = viewHolder //Guarda ese viewHolder en la vista para usos futuros
        viewHolder.locksmithName.text = locksmiths.get(position).locksmithDesc //Establece el nombre del cerrajero
        viewHolder.locksmithBusiness.text = locksmiths.get(position).tradeName //Establece el texto de la razón social
        viewHolder.invited.isChecked = locksmiths.get(position).ticketSent //Activa o desactiva el switch en caso de que el cerrajero ya haya sido invitado anteriormente
        return v //Retorna la vista
    }

    override fun getItem(position: Int): LocksmithModel {
        return locksmiths.get(position) //Retorna el cerrajero de la posición solicitada
    }

    override fun getItemId(position: Int): Long {
        return position.toLong() //Retorna el id de un objeto
    }

    override fun getCount(): Int {
        return this.locksmiths.size //Retorna la cantidad de cerrajeros asociados al cliente
    }

    //ViewHolder personalizado para nuestro elemento
    class ViewHolder(view: View) {
        var locksmithName : TextView = view.findViewById(R.id.lblLocksmithName)
        var locksmithBusiness : TextView = view.findViewById(R.id.lblLocksmithBusiness)
        var invited : Switch = view.findViewById(R.id.switchInvited)
    }

}


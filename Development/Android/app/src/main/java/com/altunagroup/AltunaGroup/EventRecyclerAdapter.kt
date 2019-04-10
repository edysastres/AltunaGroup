package com.altunagroup.AltunaGroup

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import kotlin.random.Random

//Adaptador de datos para el RecyclerView de los eventos

class EventRecyclerAdapter : RecyclerView.Adapter<EventRecyclerAdapter.ViewHolder> {

    private var events : ArrayList<Event> = ArrayList<Event>() //Lista para obtener los eventos disponibles
    private var ctx : Context //Almacena el Context desde la actividad para no hacer referencias desde la clase y obtener context
    private var lvCerrajerosInvitados : ListView //Referencia al listview de cerrajeros para poder adaptarlo
    private var act : Activity //Índice a la actividad padre
    private var progress: ProgressBar //Este objeto hace referencia a una barra de progreso que se utiliza para dar un feedback visual al usuario, para enviarlo e invocarlo desde RequestHelper

    constructor(events: ArrayList<Event>, ctx: Context, lvCerrajeros: ListView, act : Activity, loadProgress: ProgressBar) : super() {
        this.events = events
        this.ctx = ctx
        this.lvCerrajerosInvitados = lvCerrajeros
        this.act = act
        this.progress = loadProgress
        progress.visibility = View.GONE
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder { //Evento ejecutado al crear el viewHolder (información de la vista)
        var view : View = LayoutInflater.from(p0.context).inflate(R.layout.layout_event_container, null) //Infla el elemento visual que muestra los eventos
        return ViewHolder(view) //Retorna el elemento inflado
    }

    override fun getItemCount(): Int {
        return events.size //Retorna la cantidad de eventos disponibles, si es 0 no infla ningún elemento
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) { //Evento ejecutado al enlazar el ViewHolder
        var imageAsBytes : ByteArray = Base64.decode(events.get(p1).image, Base64.DEFAULT) //Obtiene los bytes de la imagen desde la string base64
        var decoded : Bitmap = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.size) //Crea una imagen a partir del string recibido

        p0.eventLogo.setImageBitmap(decoded) //Establece la imagen generada como el logo del evento
        p0.eventName.text = events.get(p1).eventName //Establece el nombre del evento
        p0.eventLocation.text = events.get(p1).location //Establece el lugar del evento
        p0.eventDate.text = events.get(p1).beginDate.toString() + "-" + events.get(p1).endDate.toString() //Establece la fecha de inicio y fin del evento

        p0.eventLogo.setOnClickListener(View.OnClickListener { //Crea el evento para ejecutar una acción al tocar la imagen del evento
            RequestHelper(ctx).getLocksmithsFromEvent(events.get(p1).eventId, lvCerrajerosInvitados, act, progress) //Ejecuta la solicitud para traer los cerrajeros asociados al evento
        })
    }

    class ViewHolder : RecyclerView.ViewHolder { //Implementación personalizada de un ViewHolder para adaptarlo a nuestra vista personalizada
        var eventLogo : ImageView //Logo del evento
        var eventName : TextView //Nombre del evento
        var eventLocation : TextView //Ubicación del evento
        var eventDate : TextView //Fecha de inicio y fin del evento

        constructor(itemView: View) : super(itemView) { //Recibe la vista del evento y establece las propiedades del viewHolder en base a la vista
            this.eventLogo = itemView.findViewById(R.id.imgEventLogo)
            this.eventName = itemView.findViewById(R.id.txtEventName)
            this.eventLocation = itemView.findViewById(R.id.txtEventLocation)
            this.eventDate = itemView.findViewById(R.id.txtEventDate)
        }
    }
}
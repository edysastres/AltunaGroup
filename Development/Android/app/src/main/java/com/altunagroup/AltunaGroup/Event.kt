package com.altunagroup.AltunaGroup

//Clase utilizada para modelar los datos de un evento

class Event {

    var eventId : Int
    var eventName : String
    var location : String
    var city : String
    var state : String
    var countryId : String
    //public var beginDate : Date
    //public var endDate : Date
    var beginDate : String
    var endDate : String
    var image : String

    constructor(
        eventId: Int,
        eventName: String,
        location: String,
        city: String,
        state: String,
        countryId: String,
        beginDate: String,
        endDate: String,
        image: String
    ) {
        this.eventId = eventId
        this.eventName = eventName
        this.location = location
        this.city = city
        this.state = state
        this.countryId = countryId
        this.beginDate = beginDate
        this.endDate = endDate
        this.image = image
    }
}
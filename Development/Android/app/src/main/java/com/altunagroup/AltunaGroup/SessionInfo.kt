package com.altunagroup.AltunaGroup

import java.util.*

class SessionInfo {
    //Login-Persistentes
    var user : String
    var pass : String
    //Persistentes
    var userId : String = ""
    var dbInfo : DatabaseInfo = DatabaseInfo("0", "", "")
    var databaseName : String = ""
    var databaseDescription : String = ""
    var databaseList : ArrayList<DatabaseInfo> = ArrayList<DatabaseInfo>()
    var loginDate : Date = Date()
    var languageId : String = ""
    var authorizationToken : String = ""
    var groupName : String = ""

    constructor(
        user: String,
        pass: String,
        userId: String,
        dbInfo: DatabaseInfo,
        databaseName: String,
        databaseDescription: String,
        databaseList: ArrayList<DatabaseInfo>,
        loginDate: Date,
        languageId: String,
        authorizationToken: String,
        groupName: String
    ) {
        this.user = user
        this.pass = pass
        this.userId = userId
        this.dbInfo = dbInfo
        this.databaseName = databaseName
        this.databaseDescription = databaseDescription
        this.databaseList = databaseList
        this.loginDate = loginDate
        this.languageId = languageId
        this.authorizationToken = authorizationToken
        this.groupName = groupName
    }

    constructor(user: String, pass: String) {
        this.user = user
        this.pass = pass
    }


}

class DatabaseInfo{
    var databaseId : String
    var databaseName : String
    var databaseDescription : String

    constructor(databaseId: String, databaseName: String, databaseDescription: String) {
        this.databaseId = databaseId
        this.databaseName = databaseName
        this.databaseDescription = databaseDescription
    }
}

class LoginData(user: String, pass: String) {
    var user: String = user
    var password: String = pass
}
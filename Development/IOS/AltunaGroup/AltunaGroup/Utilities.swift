//
//  Utilities.swift
//  Intermovil
//
//  Created by Aldo on 25/08/18.
//  Copyright © 2018 Intermovil. All rights reserved.
//

import Foundation

enum RootScreen {
    case home
    case payments
    case inventory
    case locksmiths
    case none
}

class Utilities {
    static let sharedInstance = Utilities()
    
    var rootScreen = RootScreen.home
    
    //Login
    var isRegister = false
    
    let server = "https://siiges.jalisco.gob.mx"
    
    var screens = [Int]()
    
    var selectedLocksmith: LocksmithItem?
    
    //Push Notifications Screen
    var pushNotificationsScreen = -1
    
    
    
    init(){
    }
    
    
}

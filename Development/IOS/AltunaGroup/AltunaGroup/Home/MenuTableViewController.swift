//
//  MenuTableViewController.swift
//  Intermovil
//
//  Created by Aldo on 26/08/18.
//  Copyright © 2018 Intermovil. All rights reserved.
//

import UIKit
import Alamofire
import SwiftyJSON
import Kingfisher
import SCLAlertView
import CoreData

class MenuTableViewController: UITableViewController {
    
    var userType = 0
    
    @IBOutlet weak var headerBackground: UIView!
    
    @IBOutlet weak var txtCompany: UILabel!
    
    override func viewDidLoad() {
        
        super.viewDidLoad()
        
        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false
        
        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem
        headerBackground.aplicarGradiente(colores: [UIColor(red: 7/255, green: 25/255, blue: 93/255, alpha: 1.0), UIColor(red: 7/255, green: 25/255, blue: 93/255, alpha: 1.0), UIColor(red: 63/255, green: 81/255, blue: 181/255, alpha: 1.0)], puntoInicial: CGPoint.init(x: 0, y: 0), puntoFinal: CGPoint.init(x: 1, y: 1))
    }
    
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        let company = UserDefaults.standard.string(forKey: "Company")
        txtCompany.text = (company?.isEmpty)! ? "Empresa" : company
        
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        if let topController = UIApplication.topViewController() {
            if (topController is MasterViewController) {
                let master = topController as! MasterViewController
                master.viewWillAppear(true)
            }
        }
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        switch (indexPath.section) {
        case 0: //First Section
            switch (indexPath.row) {
            case 0: //Home
                DispatchQueue.main.async {
                    Utilities.sharedInstance.rootScreen = .home
                    self.dismiss(animated: true, completion: nil)
                }
            case 1: //Payments
                DispatchQueue.main.async {
                    let userID = UserDefaults.standard.integer(forKey: "CustomerID")
                    if(userID == -1){
                        SCLAlertView().showWarning("Primero selecciona un cliente", subTitle: "", closeButtonTitle:"OK").setDismissBlock {
                            self.dismiss(animated: true, completion: nil)
                            self.dismiss(animated: true, completion: nil)
                        }
                    } else {
                        Utilities.sharedInstance.rootScreen = .payments
                        self.dismiss(animated: true, completion: nil)
                    }
                }
            case 2: //Inventories
                DispatchQueue.main.async {
                    let userID = UserDefaults.standard.integer(forKey: "CustomerID")
                    if(userID == -1){
                        SCLAlertView().showWarning("Primero selecciona un cliente", subTitle: "", closeButtonTitle:"OK").setDismissBlock {
                            self.dismiss(animated: true, completion: nil)
                            self.dismiss(animated: true, completion: nil)
                        }
                    } else {
                        Utilities.sharedInstance.rootScreen = .inventory
                        self.dismiss(animated: true, completion: nil)
                    }
                }
            case 3: //Locksmith
                DispatchQueue.main.async {
                    Utilities.sharedInstance.rootScreen = .locksmiths
                    self.dismiss(animated: true, completion: nil)
                }
            default: break
            }
        case 1: //Second Section
            switch (indexPath.row) {
            case 0: //Settings
                changeCompany()
            case 1: //Logout
                logout()
            default: break
            }
        default: break
        }
    }
    
    override func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return super.tableView(tableView, heightForRowAt: indexPath)
    }
    
    func logout(){
        
        UserDefaults.standard.set(false, forKey: "LoggedIn")
        
        SCLAlertView().showInfo("Cerrando sesión...", subTitle: "", closeButtonTitle:"OK").setDismissBlock {
            self.dismiss(animated: true, completion: nil)
            self.dismiss(animated: true, completion: nil)
        }
    }
    
    
    func changeCompany(){
        
        let appearance = SCLAlertView.SCLAppearance(
            showCloseButton: false
        )
        let alertView = SCLAlertView(appearance: appearance)
        alertView.addButton("Colombia") {
            UserDefaults.standard.set("Colombia", forKey: "Company")
            self.txtCompany.text = "Colombia"
        }
        alertView.addButton("México") {
            UserDefaults.standard.set("México", forKey: "Company")
            self.txtCompany.text = "México"
        }
        alertView.addButton("Perú") {
            UserDefaults.standard.set("Perú", forKey: "Company")
            self.txtCompany.text = "Perú"
        }
        alertView.addButton("USA") {
            UserDefaults.standard.set("USA", forKey: "Company")
            self.txtCompany.text = "USA"
        }
        alertView.showNotice("Cambiar Empresa", subTitle: "Seleccione la empresa", closeButtonTitle: "Cancelar").setDismissBlock {
            self.dismiss(animated: true, completion: nil)
            
        }
    }
    
    @IBAction func close(_ sender: UIButton) {
        self.dismiss(animated: true, completion: nil)
    }
    
}

extension UIApplication {
    class func topViewController(base: UIViewController? = UIApplication.shared.keyWindow?.rootViewController) -> UIViewController? {
        if let nav = base as? UINavigationController {
            return topViewController(base: nav.visibleViewController)
        }
        if let tab = base as? UITabBarController {
            if let selected = tab.selectedViewController {
                return topViewController(base: selected)
            }
        }
        if let presented = base?.presentedViewController {
            return topViewController(base: presented)
        }
        return base
    }
}


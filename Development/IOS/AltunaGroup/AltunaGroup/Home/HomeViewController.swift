//
//  ViewController.swift
//  AltunaGroup
//
//  Created by administrator on 1/17/19.
//  Copyright © 2019 AltunaGroup. All rights reserved.
//

import UIKit
import SCLAlertView

class HomeViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    
    @IBOutlet weak var bannerH: NSLayoutConstraint!
    //TableView
    @IBOutlet weak var tableView: UITableView!
    //Buttons
    @IBOutlet weak var btnPayment: UIButton!
    @IBOutlet weak var viewPayment: UIView!
    @IBOutlet weak var btnInventory: UIButton!
    @IBOutlet weak var viewInventory: UIView!
    @IBOutlet weak var btnLocksmith: UIButton!
    @IBOutlet weak var viewLocksmith: UIView!
    
    @IBOutlet weak var txtCustomer: UITextField!
    @IBOutlet weak var lblCustomer: UILabel!
    
    
    var customers = [CustomerItem]()
    var customersForSearch = [CustomerItem]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        tableView.dataSource = self
        tableView.delegate = self
        
        tableView.tableFooterView = UIView()
        
        tableView.isHidden = true
        
        bannerH.constant = view.frame.height * 0.35
        
        let tap = UITapGestureRecognizer(target: self, action: #selector(hideKeyboard))
        tap.cancelsTouchesInView = false
        view.addGestureRecognizer(tap)
        
        //UI
        lblCustomer.text = (UserDefaults.standard.string(forKey: "CustomerName")?.isEmpty)! ? "Seleccionar cliente" : UserDefaults.standard.string(forKey: "CustomerName")
        
        //Buttons
        btnPayment.layer.cornerRadius = 4
        viewPayment.layer.borderColor = UIColor.lightGray.cgColor
        viewPayment.layer.borderWidth = 0.5
        viewPayment.layer.cornerRadius = 8
        viewPayment.layer.shadowColor = UIColor.darkGray.cgColor
        viewPayment.layer.shadowOffset = CGSize(width: 0, height: 1)
        viewPayment.layer.shadowOpacity = 1
        viewPayment.layer.shadowRadius = 2.0
        viewPayment.clipsToBounds = false
        viewPayment.layer.masksToBounds = false
        
        let paymentTap = UITapGestureRecognizer(target: self, action: #selector(paymentTapped))
        paymentTap.cancelsTouchesInView = false
        viewPayment.addGestureRecognizer(paymentTap)
        
        btnInventory.layer.cornerRadius = 4
        viewInventory.layer.borderColor = UIColor.lightGray.cgColor
        viewInventory.layer.borderWidth = 0.5
        viewInventory.layer.cornerRadius = 8
        viewInventory.layer.shadowColor = UIColor.darkGray.cgColor
        viewInventory.layer.shadowOffset = CGSize(width: 0, height: 1)
        viewInventory.layer.shadowOpacity = 1
        viewInventory.layer.shadowRadius = 2.0
        viewInventory.clipsToBounds = false
        viewInventory.layer.masksToBounds = false
        
        let inventoryTap = UITapGestureRecognizer(target: self, action: #selector(inventoryTapped))
        inventoryTap.cancelsTouchesInView = false
        viewInventory.addGestureRecognizer(inventoryTap)
        
        btnLocksmith.layer.cornerRadius = 4
        viewLocksmith.layer.borderColor = UIColor.lightGray.cgColor
        viewLocksmith.layer.borderWidth = 0.5
        viewLocksmith.layer.cornerRadius = 8
        viewLocksmith.layer.shadowColor = UIColor.darkGray.cgColor
        viewLocksmith.layer.shadowOffset = CGSize(width: 0, height: 1)
        viewLocksmith.layer.shadowOpacity = 1
        viewLocksmith.layer.shadowRadius = 2.0
        viewLocksmith.clipsToBounds = false
        viewLocksmith.layer.masksToBounds = false
        
        let locksmithTap = UITapGestureRecognizer(target: self, action: #selector(locksmithTapped))
        locksmithTap.cancelsTouchesInView = false
        viewLocksmith.addGestureRecognizer(locksmithTap)
        
        customers.append(CustomerItem(name: "INTERLINE BRANDS", address: "PO BOX 2317 Jacksonville, Florida"))
        customers.append(CustomerItem(name: "MAZDA", address: "Handled St 15 Tennesse, Tennesse"))
        customers.append(CustomerItem(name: "INFINITE SUPPLY", address: "Infinite Loop 8 Dallas, México"))
        customers.append(CustomerItem(name: "QUEEN", address: "Carranza 27 Guadalajara, Jalisco"))
        customers.append(CustomerItem(name: "Guns & Roses", address: "Allende 1234 San Luis, San Luis"))
        customers.append(CustomerItem(name: "Urrea", address: "Cultural 12 Guadalajara, Jalisco"))
        customers.append(CustomerItem(name: "Llaves la cueva", address: "Hidalgo 217 Zapopan, Jalisco"))
        customers.append(CustomerItem(name: "Keystorm", address: "Alcalde 237 Tlaquepaque, Jalisco"))
        customers.append(CustomerItem(name: "La Llave Maestra", address: "BOX 2317 Tlaquepaque, Jalisco"))
        customers.append(CustomerItem(name: "Internazionale", address: "De la Mora 27 Tonalá, Jalisco"))
        
        customersForSearch = customers
    }
    

    @objc func paymentTapped(){
        let userID = UserDefaults.standard.integer(forKey: "CustomerID")
        if(userID == -1){
            SCLAlertView().showWarning("Primero selecciona un cliente", subTitle: "", closeButtonTitle:"OK").setDismissBlock {
                self.dismiss(animated: true, completion: nil)
            }
        } else {
            Utilities.sharedInstance.rootScreen = .payments
            self.parent?.viewWillAppear(true)
        }
        
    }
    
    @objc func inventoryTapped(){
        let userID = UserDefaults.standard.integer(forKey: "CustomerID")
        if(userID == -1){
            SCLAlertView().showWarning("Primero selecciona un cliente", subTitle: "", closeButtonTitle:"OK").setDismissBlock {
                self.dismiss(animated: true, completion: nil)
            }
        } else {
            Utilities.sharedInstance.rootScreen = .inventory
            self.parent?.viewWillAppear(true)
        }
        
    }
    
    @objc func locksmithTapped(){
        Utilities.sharedInstance.rootScreen = .locksmiths
        self.parent?.viewWillAppear(true)
    }
    
    @objc func hideKeyboard(){
        view.endEditing(true)
    }
    
    
    //TextField
    @IBAction func textFieldFocused(_ sender: UITextField) {
        print("focused")
        tableView.isHidden = false
    }
    
    @IBAction func refreshList(_ sender: UITextField) {
        customersForSearch = customers.filter({ (customer) -> Bool in
            let text = txtCustomer.text!.lowercased()
            if text.isEmpty {
                return true
            } else {
                return customer.name.lowercased().contains(text)
            }
        })
        tableView.reloadData()
    }
    
    @IBAction func textFieldNotActive(_ sender: Any) {
        //txtCustomer.text = ""
        //customersForSearch = customers
        //tableView.reloadData()
        //tableView.isHidden = true
    }
    
    //Customers
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return customersForSearch.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "customer") as! CustomerTableViewCell
        
        cell.lblCustomer.text = customersForSearch[indexPath.row].name
        cell.lblAddress.text = customersForSearch[indexPath.row].address
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
        UserDefaults.standard.set(indexPath.row, forKey: "CustomerID")
        UserDefaults.standard.set(customersForSearch[indexPath.row].name, forKey: "CustomerName")
        
        //Update UI
        lblCustomer.text = customersForSearch[indexPath.row].name
        txtCustomer.text = ""
        customersForSearch = customers
        tableView.reloadData()
        tableView.isHidden = true
    }
    
}

class CustomerItem {
    var imgUrl = ""
    var name = ""
    var address = ""
    
    init(name: String, address: String){
        self.name = name
        self.address = address
    }
}

class CustomerTableViewCell: UITableViewCell {
    
    @IBOutlet weak var imgCustomer: NSLayoutConstraint!
    @IBOutlet weak var lblCustomer: UILabel!
    @IBOutlet weak var lblAddress: UILabel!
    
}

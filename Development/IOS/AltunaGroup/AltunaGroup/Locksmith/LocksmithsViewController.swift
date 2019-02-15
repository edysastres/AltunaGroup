//
//  LocksmithsViewController.swift
//  AltunaGroup
//
//  Created by Aldo Hernandez on 2/14/19.
//  Copyright © 2019 AltunaGroup. All rights reserved.
//

import UIKit

class LocksmithsViewController: UIViewController, UITableViewDelegate, UITableViewDataSource, UITextFieldDelegate {
    
    
    //TableView
    @IBOutlet weak var tableView: UITableView!
    
    @IBOutlet weak var txtLocksmith: UITextField!
    
    var locksmiths = [LocksmithItem]()
    var locksmithsForSearch = [LocksmithItem]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        tableView.dataSource = self
        tableView.delegate = self
        
        tableView.tableFooterView = UIView()
        
        txtLocksmith.delegate = self
        
        let tap = UITapGestureRecognizer(target: self, action: #selector(hideKeyboard))
        tap.cancelsTouchesInView = false
        view.addGestureRecognizer(tap)
        
        locksmiths.append(LocksmithItem(name: "INTERLINE BRANDS", address: "PO BOX 2317 Jacksonville, Florida"))
        locksmiths.append(LocksmithItem(name: "MAZDA", address: "Handled St 15 Tennesse, Tennesse"))
        locksmiths.append(LocksmithItem(name: "INFINITE SUPPLY", address: "Infinite Loop 8 Dallas, México"))
        locksmiths.append(LocksmithItem(name: "QUEEN", address: "Carranza 27 Guadalajara, Jalisco"))
        locksmiths.append(LocksmithItem(name: "Guns & Roses", address: "Allende 1234 San Luis, San Luis"))
        locksmiths.append(LocksmithItem(name: "Urrea", address: "Cultural 12 Guadalajara, Jalisco"))
        locksmiths.append(LocksmithItem(name: "Llaves la cueva", address: "Hidalgo 217 Zapopan, Jalisco"))
        locksmiths.append(LocksmithItem(name: "Keystorm", address: "Alcalde 237 Tlaquepaque, Jalisco"))
        locksmiths.append(LocksmithItem(name: "La Llave Maestra", address: "BOX 2317 Tlaquepaque, Jalisco"))
        locksmiths.append(LocksmithItem(name: "Internazionale", address: "De la Mora 27 Tonalá, Jalisco"))
        
        locksmithsForSearch = locksmiths
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        Utilities.sharedInstance.selectedLocksmith = nil
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
        locksmithsForSearch = locksmiths.filter({ (customer) -> Bool in
            let text = txtLocksmith.text!.lowercased()
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
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        view.endEditing(true)
        return false
    }
    
    //Customers
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return locksmithsForSearch.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "locksmith") as! LocksmithTableViewCell
        
        cell.lblLocksmith.text = locksmithsForSearch[indexPath.row].name
        cell.lblAddress.text = locksmithsForSearch[indexPath.row].address
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        Utilities.sharedInstance.selectedLocksmith = locksmiths[indexPath.row]
        self.tabBarController?.selectedIndex = 1
    }
    
}

class LocksmithItem {
    var imgUrl = ""
    var name = ""
    var address = ""
    
    init(name: String, address: String){
        self.name = name
        self.address = address
    }
}

class LocksmithTableViewCell: UITableViewCell {
    
    @IBOutlet weak var imgCustomer: NSLayoutConstraint!
    @IBOutlet weak var lblLocksmith: UILabel!
    @IBOutlet weak var lblAddress: UILabel!
    
}


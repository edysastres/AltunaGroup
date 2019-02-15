//
//  InventoryViewController.swift
//  AltunaGroup
//
//  Created by Aldo Hernandez on 2/13/19.
//  Copyright © 2019 AltunaGroup. All rights reserved.
//

import UIKit

class InventoryViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var tableView: UITableView!
    
    var references = [ReferenceItem]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        tableView.dataSource = self
        tableView.delegate = self
        
        // Do any additional setup after loading the view.
        tableView.tableFooterView = UIView()
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 10
        //return invoices.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        let cell = tableView.dequeueReusableCell(withIdentifier: "reference") as! ReferenceTableViewCell
        //cell.lblDate.text = "Factura generada el " + invoices[indexPath.row].invoiceDate
        //cell.lblDate.text = "Número de Factura: " + invoices[indexPath.row].invoice
        //cell.lblDate.text = "Cliente: " + invoices[indexPath.row].customer
        //cell.lblDate.text = "Cantidad: $" + invoices[indexPath.row].amount
        //cell.lblDate.text = "Folio Fiscal: " + invoices[indexPath.row].certificateNumber
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        performSegue(withIdentifier: "detail", sender: self)
    }
    
    
    /*
     // MARK: - Navigation
     
     // In a storyboard-based application, you will often want to do a little preparation before navigation
     override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
     // Get the new view controller using segue.destination.
     // Pass the selected object to the new view controller.
     }
     */
    
}

class ReferenceItem {
    var imgReference = ""
    var reference = ""
    var weight = ""
    var material = ""
    var price = ""
    
    init(reference: String, weight: String, material: String, price: String){
        self.reference = reference
        self.weight = weight
        self.material = material
        self.price = price
    }
}

class ReferenceTableViewCell: UITableViewCell {
    
    
    @IBOutlet weak var lblReference: UILabel!
    @IBOutlet weak var lblPrice: UILabel!
    
}

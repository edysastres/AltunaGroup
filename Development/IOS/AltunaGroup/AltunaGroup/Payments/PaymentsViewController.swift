//
//  PaymentsViewController.swift
//  AltunaGroup
//
//  Created by Aldo Hernandez on 2/13/19.
//  Copyright © 2019 AltunaGroup. All rights reserved.
//

import UIKit

class PaymentsViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {

    @IBOutlet weak var tableView: UITableView!
    
    var invoices = [InvoiceItem]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        tableView.dataSource = self
        tableView.delegate = self
        
        tableView.tableFooterView = UIView()

        // Do any additional setup after loading the view.
        
        invoices.append(InvoiceItem(invoiceDate: "1 de neero de 2019", invoice: "FV-18/000001", customer: "KWIKSET SUPPLY", amount: "413.00", certificateNumber: "1234123465456"))
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 10
        //return invoices.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        let cell = tableView.dequeueReusableCell(withIdentifier: "invoice") as! InvoiceTableViewCell
        //cell.lblDate.text = "Factura generada el " + invoices[indexPath.row].invoiceDate
        //cell.lblDate.text = "Número de Factura: " + invoices[indexPath.row].invoice
        //cell.lblDate.text = "Cliente: " + invoices[indexPath.row].customer
        //cell.lblDate.text = "Cantidad: $" + invoices[indexPath.row].amount
        //cell.lblDate.text = "Folio Fiscal: " + invoices[indexPath.row].certificateNumber
        return cell
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

class InvoiceItem {
    var imgUrl = ""
    var invoiceDate = ""
    var invoice = ""
    var customer = ""
    var amount = ""
    var certificateNumber = ""
    
    init(invoiceDate: String, invoice: String, customer: String, amount: String, certificateNumber: String){
        self.invoiceDate = invoiceDate
        self.invoice = invoice
        self.customer = customer
        self.amount = amount
        self.certificateNumber = certificateNumber
    }
}

class InvoiceTableViewCell: UITableViewCell {
    
    @IBOutlet weak var imgInvoice: UIImageView!
    @IBOutlet weak var imgStatus: UIImageView!
    
    @IBOutlet weak var lblDate: UILabel!
    @IBOutlet weak var lblInvoice: UILabel!
    @IBOutlet weak var lblCustomer: UILabel!
    @IBOutlet weak var lblAmount: UILabel!
    @IBOutlet weak var lblCertificate: UILabel!
    
    
}

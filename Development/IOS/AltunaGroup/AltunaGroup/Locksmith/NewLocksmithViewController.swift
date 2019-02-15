//
//  NewLocksmithViewController.swift
//  AltunaGroup
//
//  Created by Aldo Hernandez on 2/14/19.
//  Copyright © 2019 AltunaGroup. All rights reserved.
//

import UIKit
import SCLAlertView

class NewLocksmithViewController: UIViewController, UITableViewDelegate, UITableViewDataSource, UINavigationControllerDelegate, UIImagePickerControllerDelegate {
    
    
    @IBOutlet weak var imgPhoto: UIImageView!
    @IBOutlet weak var txtName: UITextField!
    @IBOutlet weak var txtCompany: UITextField!
    
    @IBOutlet weak var tableView: UITableView!
    
    var addresses = [String]()
    var imagePicker: UIImagePickerController!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        
        tableView.dataSource = self
        tableView.delegate = self
        tableView.tableFooterView = UIView()
        
        let tap = UITapGestureRecognizer(target: self, action: #selector(hideKeyboard))
        tap.cancelsTouchesInView = true
        view.addGestureRecognizer(tap)
        
        let cameraTap = UITapGestureRecognizer(target: self, action: #selector(takePhoto))
        cameraTap.cancelsTouchesInView = false
        imgPhoto.addGestureRecognizer(cameraTap)
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        if let locksmith = Utilities.sharedInstance.selectedLocksmith {
            txtName.text = locksmith.name
            txtCompany.text = locksmith.name
        } else {
            //Clear UI
            txtName.text = ""
            txtCompany.text = ""
        }
    }
    
    @objc func hideKeyboard(){
        view.endEditing(true)
    }
    
    @objc func takePhoto(){
        if(UIImagePickerController.isSourceTypeAvailable(.camera)){
            imagePicker =  UIImagePickerController()
            imagePicker.delegate = self
            imagePicker.sourceType = .camera
            present(imagePicker, animated: true, completion: nil)
        } else {
            _ = SCLAlertView().showInfo("Error...", subTitle: "La cámara no esta disponible", closeButtonTitle: "Ok")
        }
    }
    
    //MARK: - Done image capture here
    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey : Any]) {
        imagePicker.dismiss(animated: true, completion: nil)
        imgPhoto.image = info[UIImagePickerController.InfoKey.originalImage] as? UIImage
    }
    
    @IBAction func addAddress(_ sender: UIButton) {
        
        let appearance = SCLAlertView.SCLAppearance(shouldAutoDismiss: false)
        
        let alert = SCLAlertView(appearance: appearance)
        
        var alertViewResponder = SCLAlertViewResponder(alertview: alert)
        
        let txtStreet = alert.addTextField("Calle")
        let txtExtNumber = alert.addTextField("Exterior")
        let txtIntNumber = alert.addTextField("Interior")
        let txtRegion = alert.addTextField("Colonia")
        let txtZIPCode = alert.addTextField("Código Postal")
        txtZIPCode.keyboardType = .numberPad
        let txtCity = alert.addTextField("Ciudad")
        let txtEmail = alert.addTextField("Email")
        txtEmail.keyboardType = .emailAddress
        let txtPhone = alert.addTextField("Teléfono")
        txtPhone.keyboardType = .numberPad
        
        alert.addButton("Guardar") {
            if ((txtStreet.text?.isEmpty)!){
                _ = SCLAlertView().showWarning("Información incompleta...", subTitle: "Ingrese el nombre de la calle", closeButtonTitle:"OK")
            } else if ((txtExtNumber.text?.isEmpty)!){
                _ = SCLAlertView().showWarning("Información incompleta...", subTitle: "Ingrese el número de la calle", closeButtonTitle:"OK")
            } else {
                let address = txtStreet.text! + " " + txtExtNumber.text!
                self.addresses.append(address)
                self.tableView.reloadData()
                alertViewResponder.close()
            }
        }
        alertViewResponder = alert.showInfo("Nueva dirección", subTitle: "Ingrese los datos del domicilio", closeButtonTitle: "Cancelar")
    }
    
    @IBAction func save(_ sender: UIButton) {
        
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return addresses.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "address")
        cell?.textLabel?.text = (indexPath.row + 1).description
        cell?.detailTextLabel?.text = addresses[indexPath.row]
        return cell!
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

//
//  NewLocksmithViewController.swift
//  AltunaGroup
//
//  Created by Aldo Hernandez on 2/14/19.
//  Copyright © 2019 AltunaGroup. All rights reserved.
//

import UIKit

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
    
    @objc func hideKeyboard(){
        view.endEditing(true)
    }
    
    @objc func takePhoto(){
        imagePicker =  UIImagePickerController()
        imagePicker.delegate = self
        imagePicker.sourceType = .camera
        present(imagePicker, animated: true, completion: nil)
    }
    
    //MARK: - Done image capture here
    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey : Any]) {
        imagePicker.dismiss(animated: true, completion: nil)
        imgPhoto.image = info[UIImagePickerController.InfoKey.originalImage] as? UIImage
    }
    
    @IBAction func addAddress(_ sender: UIButton) {
        
        addresses.append("asdasd asdas")
        tableView.reloadData()
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

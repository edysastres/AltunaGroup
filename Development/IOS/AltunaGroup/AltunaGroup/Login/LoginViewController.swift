//
//  LoginViewController.swift
//  Siiga
//
//  Created by Aldo on 22/08/18.
//  Copyright © 2018 SICYT. All rights reserved.
//

import UIKit
import SCLAlertView

class LoginViewController: UIViewController, UITextFieldDelegate {
    
    @IBOutlet weak var loader: UIActivityIndicatorView!
    @IBOutlet weak var txtUser: UITextField!
    @IBOutlet weak var txtPassword: UITextField!
    
    
    
    var rememberCredentials = false
    
    //ALDO
    var activeTextField: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        txtUser.attributedPlaceholder = NSAttributedString(string: "Usuario",attributes: [NSAttributedString.Key.foregroundColor: UIColor.white])
        txtPassword.attributedPlaceholder = NSAttributedString(string: "Contraseña",attributes: [NSAttributedString.Key.foregroundColor: UIColor.white])
        //ALDO
        txtUser.delegate = self
        txtPassword.delegate = self
        
        //ALDO
        let tap = UITapGestureRecognizer(target: self, action: #selector(hideKeyboard))
        view.addGestureRecognizer(tap)
    }

    //ALDO
    override func viewWillAppear(_ animated: Bool) {
        let center = NotificationCenter.default
        center.addObserver(self, selector: #selector(keyboardDidShow(notification:)), name: UIResponder.keyboardWillShowNotification, object: nil)
        center.addObserver(self, selector: #selector(keyboardWillHide(notification:)), name: UIResponder.keyboardWillHideNotification, object: nil)
    }
    
    //ALDO
    override func viewWillDisappear(_ animated: Bool) {
        NotificationCenter.default.removeObserver(self, name: UIResponder.keyboardWillShowNotification, object: nil)
        NotificationCenter.default.removeObserver(self, name: UIResponder.keyboardWillHideNotification, object: nil)
    }
    
    //ALDO
    @objc func hideKeyboard(){
        view.endEditing(true)
    }
    
    //ALDO
    func textFieldDidBeginEditing(_ textField: UITextField) {
        activeTextField = textField
    }
    
    //ALDO
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    //ALDO
    @objc func keyboardDidShow(notification: Notification){
        let info = notification.userInfo! as NSDictionary
        let keyboardSize = (info[UIResponder.keyboardFrameBeginUserInfoKey] as! NSValue).cgRectValue
        let keyboardY = self.view.frame.size.height - keyboardSize.height
        let editingTextFieldY = self.activeTextField.frame.origin.y
        //let offset:CGFloat = self.activeTextField == txtCode ? (60 + 96) : (60 + 16)
        let offset:CGFloat = 156
        
        if(self.view.frame.origin.y >= 0){
            //Checking if the TextField is really hidden behind the keyboard
            if (editingTextFieldY > keyboardY - offset){
                UIView.animate(withDuration: 0.25, delay: 0.0, options: UIView.AnimationOptions.curveEaseIn, animations: {
                    self.view.frame = CGRect(x: 0, y: self.view.frame.origin.y - (editingTextFieldY - (keyboardY - offset)), width: self.view.bounds.width, height: self.view.bounds.height)
                }, completion: nil)
            }
        }
    }
    
    //ALDO
    @objc func keyboardWillHide(notification: Notification){
        UIView.animate(withDuration: 0.25, delay: 0.0, options: UIView.AnimationOptions.curveEaseIn, animations: {
            self.view.frame = CGRect(x: 0, y: 0, width: self.view.bounds.width, height: self.view.bounds.height)
        }, completion: nil)
    }
    
    @IBAction func login(_ sender: UIButton) {
        if((txtUser.text?.isEmpty)! || (txtPassword.text?.isEmpty)!){
            _ = SCLAlertView().showWarning("Advertencia...", subTitle: "Por favor llene los espacios", closeButtonTitle:"OK")
        } else {
            loginAltuna()
        }
    }
    
    func loginAltuna(){
        if Reachability.isConnectedToNetwork(){
            loader.startAnimating()
            SCLAlertView().showNotice("Bienvenido...", subTitle: "", closeButtonTitle:"OK").setDismissBlock {
                self.loader.stopAnimating()
                UserDefaults.standard.set(true, forKey: "LoggedIn")
                self.dismiss(animated: true, completion: nil)
            }
        }else{
            loader.stopAnimating()
            print("Internet Connection not Available!")
            _ = SCLAlertView().showWarning("Error...", subTitle:"Por favor verifica tu conexión a internet e intenta de nuevo más tarde", closeButtonTitle:"OK")
        }
    }
    
    
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}

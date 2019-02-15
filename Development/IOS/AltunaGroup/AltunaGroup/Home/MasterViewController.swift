//
//  MasterViewController.swift
//  Intermovil
//
//  Created by Aldo on 25/08/18.
//  Copyright © 2018 Intermovil. All rights reserved.
//

import UIKit
import SideMenu


class MasterViewController: UIViewController {
    
    
    @IBOutlet weak var childVC: UIView!
    
    var currentScreen = RootScreen.none
    var currentViewController: UIViewController? = nil
    
    
    var button = UIButton.init(type: .custom)

    override func viewDidLoad() {
        
        // Do any additional setup after loading the view.
        
        //UI
        //UIApplication.shared.statusBarView?.backgroundColor = UIColor(red: 13/255, green: 56/255, blue: 99/255, alpha: 1.0)
        
        //navigationController?.navigationBar.barTintColor = UIColor(red: 7/255, green: 41/255, blue: 93/255, alpha: 1.0) //PrimaryDark
        navigationController?.navigationBar.barTintColor = UIColor(red: 63/255, green: 81/255, blue: 181/255, alpha: 1.0)
        navigationController?.navigationBar.titleTextAttributes = [NSAttributedString.Key.foregroundColor: UIColor.white]
        
        //SideMenu
        SideMenuManager.default.menuPushStyle = .replace
        SideMenuManager.default.menuPresentMode = .viewSlideOut
        SideMenuManager.default.menuAllowPushOfSameClassTwice = false
        SideMenuManager.default.menuAnimationBackgroundColor = UIColor(red: 63/255, green: 81/255, blue: 181/255, alpha: 1.0)
        SideMenuManager.default.menuAnimationFadeStrength = 1
        SideMenuManager.default.menuWidth = view.frame.width * 0.8
        
        
        super.viewDidLoad()
        
        
        //set frame
        button.frame = CGRect(x: 0, y: 0, width: 64, height: 64)
        button.imageView?.contentMode = .scaleAspectFit
        
        let barButton = UIBarButtonItem(customView: button)
        //assign button to navigationbar
        let currWidth = barButton.customView?.widthAnchor.constraint(equalToConstant: 40)
        currWidth?.isActive = true
        let currHeight = barButton.customView?.heightAnchor.constraint(equalToConstant: 40)
        currHeight?.isActive = true
        
        self.navigationItem.rightBarButtonItem = barButton
        
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        let loggedIn = UserDefaults.standard.bool(forKey: "LoggedIn")
        if(!loggedIn){
            DispatchQueue.main.async {
                self.performSegue(withIdentifier: "login", sender: self)
            }
        } else {
            
            if(currentScreen != Utilities.sharedInstance.rootScreen){
                removeInactiveViewController(inactiveViewController: currentViewController)
                currentScreen = Utilities.sharedInstance.rootScreen
                var frontTitle = ""
                switch(Utilities.sharedInstance.rootScreen){
                case .home:
                    currentViewController = self.storyboard?.instantiateViewController(withIdentifier: "home") as! HomeViewController
                    frontTitle = "Llaves Altuna de México"
                    //let img = UIImage(named: "IOS01")
                    //addImageToNavigationTitle(img: img!, title: "Noticias")
                    
                case .payments:
                    currentViewController = self.storyboard?.instantiateViewController(withIdentifier: "payments") as! PaymentsViewController
                    frontTitle = "Cobranza - " + UserDefaults.standard.string(forKey: "CustomerName")!
                    
                case .inventory:
                    currentViewController = self.storyboard?.instantiateViewController(withIdentifier: "inventories") as! InventoryViewController
                    frontTitle = "Inventarios - " + UserDefaults.standard.string(forKey: "CustomerName")!
                  
                case .locksmiths:
                    currentViewController = self.storyboard?.instantiateViewController(withIdentifier: "locksmith")
                    frontTitle = "Cerrajeros"
                
                default:
                    print("Other")
                    frontTitle = "Altuna Group"
                }
                self.title = frontTitle
                updateActiveViewController()
            }
            
        }
        
        UserDefaults.standard.set(true, forKey: "Foreground")
    }
    
    func addImageToNavigationTitle(img: UIImage, title: String) {
        
        
        // Only execute the code if there's a navigation controller
        if self.navigationController == nil {
            return
        }
        
        // Create a navView to add to the navigation bar
        let navView = UIView()
        
        // Create the label
        let label = UILabel()
        label.font = UIFont.boldSystemFont(ofSize: 18)
        label.text = " \(title)"
        label.textColor = UIColor.white
        label.sizeToFit()
        label.center = navView.center
        label.textAlignment = NSTextAlignment.center
        
        // Create the image view
        let image = UIImageView()
        image.image = img
        // To maintain the image's aspect ratio:
        let imageAspect = image.image!.size.width/image.image!.size.height
        // Setting the image frame so that it's immediately before the text:
        image.frame = CGRect(x: label.frame.origin.x-label.frame.size.height*imageAspect*1.3, y: label.frame.origin.y*1.3, width: label.frame.size.height*imageAspect*1.3, height: label.frame.size.height*1.3)
        image.contentMode = UIView.ContentMode.scaleAspectFit
        
        // Add both the label and image view to the navView
        navView.addSubview(label)
        navView.addSubview(image)
        
        // Set the navigation bar's navigation item's titleView to the navView
        self.navigationItem.titleView = navView
        
        // Set the navView's frame to fit within the titleView
        navView.sizeToFit()
 
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    private func removeInactiveViewController(inactiveViewController: UIViewController?) {
        if let inactiveVC = inactiveViewController {
            //1. Avisamos al child view controller que vamos a eliminarlo de su content
            inactiveVC.willMove(toParent: nil)
            
            //2. Eliminamos la view del child view controller de la jerarquía de vistas del content
            inactiveVC.view.removeFromSuperview()
            
            //3. Finalizamos la relación entre el content y el child view controller
            inactiveVC.removeFromParent()
        }
    }
    
    private func updateActiveViewController() {
        if let activeVC = currentViewController {
            //1. Comenzamos la relación entre el content y el child view controller
            addChild(activeVC)
            //2. Establecemos el tamaño y posición del child view controller
            activeVC.view.frame = childVC.bounds
            //3. Añadimos la view del child view controller a la jerarquía de vistas del content
            childVC.addSubview(activeVC.view)
            //4. Avisamos al child view controller que hemos realizado el cambio
            activeVC.didMove(toParent: self)
        }
    }
    

}

extension UIView {
    /** Aplica un gradiente a la instancia. Pueden haber 2 o más colores. El sistema de coordenadas del gradiente es como el que se muestra en la siguiente figura.
     
     (0,0)____(1,0)
     |    |
     |    |
     (1,0)----(1,1)
     */
    /// - Parameter colores: Arreglo de los colores usados por el gradiente. Default: blanco y gris
    /// - Parameter ubicaciones: Arreglo conteniendo las ubicaciones de los colores. Default: nil.
    /// - Parameter puntoInicial: CGPoint del punto de inicio del gradiente. The minValue(x = 0, y = 0) maxValue(x = 1, y = 1). DefaultValue(x = 0, y = 1)
    /// - Parameter puntoFinal: CGPoint del punto final del gradiente. The minValue(x = 0, y = 0) maxValue(x = 1, y = 1). DefaultValue(x = 1, y = 0)
    func aplicarGradiente(colores: [UIColor] = [.white,.gray], ubicaciones: [NSNumber]? = nil,puntoInicial: CGPoint = CGPoint(x: 0, y: 1), puntoFinal : CGPoint = CGPoint(x: 1, y: 0)) {
        let gradient: CAGradientLayer = CAGradientLayer()
        gradient.frame = self.bounds
        gradient.name = "gradientLayer"
        gradient.colors = colores.map { $0.cgColor }
        gradient.locations = ubicaciones
        gradient.startPoint = puntoInicial
        gradient.endPoint = puntoFinal
        self.layer.insertSublayer(gradient, at: 0)
    }
}

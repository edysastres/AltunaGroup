package com.altunagroup.AltunaGroup

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_collection_list.*
import kotlinx.android.synthetic.main.fragment_new_locksmith.*
import kotlinx.android.synthetic.main.new_address_dialog.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [NewLocksmithFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [NewLocksmithFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class NewLocksmithFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    private val CAMERA_REQUEST_CODE = 111
    private val REQUEST_GALLERY_CAMERA = 222

    private var locksmithName: String = ""


    lateinit var addresses: ArrayList<String>

    //Dialog
    lateinit var dialog: Dialog
    //Dialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        locksmithName = arguments?.getString("locksmithName","") ?: ""
        return inflater.inflate(R.layout.fragment_new_locksmith, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Dialog
        dialog = Dialog(context)
        btnAddAddress.setOnClickListener {
            showPopup(it)
        }
        //Dialog

        if(locksmithName.isNotEmpty()){
            (activity as LocksmithActivity).title = "Modificar - " + locksmithName
            txtLocksmithName.setText(locksmithName)
            txtCompany.setText(locksmithName)
            addresses = arrayListOf("Avenida de los Maestros, ext 1553, Los sauces, 456789, Jalisco, México","B")
        } else {
            (activity as LocksmithActivity).title = "Nuevo Registro"
            txtLocksmithName.setText("")
            txtCompany.setText("")
            addresses = ArrayList()
        }


        rv_addresses.layoutManager = LinearLayoutManager(context)
        rv_addresses.adapter = AddressAdapter(addresses, context!!)

        rv_addresses.addOnItemClickListener(object: OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                // Your logic
                var popup = PopupMenu(context, view)
                popup.inflate(R.menu.address_menu)
                popup.setOnMenuItemClickListener {
                    when(it.itemId) {
                        R.id.action_delete_address -> {
                            addresses.removeAt(position)
                            rv_addresses.adapter = AddressAdapter(addresses, context!!)
                        }
                        else -> {
                            Toast.makeText(context, "Opción desconocida",Toast.LENGTH_LONG).show()
                        }
                    }
                    true
                }
                popup.show()
            }
        })

        imgLocksmithProfile.setOnClickListener {
            if (Build.VERSION.SDK_INT >= 23) {
                if (ContextCompat.checkSelfPermission(this.context!!, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                        this.context!!, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                        context as Activity,
                        arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_GALLERY_CAMERA)
                } else {
                    openCamera()
                }
            } else {
                openCamera()
            }
        }
    }



    private fun showPopup(view: View){
        dialog.setContentView(R.layout.new_address_dialog)
        var btnCloseDialog = dialog.findViewById<Button>(R.id.btnCancelDialog)
        btnCloseDialog.setOnClickListener {
            dialog.dismiss()
        }
        var btnAddAddress = dialog.findViewById<Button>(R.id.btnSaveDialog)
        btnAddAddress.setOnClickListener {

            //UI
            var txtStreetDialog = dialog.findViewById<EditText>(R.id.txtStreetDialog)
            var txtExtNumberDialog = dialog.findViewById<EditText>(R.id.txtExtNumberDialog)

            if(txtStreetDialog.text.isEmpty()){
                txtStreetDialog.setError("Este campo es requerido")
            } else if (txtExtNumberDialog.text.isEmpty()){
                txtExtNumberDialog.setError("Este campo es requerido")
            } else {
                addresses.add(txtStreetDialog.text.toString() + ", ext " + txtExtNumberDialog.text.toString())
                //Refresh view
                rv_addresses.adapter = AddressAdapter(addresses, context!!)
                dialog.dismiss()
            }
        }
        dialog.show()

    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        //if (intent.resolveActivity(packageManager) != null)
            startActivityForResult(intent, CAMERA_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_GALLERY_CAMERA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                openCamera()
            } else {
                Toast.makeText(context, "Permiso denegado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                CAMERA_REQUEST_CODE -> {

                    val extras = data?.getExtras()
                    val imageBitmap = extras?.get("data") as Bitmap
                    imgLocksmithProfile.setImageBitmap(imageBitmap)

                }
            }
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            //throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NewLocksmithFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewLocksmithFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

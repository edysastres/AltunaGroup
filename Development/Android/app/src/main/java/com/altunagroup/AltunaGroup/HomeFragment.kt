package com.altunagroup.AltunaGroup

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.SearchView
import kotlinx.android.synthetic.main.fragment_home.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [HomeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    var customers = ArrayList<Customer>()
    var customersForSearch = ArrayList<Customer>()

    lateinit var preferencesHelper: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        preferencesHelper = PreferencesHelper(context!!)

        customers.add(Customer("INTERLINE BRANDS", "PO BOX 2317 Jacksonville, Florida"))
        customers.add(Customer("MAZDA", "Handled St 15 Tennesse, Tennesse"))
        customers.add(Customer("INFINITE SUPPLY", "Infinite Loop 8 Dallas, México"))
        customers.add(Customer("QUEEN", "Carranza 27 Guadalajara, Jalisco"))
        customers.add(Customer("Guns & Roses", "Allende 1234 San Luis, San Luis"))
        customers.add(Customer("Urrea", "Cultural 12 Guadalajara, Jalisco"))
        customers.add(Customer("Llaves la cueva", "Hidalgo 217 Zapopan, Jalisco"))
        customers.add(Customer("Keystorm", "Alcalde 237 Tlaquepaque, Jalisco"))
        customers.add(Customer("La Llave Maestra", "BOX 2317 Tlaquepaque, Jalisco"))
        customers.add(Customer("Internazionale", "De la Mora 27 Tonalá, Jalisco"))

        customersForSearch = customers
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewCustomerHome.queryHint = "Cliente:"
        searchViewCustomerHome.isFocusable = false
        searchViewCustomerHome.isIconified = false
        searchViewCustomerHome.clearFocus()

        rv_customers_home.layoutManager = LinearLayoutManager(this.context)
        rv_customers_home.adapter = CustomersAdapter(customers, this.context!!)

        runLayoutAnimation(rv_customers_home)

        txtCustomer.text = if(preferencesHelper.customerName.equals("")) "Seleccione un cliente" else preferencesHelper.customerName

        rv_customers_home.addOnItemClickListener(object: OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                // Your logic
                //Save preferences
                preferencesHelper.customerID = position
                preferencesHelper.customerName = customersForSearch.get(position).name
                txtCustomer.text = customersForSearch.get(position).name
                searchViewCustomerHome.setQuery("",false)
                searchViewCustomerHome.clearFocus()
            }
        })

        searchViewCustomerHome.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                rv_customers_home.visibility = View.VISIBLE
            } else {
                rv_customers_home.visibility = View.GONE
            }
        }

        searchViewCustomerHome.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextChange(text: String?): Boolean {
                //var c = ArrayList<Customer>()
                customersForSearch = customers.filter { customer -> customer.name.contains(text!!,true) } as ArrayList<Customer>
                rv_customers_home.adapter = CustomersAdapter(customersForSearch, context!!)

                //runLayoutAnimation(rv_customers_home)

                return false
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }
        })

        btnMenuPayment.setOnClickListener {
            if (preferencesHelper.customerID == -1) {
                Snackbar.make(view,"Primero elige un cliente de la lista",Snackbar.LENGTH_SHORT).show()
            } else {
                var intent = Intent(context, CollectionListActivity::class.java)
                startActivity(intent)
            }
        }

        cardMenuPayment.setOnClickListener {
            if (preferencesHelper.customerID == -1) {
                Snackbar.make(view,"Primero elige un cliente de la lista",Snackbar.LENGTH_SHORT).show()
            } else {
                var intent = Intent(context, CollectionListActivity::class.java)
                startActivity(intent)
            }
        }

        btnMenuInventory.setOnClickListener {
            if (preferencesHelper.customerID == -1) {
                Snackbar.make(view,"Primero elige un cliente de la lista",Snackbar.LENGTH_SHORT).show()
            } else {
                var intent = Intent(context, ProductListActivity::class.java)
                startActivity(intent)
            }
        }

        cardMenuInventory.setOnClickListener {
            if (preferencesHelper.customerID == -1) {
                Snackbar.make(view,"Primero elige un cliente de la lista",Snackbar.LENGTH_SHORT).show()
            } else {
                var intent = Intent(context, ProductListActivity::class.java)
                startActivity(intent)
            }
        }

        btnMenuLocksmith.setOnClickListener {
            var intent = Intent(context, LocksmithActivity::class.java)
            startActivity(intent)
        }

        cardMenuLocksmith.setOnClickListener {
            var intent = Intent(context, LocksmithActivity::class.java)
            startActivity(intent)
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


    fun runLayoutAnimation(recyclerView: RecyclerView) {
        val context = recyclerView.context
        val controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_from_bottom)
        recyclerView.layoutAnimation = controller
        recyclerView.adapter!!.notifyItemRangeInserted(0, customersForSearch.size - 1)
        recyclerView.scheduleLayoutAnimation()
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
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

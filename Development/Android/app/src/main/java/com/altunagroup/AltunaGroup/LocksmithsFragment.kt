package com.altunagroup.AltunaGroup

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_choose_customer.*
import kotlinx.android.synthetic.main.fragment_locksmiths.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [LocksmithsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [LocksmithsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class LocksmithsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

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
        return inflater.inflate(R.layout.fragment_locksmiths, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var customers = ArrayList<Customer>()
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

        rv_locksmiths.layoutManager = LinearLayoutManager(this.context)
        rv_locksmiths.adapter = CustomersAdapter(customers, this.context!!)

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
         * @return A new instance of fragment LocksmithsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LocksmithsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
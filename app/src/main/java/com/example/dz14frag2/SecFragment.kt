package com.example.dz14frag2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction


class SecFragment : Fragment(),OnFregmentDataListener {
    private lateinit var onFregmentDataListener: OnFregmentDataListener
    private lateinit var redactCountTV:TextView
    private lateinit var redacttextNoteET:TextView
    private lateinit var redactBTN:Button




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        onFregmentDataListener = requireActivity() as OnFregmentDataListener

        val view = inflater.inflate(R.layout.fragment_sec, container, false)
        redactCountTV=view.findViewById(R.id.redactCountTV)
        redacttextNoteET = arguments?.getString("textMes")


        redactBTN.setOnClickListener {

            val value = redacttextNoteET.text
            onData(value.toString())
        }



        return view
    }

    override fun onData(data: String) {
        val bundle = Bundle()
        bundle.putString("newText", data)

        val transaction = this.fragmentManager?.beginTransaction()
        val festFragment = FragmentFest()
        festFragment.arguments = bundle
        transaction?.replace(R.id.festfragment,festFragment)
        transaction?.addToBackStack(null)
        transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction?.commit()
    }
    }



}
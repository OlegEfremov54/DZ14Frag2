package com.example.dz14frag2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class SecFragment : Fragment(),OnFregmentDataListener {
    private lateinit var onFregmentDataListener: OnFregmentDataListener
    private lateinit var redactCountTV:TextView
    private lateinit var redacttextNoteET:TextView




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        onFregmentDataListener = requireActivity() as OnFregmentDataListener

        val view = inflater.inflate(R.layout.fragment_sec, container, false)
        redactCountTV=view.findViewById(R.id.redactCountTV)
        redacttextNoteET=arguments?.getString("textMes")



        return view
    }
r


}
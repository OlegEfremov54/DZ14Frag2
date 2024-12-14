package com.example.dz14frag2

import android.annotation.SuppressLint
import android.icu.text.Transliterator.Position
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction


class SecFragment : Fragment(),OnFragmentDataListener {
    private lateinit var onFragmentDataListener: OnFragmentDataListener
    private lateinit var redactCountTV:TextView
    private lateinit var redactTextNoteET:EditText
    private lateinit var redactBTN:Button
    private var position: Int? = 0
    private var text:String? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("SecFragment", "OnCreateView")
        onFragmentDataListener = requireActivity() as OnFragmentDataListener

        val view = inflater.inflate(R.layout.fragment_sec, container, false)
        redactCountTV=view.findViewById(R.id.redactCountTV)
        redactTextNoteET = view.findViewById(R.id.redactTextNoteET)
        position = arguments?.getInt("position")
        text = arguments?.getString("textMes")


        redactTextNoteET.setText(text)
        redactCountTV.setText(position.toString())




        redactBTN=view.findViewById(R.id.redactBTN)
        redactBTN.setOnClickListener {

            if (redactTextNoteET.text.isEmpty()) {
                return@setOnClickListener
            }

            val value = redactTextNoteET.text.toString()
            val position = redactCountTV.text.toString().toIntOrNull()


            onData(value, position!!)
        }



        return view
    }

    override fun onData(data: String, position: Int) {
        val bundle = Bundle()
        //bundle.putString("newText", data)
        bundle.putString("newText", redactTextNoteET.text.toString())
        bundle.putInt("position", position)


        //bundle.putString("oldText", redactTextNoteET.toString())
        //bundle.putString("index", redactCountTV.toString())

        val transaction = this.fragmentManager?.beginTransaction()
        val festFragment = FragmentFest()
        festFragment.arguments = bundle
        transaction?.replace(R.id.main,festFragment)
        transaction?.addToBackStack(null)
        transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction?.commit()
    }
    }




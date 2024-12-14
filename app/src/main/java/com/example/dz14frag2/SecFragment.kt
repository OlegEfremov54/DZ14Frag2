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

        //Находим и привязываем поля и кнопки
        val view = inflater.inflate(R.layout.fragment_sec, container, false)
        redactCountTV=view.findViewById(R.id.redactCountTV)
        redactTextNoteET = view.findViewById(R.id.redactTextNoteET)
        //Получаем данные из предыдущего фрагмента
        position = arguments?.getInt("position")
        text = arguments?.getString("textMes")

        //Полученные данные заносим в поля
        redactTextNoteET.setText(text)
        redactCountTV.setText((position?.plus(1)).toString())
        //Кнопка Редактировать
        redactBTN=view.findViewById(R.id.redactBTN)
        //Слушатель
        redactBTN.setOnClickListener {

            val value = redactTextNoteET.text.toString()
            onData(value, position!!)
        }
        return view
    }

    //Он Дата для перехода-возврата на Первый фрагмент
    override fun onData(data: String, position: Int) {
        val bundle = Bundle()
       // Передаем данные в бангл
        bundle.putString("newText", redactTextNoteET.text.toString())
        bundle.putInt("position", position)
        //Транзакция
        val transaction = parentFragmentManager.beginTransaction()
        val festFragment = FragmentFest()
        festFragment.arguments = bundle
        transaction?.replace(R.id.festfragment,festFragment)
        transaction?.addToBackStack(null)
        transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction?.commit()
    }
}





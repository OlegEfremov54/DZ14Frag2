package com.example.dz14frag2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import java.util.Date


class FragmentFest : Fragment() {
    private lateinit var onFragmentDataListener: OnFragmentDataListener

    private lateinit var addBTN: Button

    private lateinit var recyclerViewRV: RecyclerView
    private lateinit var generateCountTV: TextView
    private lateinit var textNoteET: EditText

    private val textMess: MutableList<TextMes> = mutableListOf()
    private var count: Int = 1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_fest, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Привязываем кнопку Добавить
        addBTN = view.findViewById(R.id.addBTN)

        //Привязываем поля вывода инфы
        recyclerViewRV = view.findViewById(R.id.recyclerViewRV)
        generateCountTV = view.findViewById(R.id.generateCountTV)
        textNoteET = view.findViewById(R.id.textNoteET)

        //Присваеваем номер сообщению
        generateCountTV.text = "№ $count"
        //Запускаем менеджер
        recyclerViewRV.layoutManager =  LinearLayoutManager(requireContext())
        val adapter = MyAdapter(textMess)
        recyclerViewRV.adapter = adapter

        //Определяем что размеры фиксированные
        recyclerViewRV.setHasFixedSize(true)

        adapter.setOnTextClickListener(object : MyAdapter.OnTextClickListener {
            override fun OnTextClick(textMes: TextMes, position: Int) {

                onFragmentDataListener.onData(textMess[position].text)

            }

        })




        //Кнопка Добавить
        addBTN.setOnClickListener {
            if (textNoteET.text.isEmpty()) {
                return@setOnClickListener
            }
            val text = textNoteET.text.toString()
            val date = Date().toString()
            val checkBoxStart = false
            val textMes = TextMes(count, text, date, checkBoxStart)
            textMess.add(textMes)
            adapter.notifyDataSetChanged()
            count = ((textMess[textMess.size - 1].count) + 1)
            generateCountTV.text = "№ $count"
            textNoteET.text.clear()
        }

    }

    override fun onResume() {
        super.onResume()
        val newText:String? = arguments?.getString("newText")
        if (newText!=null){
            textNoteET.setText(newText)
            val key = arguments?.getString("oldText") ?: return
            val index = search(textMess, key)
            if (index != -1) {
                val newTexMes = TextMes(
                    count = textMess[index].count,
                    text = newText,
                    date = textMess[index].date,
                    checkBoxStart = textMess[index].checkBoxStart
                )
                swap(textMess, index, newTexMes)
            }

            val adapter = MyAdapter(textMess)
            recyclerViewRV.adapter = adapter
            adapter?.notifyDataSetChanged()



        }

    }

    fun swap(textMes: MutableList<TextMes>, index:Int, newTexMes: TextMes){
        textMes.add(index + 1,newTexMes)
        textMes.removeAt(index)

    }

    fun search(textMes: MutableList<TextMes>, key: String): Int {
        return textMes.indexOfFirst { it.text == key }
    }


}
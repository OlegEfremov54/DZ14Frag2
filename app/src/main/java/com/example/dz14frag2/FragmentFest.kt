package com.example.dz14frag2

import android.annotation.SuppressLint

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

    //private val textMess: MutableList<TextMes> = mutableListOf()
    //private var count: Int = 1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_fest, container, false)

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Привязываем кнопку Добавить
        addBTN = view.findViewById(R.id.addBTN)
        //Привязываем поля вывода инфы
        recyclerViewRV = view.findViewById(R.id.recyclerViewRV)
        generateCountTV = view.findViewById(R.id.generateCountTV)
        textNoteET = view.findViewById(R.id.textNoteET)
        //Номер Заметки
        generateCountTV.text = "№ $count"
        //Запускаем менеджер
        recyclerViewRV.layoutManager =  LinearLayoutManager(requireContext())
        val adapter = MyAdapter(textMess)
        recyclerViewRV.adapter = adapter

        //Определяем что размеры фиксированные
        recyclerViewRV.setHasFixedSize(true)

        onFragmentDataListener = requireActivity() as  MainActivity

        //Слушатель нажатия на отдельную Заметку
        adapter.setOnTextClickListener(object : MyAdapter.OnTextClickListener {
            override fun OnTextClick(textMes: TextMes, position: Int) {

                onFragmentDataListener.onData(textMess[position].text, position)
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

// Он Резуме для перезапуска фрагмента после редактирования
    override fun onResume() {
        super.onResume()
        //Получаем данные
        val newText:String? = arguments?.getString("newText")
        val position = arguments?.getInt("position")
        // вносим измененния
        if (newText!=null){
            textMess[position!!].text = newText
            //обновляем список
            recyclerViewRV.adapter?.notifyItemChanged(position)
        }
    }
}
// Тестовые функции для замены Заметки - наверное не пригодятся

    fun swap(textMes: MutableList<TextMes>, index:Int, newTexMes: TextMes){

        textMes.add(index + 1,newTexMes)
        textMes.removeAt(index)

    }

    fun search(textMes: MutableList<TextMes>, key: String): Int {
        return textMes.indexOfFirst { it.text == key }
    }



package com.example.dz14frag2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView

var textMess: MutableList<TextMes> = mutableListOf()
var count: Int = 1

class MainActivity : AppCompatActivity(),OnFragmentDataListener {
    private lateinit var toolbarMain: Toolbar
    private lateinit var addBTN: Button

    private lateinit var recyclerViewRV: RecyclerView
    private lateinit var generateCountTV: TextView
    private lateinit var textNoteET: EditText

    var textMess: MutableList<TextMes> = mutableListOf()
    var count: Int = 1


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.festfragment, FragmentFest())
                .commit()

        }

        //Тулбар
        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        title = "  Мои заметки"
        toolbarMain.subtitle = " Версия 1.Главная страница"
        toolbarMain.setLogo(R.drawable.adresbook2smoll)

    }
    //Инициализация Меню
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.infoMenuMain -> {
                Toast.makeText(
                    applicationContext, "Автор Ефремов О.В. Создан 8.12.2024",
                    Toast.LENGTH_LONG
                ).show()
            }

            R.id.exitMenuMain -> {
                Toast.makeText(
                    applicationContext, "Работа приложения завершена",
                    Toast.LENGTH_LONG
                ).show()
                finishAffinity()
            }
        }
        return super.onOptionsItemSelected(item)
    }

   override fun onData(data: String, index: Int) {
       val bundle=Bundle()
       bundle.putString("textMes", data)
       val transaction = this.supportFragmentManager.beginTransaction()
       val secFragment = SecFragment()
       secFragment.arguments = bundle
       transaction.replace(R.id.festfragment,secFragment)
       transaction.addToBackStack(null)
       transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
       transaction.commit()
    }
}
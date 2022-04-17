package com.example.pr7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.widget.ButtonBarLayout

class MainActivity : AppCompatActivity() {

    lateinit var buttonAdd:Button
    lateinit var buttonDel: Button
    lateinit var editText: EditText
    private lateinit var adapter: ArrayAdapter<String>
    val countries:Array<String> = arrayOf("Страна0", "Страна1", "Страна2")
    lateinit var countriesListView:ListView
    var selected:ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countriesListView = findViewById(R.id.countriesListView)
        buttonAdd = findViewById(R.id.addButton)
        buttonDel = findViewById(R.id.deleteButton)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, countries)

        countriesListView.setAdapter(adapter)

        countriesListView.setOnItemClickListener { adapterView:AdapterView<*>, view1:View, i:Int, l:Long ->
            val selectedItem = countries[i]
            val selection:TextView = findViewById(R.id.selection)
            selection.setText(selectedItem)
        }

        countriesListView.setOnItemClickListener(AdapterView.OnItemClickListener
        { parent, y, position, id ->
            val user = adapter.getItem(position)
            Toast.makeText(applicationContext, user, Toast.LENGTH_SHORT).show()
            if(countriesListView.isItemChecked(position)) selected.add(user.toString())
            else selected.remove(user.toString())
        })
    }

    fun addClick(view: android.view.View) {
        editText = findViewById(R.id.editText)
        if(!editText.text.toString().isEmpty()){
            adapter.add(editText.text.toString())
            editText.setText("")
            adapter.notifyDataSetChanged()
        }
    }
    fun deleteClick(view: android.view.View) {
        for(i in selected.indices){
            adapter.remove(selected[i])
        }
        countriesListView.clearChoices()
        selected.clear()
        adapter.notifyDataSetChanged()
    }
}//спать хочешь?

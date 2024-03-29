package co.edu.uan.listtask

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import android.widget.*
import java.io.FileNotFoundException
import java.io.PrintStream

class MainActivity : AppCompatActivity() {

    var ArrayListTask = ArrayList<String>()
    var adaptador2:ArrayAdapter<String>?=null
    var FILE_NAME: String = "tasks.txt"
    var LIST_VIEW: ListView?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LIST_VIEW = findViewById<ListView>(R.id.ListTask)
        ArrayListTask = readTaskFile()
        configureList()
        LIST_VIEW!!.onItemLongClickListener =  AdapterView.OnItemLongClickListener { parent, view, position, id ->
            ArrayListTask!!.removeAt(position)
            adaptador2!!.notifyDataSetChanged()
            true
        }
    }

    fun wrifeFile() {
        Log.i("CLASE", "Guardando la tarea $editText.text.toString()")
        val output = PrintStream(
            openFileOutput(FILE_NAME, Context.MODE_APPEND))
        output.println(editText.text.toString())
        output.close()
        ArrayListTask = readTaskFile()
        adaptador2!!.notifyDataSetChanged()

    }

    fun AddButtonClick(view: View){
        if(editText.text.toString().isNullOrEmpty()){
            Toast.makeText(this, "Write your task", Toast.LENGTH_SHORT).show()
        }else{
            ArrayListTask.add(editText.text.toString())
            adaptador2!!.notifyDataSetChanged()
        }
    }

    fun configureList(){
        adaptador2 = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ArrayListTask)
        ListTask.adapter = adaptador2
    }

    fun readTaskFile() : ArrayList<String>{
        val inputStream = resources.openRawResource(R.raw.tasks)
        val scan = Scanner(inputStream)
        val tasksArrayList = arrayListOf<String>()
        while (scan.hasNextLine()){
            val line = scan.nextLine()
            tasksArrayList.add(line)
        }
        scan.close()
        return tasksArrayList
    }
}
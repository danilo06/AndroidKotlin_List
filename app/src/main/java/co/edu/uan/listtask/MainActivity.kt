package co.edu.uan.listtask

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.io.PrintStream

class MainActivity : AppCompatActivity() {

    var ArrayListTask = ArrayList<String>()
    var adaptador2:ArrayAdapter<String>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureList()
    }

    fun AddButtonClick(view: View){


        if(editText.text.toString().isNullOrEmpty()){
            Toast.makeText(this, "Write your task", Toast.LENGTH_SHORT).show()
        }else{
            var texto = editText.text.toString()
            //writeFile(texto)
            ArrayListTask.add(texto)
            writeFile()
            //configureList()
        }
    }

    fun writeFile(){
        //Agregar la informacion al arreglo y luego reemplazar el archivo de texto

        adaptador2 = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ArrayListTask)
        ListTask.adapter = adaptador2
    }

    fun configureList(){
        ArrayListTask = readTaskFile()
        var adaptador = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ArrayListTask)
        ListTask.adapter = adaptador
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
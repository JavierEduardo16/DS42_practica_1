package com.example.convertidor

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var conversion: Int = 0
    private var result: Double = 0.0
    private var value: String = ""
    private lateinit var total: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var selectedTemp = findViewById<Spinner>(R.id.spinner)
        var temp = findViewById<EditText>(R.id.editTextText)
        var button = findViewById<Button>(R.id.button)

        total = findViewById<TextView>(R.id.textView)

        if (selectedTemp != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                resources.getStringArray(R.array.spinner)
            )
            selectedTemp.adapter = adapter
            selectedTemp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    Toast.makeText(this@MainActivity, "Option selected: $position", Toast.LENGTH_SHORT).show()
                    conversion = position
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // TODO: Handle this case
                }
            }

            button.setOnClickListener {
                value = temp.text.toString()
                converter(conversion)
            }
        }
    }

    fun converter(_conversion: Int) {
        when (_conversion) {
            0 -> {
                result = (value.toDouble() * 9 / 5) + 32
            }
            1 -> {
                result = value.toDouble() + 273.15
            }
            2 -> {
                result = (value.toDouble() - 32) * 5 / 9
            }
            3 -> {
                result = (value.toDouble() + 459.67) / 1.8
            }
            4 -> {
                result = value.toDouble() - 273.15
            }
            5 -> {
                result = (value.toDouble() - 273.15) * 1.8 + 32
            }
        }
        total.text = result.toString()
    }

}

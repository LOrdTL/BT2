package com.example.bt2

import android.R.layout
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var editTextNumber: EditText? = null
    private var radioEven: RadioButton? = null
    private var radioOdd: RadioButton? = null
    private var radioSquare: RadioButton? = null
    private var buttonShow: Button? = null
    private var listViewResult: ListView? = null
    private var textViewError: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNumber = findViewById<EditText>(R.id.editTextNumber)
        radioEven = findViewById<RadioButton>(R.id.radioEven)
        radioOdd = findViewById<RadioButton>(R.id.radioOdd)
        radioSquare = findViewById<RadioButton>(R.id.radioSquare)
        buttonShow = findViewById<Button>(R.id.buttonShow)
        listViewResult = findViewById<ListView>(R.id.listViewResult)
        textViewError = findViewById<TextView>(R.id.textViewError)

        buttonShow!!.setOnClickListener(View.OnClickListener { showResults() })
    }

    private fun showResults() {
        textViewError!!.visibility = View.GONE // Ẩn lỗi ban đầu
        val inputText = editTextNumber!!.text.toString().trim { it <= ' ' }

        // Kiểm tra nhập hợp lệ
        if (inputText.isEmpty()) {
            textViewError!!.text = "Vui lòng nhập số."
            textViewError!!.visibility = View.VISIBLE
            return
        }

        val n: Int
        try {
            n = inputText.toInt()
            if (n < 0) {
                textViewError!!.text = "Vui lòng nhập số nguyên dương."
                textViewError!!.visibility = View.VISIBLE
                return
            }
        } catch (e: NumberFormatException) {
            textViewError!!.text = "Dữ liệu không hợp lệ."
            textViewError!!.visibility = View.VISIBLE
            return
        }

        val results = ArrayList<String>()


        // Xử lý theo lựa chọn RadioButton
        if (radioEven!!.isChecked) {
            var i = 0
            while (i <= n) {
                results.add(i.toString())
                i += 2
            }
        } else if (radioOdd!!.isChecked) {
            var i = 1
            while (i <= n) {
                results.add(i.toString())
                i += 2
            }
        } else if (radioSquare!!.isChecked) {
            var i = 0
            while (i * i <= n) {
                results.add((i * i).toString())
                i++
            }
        } else {
            textViewError!!.text = "Vui lòng chọn loại số."
            textViewError!!.visibility = View.VISIBLE
            return
        }

        // Hiển thị kết quả trong ListView
        val adapter = ArrayAdapter(this, R.layout.simple_list_item, results)
        listViewResult!!.adapter = adapter
    }
}
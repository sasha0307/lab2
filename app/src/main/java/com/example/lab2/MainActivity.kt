package com.example.lab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.Exception
import androidx.appcompat.app.AlertDialog
import kotlin.math.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun discriminant(a:Double, b: Double, c: Double): Double
    {
        val D : Double = b*b - 4.0 * a * c
        return  D
    }

    fun getResult(view: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Решение")
        try {
            val a = findViewById<TextView>(R.id.a).text.toString().toDouble()
            val b = findViewById<TextView>(R.id.b).text.toString().toDouble()
            val c = findViewById<TextView>(R.id.c).text.toString().toDouble()
            if (a == 0.0 && b == 0.0 && c == 0.0)
            {
                builder.setMessage("Уравнение принимает верное равенство при любых x")
            }
            else if (a == 0.0 && b == 0.0 && c != 0.0)
            {
                builder.setMessage("Тождество не является уравнением")
            }
            else if (a == 0.0 && b != 0.0)
            {
                val x = floor(-c/b * 100.0) / 100.0
                builder.setMessage("Линейное уровнение \n x = $x")
            }
            else
            {
                val d: Double = floor(discriminant(a, b, c) * 100.0) / 100.0
                if (d < 0)
                {
                    builder.setMessage(" Дискриминант: D = $d \n Уравнение не имеет действительных корней!")
                }
                else if (d == 0.0)
                {
                    val x: Double = floor(-b / (2.0 * a) * 100.0) / 100.0
                    builder.setMessage("Дискриминант: D = $d \n Уравнение имеет 2 корня \n x1 = x2 = $x")
                }
                else if(d > 0)
                {
                    val x1 = floor((-b + sqrt(d)) / (2.0 * a) * 100.0) / 100.0
                    val x2 = floor((-b - sqrt(d)) / (2.0 * a) * 100.0) / 100.0
                    builder.setMessage("Дискриминант: D = $d \n Уравнение имеет 2 корня \n x1 = $x1 \n x2 = $x2")
                }
            }
        }
        catch (e: Exception)
        {
            builder.setTitle("Ошибка ввода данных")
            builder.setMessage("Проверьте входные данные!")
        }
        builder.setPositiveButton("Ок"){dialog, which ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.setCancelable(false)
        dialog.show()
    }
}
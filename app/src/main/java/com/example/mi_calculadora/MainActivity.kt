package com.example.mi_calculadora

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var etResultado: EditText
    private var operador: String = ""
    private var primerNumero: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etResultado = findViewById(R.id.Resultado)

        val botonesNumericos = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )

        botonesNumericos.forEach { id ->
            findViewById<Button>(id).setOnClickListener { agregarNumero((it as Button).text.toString()) }
        }

        findViewById<Button>(R.id.btnSumar).setOnClickListener { establecerOperacion("+") }
        findViewById<Button>(R.id.btnRestar).setOnClickListener { establecerOperacion("-") }
        findViewById<Button>(R.id.btnMultiplicar).setOnClickListener { establecerOperacion("*") }
        findViewById<Button>(R.id.btnDividir).setOnClickListener { establecerOperacion("/") }

        findViewById<Button>(R.id.btnIgual).setOnClickListener { calcularResultado() }
        findViewById<Button>(R.id.btnBorrar).setOnClickListener { limpiar() }
    }

    private fun agregarNumero(numero: String) {
        etResultado.append(numero)
    }

    private fun establecerOperacion(op: String){
        if (etResultado.text.isNotEmpty()) {
            primerNumero = etResultado.text.toString().toDouble()
            operador = op
            etResultado.text.clear()
        }
    }
    private fun calcularResultado() {
        if (etResultado.text.isNotEmpty()) {
            val segundoNumero = etResultado.text.toString().toDouble()
            val resultado = when (operador) {
                "+" -> primerNumero + segundoNumero
                "-" -> primerNumero - segundoNumero
                "*" -> primerNumero * segundoNumero
                "/" -> if (segundoNumero != 0.0) primerNumero / segundoNumero else "Error"
                else -> ""
            }
            etResultado.setText(resultado.toString())
        }
    }

    private fun limpiar(){
        etResultado.text.clear()
        operador = ""
        primerNumero = 0.0
    }
}






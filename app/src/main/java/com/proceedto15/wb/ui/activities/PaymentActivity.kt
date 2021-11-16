package com.proceedto15.wb.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.proceedto15.wb.R

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paymentpage)
        val i = intent
        val s = i.getStringExtra("value")
        Log.d("Msg", s.toString())
        actions(s.toString())
    }

    private fun actions(i: String){
        Log.d("Msg", i)
        findViewById<Button>(R.id.efectivo).setOnClickListener{


        }
        findViewById<Button>(R.id.tarjeta).setOnClickListener {
            val intent = Intent(this, CreditCardActivity::class.java)
            intent.putExtra("value", i)
            startActivity(intent)
        }
    }
}
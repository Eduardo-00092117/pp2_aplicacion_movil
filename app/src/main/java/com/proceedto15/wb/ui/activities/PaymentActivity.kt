package com.proceedto15.wb.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.proceedto15.wb.R

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paymentpage)
        actions()
    }

    private fun actions(){
        val bundle = intent.extras
        findViewById<Button>(R.id.efectivo).setOnClickListener{


        }
        findViewById<Button>(R.id.tarjeta).setOnClickListener {
            val intent = Intent(this, )
        }
    }
}
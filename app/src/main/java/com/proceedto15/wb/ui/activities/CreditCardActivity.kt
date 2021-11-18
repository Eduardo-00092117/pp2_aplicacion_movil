package com.proceedto15.wb.ui.activities

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.braintreepayments.cardform.view.CardForm
import com.proceedto15.wb.R

class CreditCardActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_creditcard)
        initView(intent.extras)
    }

    fun initView(i: Bundle?){
        findViewById<Button>(R.id.pay_button).setOnClickListener(paymentClickListener)

        val cardform = findViewById<CardForm>(R.id.cardform)
        cardform.cardholderName(CardForm.FIELD_REQUIRED)
            .cardRequired(true)
            .expirationRequired(true)
            .cvvRequired(true)
            .actionLabel("Purchase")
            .setup(this)
    }

    val paymentClickListener = View.OnClickListener {
        Toast.makeText(this, getString(R.string.payment_product), Toast.LENGTH_LONG).show()
        setResult(Activity.RESULT_OK)
        finish()
    }
}



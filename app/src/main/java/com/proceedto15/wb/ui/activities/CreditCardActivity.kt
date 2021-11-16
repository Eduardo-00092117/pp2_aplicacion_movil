package com.proceedto15.wb.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.braintreepayments.cardform.view.CardForm
import com.proceedto15.wb.R
import com.proceedto15.wb.databinding.ActivityCashBinding
import com.proceedto15.wb.databinding.ActivityPaymentCreditcardBinding

class CreditCardActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_creditcard)
        val i = intent.extras
        initView(i)
        }

        fun initView(i: Bundle?){
            val s = i?.getString("value")
            //Log.d("Msg", i.toString())
            val cardform = findViewById<CardForm>(R.id.cardform)
            cardform.cardholderName(CardForm.FIELD_REQUIRED)
                .cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .actionLabel("Purchase")
                .setup(this)
        }
}



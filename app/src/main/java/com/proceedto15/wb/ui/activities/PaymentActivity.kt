package com.proceedto15.wb.ui.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.proceedto15.wb.R
import com.proceedto15.wb.database.viewmodels.OrdenViewModel
import com.proceedto15.wb.utilities.AppConstants.ACT_FOR_RESULT
import com.proceedto15.wb.utilities.AppConstants.PAYMENT_KEY

class PaymentActivity : AppCompatActivity() {

    private lateinit var ordenViewModel: OrdenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paymentpage)
        ordenViewModel = ViewModelProvider(this).get(OrdenViewModel::class.java)

        actions(intent.getStringExtra(PAYMENT_KEY).toString())
    }

    private fun actions(i: String){
        findViewById<Button>(R.id.efectivo).setOnClickListener{
            Toast.makeText(this, getString(R.string.reserved_products), Toast.LENGTH_LONG).show()
            modifyDB()
            finish()
        }
        findViewById<Button>(R.id.tarjeta).setOnClickListener {
            val intent = Intent(this, CreditCardActivity::class.java)
            intent.putExtra(PAYMENT_KEY, i)
            startActivityForResult(intent, ACT_FOR_RESULT)
        }
    }

    private fun modifyDB(){
        /* ordenViewModel.allPedidos.value?.forEach {
            ordenViewModel.subtractExistence(it.id, it.Qty)
        } */
        ordenViewModel.deleteAllPedido()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode.equals(Activity.RESULT_OK)){
            modifyDB()
            finish()
        }
    }
}
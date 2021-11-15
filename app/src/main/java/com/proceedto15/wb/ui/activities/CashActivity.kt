package com.proceedto15.wb.ui.activities

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.proceedto15.wb.R
import com.proceedto15.wb.databinding.ActivityCashBinding
import com.proceedto15.wb.ui.activities.databinding.ActivityCashBinding

class CashActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityCashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        }


    }


}
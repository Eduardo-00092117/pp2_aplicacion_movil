package com.proceedto15.wb.ui.activities

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth
import com.proceedto15.wb.R
import com.proceedto15.wb.databinding.ActivityMainBinding
import com.proceedto15.wb.utilities.PopulateDB
import com.proceedto15.wb.utilities.Preferences

class MainActivity : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding
    private lateinit var firstTime: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        ifFirstTime()

        if(mAuth.currentUser == null){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_products, R.id.navigation_reservation, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    fun initData(){
        firstTime = Preferences(applicationContext)
        mAuth = FirebaseAuth.getInstance()
    }

    fun ifFirstTime(){
        if(firstTime.firstTime == ""){
            firstTime.firstTime = "1"
            PopulateDB(this).populate()
        }
    }
}
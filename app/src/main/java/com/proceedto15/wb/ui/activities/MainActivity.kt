package com.proceedto15.wb.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
        else if(mAuth.currentUser!!.email == "jsreyes3248@gmail.com"){
            intent = Intent(this, AdminActivity::class.java)
            startActivity(intent)
            finish()
        }

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_products, R.id.navigation_reservation, R.id.navigation_notifications
                //R.id.navigation_products, R.id.navigation_reservation, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.user_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.logout_action -> {
                mAuth.signOut()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.shopping_cart -> {
                val intent = Intent(this, CartActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
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
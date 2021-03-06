package com.proceedto15.wb.ui.activities

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.proceedto15.wb.R
import com.proceedto15.wb.database.entities.Cliente
import com.proceedto15.wb.database.entities.Usuario
import com.proceedto15.wb.database.viewmodels.CitaViewModel
import com.proceedto15.wb.utilities.AppConstants.RC_SIGN_IN

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var loginBtn: Button
    private lateinit var registerBtn: TextView
    private lateinit var googleBtn: TextView
    private lateinit var emailBox: EditText
    private lateinit var pswdBox: EditText
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var citaViewModel: CitaViewModel
    private var user: Usuario = Usuario(0,"","")
    private var client: Cliente = Cliente(0,0,"", "", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initData()
    }

    fun initData(){
        mAuth = FirebaseAuth.getInstance()
        citaViewModel = ViewModelProvider(this).get(CitaViewModel::class.java)
        loginBtn = findViewById(R.id.cirLoginButton)
        registerBtn = findViewById(R.id.registerAction)
        googleBtn = findViewById(R.id.googleAction)
        emailBox = findViewById(R.id.editTextEmail)
        pswdBox = findViewById(R.id.editTextPassword)

        loginBtn.setOnClickListener(loginClickListener)
        registerBtn.setOnClickListener(registerClickListener)
        googleBtn.setOnClickListener(loginWithGoogleClickListener)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.my_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    val loginClickListener = View.OnClickListener {
        //Se obtiene el correo electr??nico y la contrase??a
        val email = emailBox.text.toString()
        val pass = pswdBox.text.toString()

        //Se verifica que ninguno de los campos vengan vacios
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)){
            if(TextUtils.isEmpty(email)) emailBox.error = getString(R.string.not_empty)
            else emailBox.error = null
            if(TextUtils.isEmpty(pass)) pswdBox.error = getString(R.string.not_empty)
            else pswdBox.error = null
            return@OnClickListener
        }

        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo

        //Se verifica que exista conexion a internet
        if(activeNetwork != null && activeNetwork.isConnected){
            mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Toast.makeText(this, getString(R.string.login_successful), Toast.LENGTH_SHORT).show()
                    val intent : Intent
                    if(mAuth.currentUser?.email == "jsreyes3248@gmail.com"){
                        intent = Intent(this, AdminActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else {
                        //Busqueda del usuario en la base de datos
                        /*var temp = citaViewModel.getUsuarioPorEmail(emailBox.text.toString())
                        temp.observe(this, {
                            //insertData(it)
                            var temp2 = citaViewModel.getClientePorUsuario(it.id)
                            temp2.observe(this, {
                                Log.d("username", it.idUsuario.toString() + " "+ it.nombre)
                                val intent = Intent(this, MainActivity::class.java)
                                intent.putExtra("id", it.id)
                                startActivity(intent)
                                finish()
                            })
                        })*/
                        /*Log.d("username", user.usuario + " " + user.id.toString())
                        citaViewModel.allUsuario.observe(this, {
                            it.forEach {
                                Log.d("users", it.id.toString() + " " + it.usuario)
                            }
                        })
                        citaViewModel.allCliente.observe(this, {
                            it.forEach {
                                Log.d("clients", it.id.toString() + " " + it.idUsuario.toString() + " " + it.nombre)
                            }
                        })*/
                        /*var temp2 = citaViewModel.getClientePorUsuario(user.id)
                        temp2.observe(this, {
                            Log.d("user", it.nombre)
                            client = it
                        })*/
                        /*Log.d("Success", "search successful")*/
                        intent = Intent(this, MainActivity::class.java)
                        //intent.putExtra("id", client.id)
                    }
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this, getString(R.string.mail_pass_incorrect), Toast.LENGTH_SHORT).show()
                }
            }
        }
        else{
            Toast.makeText(this, getString(R.string.not_connected), Toast.LENGTH_SHORT).show()
        }
    }

    val registerClickListener = View.OnClickListener {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    val loginWithGoogleClickListener = View.OnClickListener {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.d("LOGIN_F", "Google sign in failed")
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, getString(R.string.google_problem), Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun insertData(data: Usuario){
        user = data
        //Log.d("username", user.id.toString())
    }
}
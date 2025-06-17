package com.example.nit3213finalapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.nit3213finalapp.api.RetrofitClient
import com.example.nit3213finalapp.model.Entity
import com.example.nit3213finalapp.model.LoginRequest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            Log.d("MainActivity", "Login button clicked")
            Toast.makeText(this, "Login button clicked", Toast.LENGTH_SHORT).show()

            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                login(username, password)
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun login(username: String, password: String) {
        val loginRequest = LoginRequest(username, password)

        lifecycleScope.launch {
            try {
                Log.d("MainActivity", "Calling login API...")
                val loginResponse = RetrofitClient.apiService.login(loginRequest)
                Log.d("MainActivity", "Login response received")

                if (loginResponse.isSuccessful) {
                    val keypass = loginResponse.body()?.keypass
                    Log.d("MainActivity", "Keypass: $keypass")

                    if (!keypass.isNullOrEmpty()) {
                        val dashboardResponse = RetrofitClient.apiService.getDashboard(keypass)
                        Log.d("MainActivity", "Dashboard response received")

                        if (dashboardResponse.isSuccessful) {
                            val entityList = dashboardResponse.body()?.entities ?: arrayListOf()

                            Log.d("MainActivity", "Entity list size: ${entityList.size}")

                            Toast.makeText(
                                applicationContext,
                                "Login Success! Items received: ${entityList.size}",
                                Toast.LENGTH_LONG
                            ).show()

                            val intent = Intent(this@MainActivity, DashboardActivity::class.java)
                            intent.putExtra("COURSE_LIST", ArrayList(entityList))  // Explicit cast
                            startActivity(intent)
                            finish()
                        } else {
                            Log.e("MainActivity", "Failed to load dashboard: ${dashboardResponse.code()}")
                            Toast.makeText(
                                applicationContext,
                                "Failed to load dashboard",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Log.e("MainActivity", "Invalid keypass received")
                        Toast.makeText(applicationContext, "Invalid keypass", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.e("MainActivity", "Login failed: ${loginResponse.code()}")
                    Toast.makeText(
                        applicationContext,
                        "Login Failed: ${loginResponse.code()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } catch (e: Exception) {
                Log.e("MainActivity", "Exception: ${e.message}", e)
                Toast.makeText(applicationContext, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}

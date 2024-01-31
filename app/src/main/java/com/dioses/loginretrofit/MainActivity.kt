package com.dioses.loginretrofit

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.dioses.loginretrofit.databinding.ActivityMainBinding
import com.dioses.loginretrofit.retrofit.Loginservice
import com.dioses.loginretrofit.retrofit.UserInfo
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/****
 * Project: Login API REST
 * From: com.cursosant.loginapirest
 * Created by Alain Nicolás Tello on 17/12/23 at 1:10 PM
 * Course: Android Practical with Kotlin.
 * Only on: https://www.udemy.com/course/kotlin-intensivo/
 * All rights reserved 2024.
 * My website: www.alainnicolastello.com
 * All my Courses(Only on Udemy):
 * https://www.udemy.com/user/alain-nicolas-tello/
 ***/
class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.swType.setOnCheckedChangeListener { button, checked ->
            button.text = if (checked) getString(R.string.main_type_login)
            else getString(R.string.main_type_register)

            mBinding.btnLogin.text = button.text
        }

        mBinding.btnLogin.setOnClickListener {
            login()
        }

        mBinding.btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }

    private fun login() {
        val email = mBinding.etEmail.text.toString().trim()
        val password = mBinding.etPassword.text.toString().trim()

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(Loginservice::class.java)

        lifecycleScope.launch {
            try {
                val result = service.loginUser(UserInfo(email, password))
                updateUI("${Constants.TOKEN_PROPERTY}: ${result.token}")
            } catch (e: Exception) {
                (e as? HttpException)?.let {
                    when (it.code()) {
                        400 -> {
                            updateUI(getString(R.string.main_error_server))
                        }

                        else -> {
                            updateUI(getString(R.string.main_error_response))
                        }
                    }
                }
            }
        }

        /*
        service.login(UserInfo(email, password)).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                when (response.code()) {
                    200 -> {
                        val result = response.body()
                        updateUI("${Constants.TOKEN_PROPERTY}: ${result?.token}")
                    }

                    400 -> {
                        updateUI(getString(R.string.main_error_server))
                    }

                    else -> {
                        updateUI(getString(R.string.main_error_response))
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("Retrofit", "Problemas en el servidor.")
            }

        })
        */
    }

    private fun updateUI(result: String) {
        mBinding.tvResult.visibility = View.VISIBLE
        mBinding.tvResult.text = result
    }
}
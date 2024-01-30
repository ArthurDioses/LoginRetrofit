package com.dioses.loginretrofit

import android.app.Application

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
class LoginApplication : Application() {
    companion object{
        lateinit var reqResAPI: ReqResAPI
    }

    override fun onCreate() {
        super.onCreate()

        //Volley
        reqResAPI = ReqResAPI.getInstance(this)
    }
}
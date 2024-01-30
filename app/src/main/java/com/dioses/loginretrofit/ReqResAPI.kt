package com.dioses.loginretrofit

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

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
class ReqResAPI constructor(context: Context) {
    companion object{
        @Volatile
        private var INSTANCE: ReqResAPI? = null

        fun getInstance(context: Context) = INSTANCE?: synchronized(this){
            INSTANCE ?: ReqResAPI(context).also { INSTANCE = it }
        }
    }

    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    fun <T> addToRequestQueue(req: Request<T>){
        requestQueue.add(req)
    }
}
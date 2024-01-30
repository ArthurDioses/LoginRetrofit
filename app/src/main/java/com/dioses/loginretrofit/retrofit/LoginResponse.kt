package com.dioses.loginretrofit.retrofit

data class LoginResponse(var token: String) : SuccessResponse(token)

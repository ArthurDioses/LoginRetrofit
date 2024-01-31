package com.dioses.loginretrofit.retrofit

import com.dioses.loginretrofit.Support
import com.dioses.loginretrofit.User

data class SingleUserResponse(
    val data: User,
    val support: Support
)
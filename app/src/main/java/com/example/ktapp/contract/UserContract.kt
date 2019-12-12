package com.example.ktapp.contract

import com.example.ktapp.model.User
import retrofit2.http.Body
import retrofit2.http.POST


interface UserContract {

    interface View{
        fun onError()
        fun onUserCreated()
    }

    interface Presenter{
        fun addUser(user: User)
        fun onDestroy()
    }

    interface ApiService{
        //@FormUrlEncoded
        @POST("user/add/")
        fun validateUserAndGetDepartments(@Body newUser: User)
    }

}
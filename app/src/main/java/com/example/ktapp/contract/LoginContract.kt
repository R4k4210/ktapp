package com.example.ktapp.contract

import com.example.ktapp.model.Department
import com.example.ktapp.model.LoginRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginContract {

    interface View{
        fun badCredenctials()
        fun emptyEmail()
        fun emptyPassword()
        fun error()
        fun hideProgressBar()
        fun showProgressBar()
        fun goToDepartmentActivity(departments: List<Department?>)
        fun login(email : String, password : String)
    }

    interface Presenter{
        fun validateCredentials(email : String, password : String)
        fun onDestroy()
        fun changeStatus(status: Int)
    }

    interface Model{
        fun loginAndGetDepartments(email : String, password : String)
    }

    interface ApiService{
        //@FormUrlEncoded
        @POST("user/")
        fun validateUserAndGetDepartments(@Body request: LoginRequest): Call<List<Department>>

    }

}
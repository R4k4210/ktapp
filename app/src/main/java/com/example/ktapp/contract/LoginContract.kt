package com.example.ktapp.contract

import com.example.ktapp.model.Department
import retrofit2.Call
import retrofit2.http.Field
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
        @POST("user/")
        fun validateUserAndGetDepartments(@Field("email") email: String, @Field("password") password: String): Call<List<Department>>

    }

}
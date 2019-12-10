package com.example.ktapp.presenter

import android.text.TextUtils
import android.util.Log
import com.example.ktapp.contract.LoginContract
import com.example.ktapp.model.Department
import com.example.ktapp.utils.LoginStatus
import com.example.ktapp.utils.RetrofitApiService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginPresenter (view: LoginContract.View): LoginContract.Presenter{
    private var loginView: LoginContract.View? = view

    override fun validateCredentials(email: String, password: String) {
        loginView?.showProgressBar()

        if(TextUtils.isEmpty(email)){
            changeStatus(LoginStatus.EMPTY_EMAIL())
        }
        if(TextUtils.isEmpty(password)){
            changeStatus(LoginStatus.EMPTY_PASS())
        }
        loginAndGetDepartments(email, password)

    }

    override fun onDestroy() {
        loginView = null
    }

    override fun changeStatus(status: Int) {

        when(status){
            LoginStatus.BAD_CREDENTIALS() -> {
                loginView?.hideProgressBar()
                loginView?.badCredenctials()
            }
            LoginStatus.EMPTY_PASS() -> {
                loginView?.hideProgressBar()
                loginView?.emptyPassword()
            }
            LoginStatus.EMPTY_EMAIL() -> {
                loginView?.hideProgressBar()
                loginView?.emptyEmail()
            }
            else -> {
                loginView?.hideProgressBar()
                loginView?.error()
            }

        }
    }

    private fun loginAndGetDepartments(email: String, password: String) {

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.103:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create<LoginContract.ApiService>(LoginContract.ApiService::class.java)

        service.validateUserAndGetDepartments().enqueue(object : Callback<List<Department>> {
            override fun onResponse(call: Call<List<Department>>, response: Response<List<Department>>) {
                if(response.isSuccessful){
                    val departments = response.body()
                    if(departments != null) {
                        loginView?.hideProgressBar()
                        loginView?.goToDepartmentActivity(departments)
                    }
                }else if(response.code() == 401){
                    changeStatus(LoginStatus.BAD_CREDENTIALS())
                }
            }

            override fun onFailure(call: Call<List<Department>>, t: Throwable) {
                changeStatus(LoginStatus.ERROR())
                t.printStackTrace()
            }
        })
    }

}
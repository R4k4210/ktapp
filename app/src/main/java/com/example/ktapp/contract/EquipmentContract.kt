package com.example.ktapp.contract

import com.example.ktapp.model.Equipment
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

interface EquipmentContract {

    interface View{
        fun showEquipments(equipments: List<Equipment>)
    }

    interface Presenter{
        fun getEquipmentsByDepartments(department: String)
    }

    interface Model{
        fun getQrString(): String
    }

    interface ApiService{
        //@FormUrlEncoded
        @GET("equipment/") //{department}
        fun getEquipmentsByDepartment(/*@Field("department" department: String)*/): Call<List<Equipment>>

    }
}
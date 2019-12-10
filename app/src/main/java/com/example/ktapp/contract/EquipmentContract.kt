package com.example.ktapp.contract

import com.example.ktapp.model.Equipment
import retrofit2.Call
import retrofit2.http.Field
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
        @GET("equipment/{department}")
        fun getEquipmentsByDepartmnt(@Field("department") department: String): Call<List<Equipment>>

    }
}
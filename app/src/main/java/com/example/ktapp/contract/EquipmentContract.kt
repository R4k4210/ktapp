package com.example.ktapp.contract

import com.example.ktapp.model.Equipment
import retrofit2.Call
import retrofit2.http.*

interface EquipmentContract {

    interface View{
        fun showEquipments(equipments: List<Equipment>)
    }

    interface Presenter{
        fun getEquipmentsByDepartments(departmentId: Int)
    }

    interface Model{
        fun getQrString(): String
    }

    interface ApiService{
        @GET("equipment/{departmentId}/")
        fun getEquipmentsByDepartment(@Path("departmentId") depId: Int): Call<List<Equipment>>
    }

}
package com.example.ktapp.contract

import com.example.ktapp.model.AddEquipmentResponse
import com.example.ktapp.model.Equipment
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface AddEquipmentContract {

    interface View{
        fun showFormWithData(equipment: Equipment)
        fun onAddError()
        fun onAddComplete(string: String)
    }

    interface Presenter{
        fun getEquipmentFromQrString(qrString: String)
        fun addNewEquipment(equipment: Equipment, id: Int)
    }

    interface ApiService{
        @POST("equipment/add/{departmentId}")
        fun addNew(@Body equipment: Equipment, @Path("departmentId") id: Int): Call<AddEquipmentResponse>
    }
}
package com.example.ktapp.contract

import com.example.ktapp.model.Equipment
import retrofit2.http.Body
import retrofit2.http.POST

interface AddEquipmentContract {

    interface View{
        fun showFormWithData(equipment: Equipment)
        fun onAddError()
        fun onAddComplete()
    }

    interface Presenter{
        fun getEquipmentFromQrString(qrString: String)
        fun addNewEquipment()
    }

    interface ApiService{
        @POST("equipment/add/")
        fun addNew(@Body equipment: Equipment)
    }
}
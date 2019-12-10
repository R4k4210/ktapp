package com.example.ktapp.presenter

import android.util.Log
import com.example.ktapp.contract.EquipmentContract
import com.example.ktapp.model.Equipment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EquipmentPresenter(view: EquipmentContract.View): EquipmentContract.Presenter{

    private val eqView: EquipmentContract.View = view

    override fun getEquipmentsByDepartments(department: String) {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.103:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create<EquipmentContract.ApiService>(EquipmentContract.ApiService::class.java)
        service.getEquipmentsByDepartmnt(department).enqueue(object: Callback<List<Equipment>> {

            override fun onResponse(call: Call<List<Equipment>>, response: Response<List<Equipment>>) {
                if(response.isSuccessful){
                    var equipments  = response.body()
                    Log.i("Equipments", equipments.toString())
                    if(equipments != null){
                        eqView.showEquipments(equipments)
                    }
                }else if(response.code() == 401){
                    //Mostrar error en la respuesta
                }


            }

            override fun onFailure(call: Call<List<Equipment>>, t: Throwable) {
                //mostrar error en la respuesta
            }

        })
    }


}


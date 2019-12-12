package com.example.ktapp.presenter

import com.example.ktapp.contract.AddEquipmentContract
import com.example.ktapp.model.AddEquipmentResponse
import com.example.ktapp.model.Equipment
import com.example.ktapp.utils.BasePath
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddEquipmentPresenter(view: AddEquipmentContract.View): AddEquipmentContract.Presenter {

    private val mView: AddEquipmentContract.View = view

    override fun getEquipmentFromQrString(qrString: String){
        if(qrString != null && qrString != ""){

            var equipment = Equipment()

            var myListOfString = qrString.split("-")
            equipment.name = myListOfString.get(0)
            equipment.identify = myListOfString.get(1)
            equipment.serial = myListOfString.get(2)
            equipment.serialInt = myListOfString.get(3)
            equipment.brand = myListOfString.get(4)
            equipment.model = myListOfString.get(5)

            mView.showFormWithData(equipment)
        }

    }

    override fun  addNewEquipment(equipment: Equipment, id: Int) {

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BasePath.BASE_PATH())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create<AddEquipmentContract.ApiService>(AddEquipmentContract.ApiService::class.java)
        service.addNew(equipment, id).enqueue(object: Callback<AddEquipmentResponse> {

            override fun onResponse(call: Call<AddEquipmentResponse>, response: Response<AddEquipmentResponse>) {
                if(response.isSuccessful){
                    val mResponse = response.body()
                    if(mResponse != null){
                        mView?.onAddComplete(mResponse.status)
                    }
                }
            }

            override fun onFailure(call: Call<AddEquipmentResponse>, t: Throwable) {
                mView?.onAddError()
            }

        })

    }
}
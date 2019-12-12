package com.example.ktapp.presenter

import com.example.ktapp.contract.AddEquipmentContract
import com.example.ktapp.model.Equipment

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

    override fun addNewEquipment() {

    }
}
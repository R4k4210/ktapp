package com.example.ktapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ktapp.R
import com.example.ktapp.contract.EquipmentContract
import com.example.ktapp.model.Equipment
import com.example.ktapp.presenter.EquipmentPresenter

class EquipmentsActivity : AppCompatActivity(), EquipmentContract.View {

    private var presenter: EquipmentPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_equipments)
        presenter = EquipmentPresenter(this)

        var department = intent.getStringExtra("description")

        presenter?.getEquipmentsByDepartments(department)

    }

    override fun showEquipments(equipments: List<Equipment>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

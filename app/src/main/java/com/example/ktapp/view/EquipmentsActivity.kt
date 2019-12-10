package com.example.ktapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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

        val department = intent.getStringExtra("description")
        Log.i("Equipment Activity", department)
        presenter?.getEquipmentsByDepartments(department)

    }

    override fun showEquipments(equipments: List<Equipment>) {
        Log.i("EquipmentsResponse", equipments.toString())
    }
}

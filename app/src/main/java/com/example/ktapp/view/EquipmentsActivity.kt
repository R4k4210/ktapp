package com.example.ktapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ktapp.R
import com.example.ktapp.adapter.EquipmentRecyclerAdapter
import com.example.ktapp.contract.EquipmentContract
import com.example.ktapp.model.Equipment
import com.example.ktapp.presenter.EquipmentPresenter
import kotlinx.android.synthetic.main.activity_equipments.*

class EquipmentsActivity : AppCompatActivity(), EquipmentContract.View {

    private var presenter: EquipmentPresenter? = null
    private lateinit var equipmentAdapter: EquipmentRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_equipments)

        presenter = EquipmentPresenter(this)
        equipmentAdapter = EquipmentRecyclerAdapter()

        val department = intent.getStringExtra("description")

        presenter?.getEquipmentsByDepartments(department)

    }

    override fun showEquipments(equipments: List<Equipment>) {
        equipmentAdapter.submitList(equipments)
        initReclyclerView()
    }

    fun initReclyclerView(){
        equipment_recycler_view.apply {
            equipment_recycler_view.layoutManager = LinearLayoutManager(this@EquipmentsActivity)
            equipment_recycler_view.adapter = equipmentAdapter
        }
    }

}

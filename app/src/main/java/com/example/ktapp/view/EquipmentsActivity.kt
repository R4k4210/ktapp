package com.example.ktapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
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
    private lateinit var btnScan: Button
    private lateinit var btnAddEq: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_equipments)

        presenter = EquipmentPresenter(this)
        equipmentAdapter = EquipmentRecyclerAdapter()

        val receivedId = intent.getStringExtra("depId")

        presenter?.getEquipmentsByDepartments(receivedId.toInt())

        btnScan = findViewById(R.id.btn_Scan)
        btnScan.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@EquipmentsActivity, ScanQrActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        })

        btnAddEq = findViewById(R.id.btn_addEq)
        btnAddEq.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@EquipmentsActivity, AddEquipmentActivity::class.java)
            intent.putExtra("departmenId", receivedId)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        })
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

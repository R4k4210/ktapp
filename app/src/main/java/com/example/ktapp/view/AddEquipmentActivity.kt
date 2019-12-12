package com.example.ktapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import com.example.ktapp.R
import com.example.ktapp.contract.AddEquipmentContract
import com.example.ktapp.model.Equipment
import com.example.ktapp.presenter.AddEquipmentPresenter

class AddEquipmentActivity : AppCompatActivity(), AddEquipmentContract.View {

    lateinit var btnSubmitEq : Button
    lateinit var name: EditText
    lateinit var identify: EditText
    lateinit var serial: EditText
    lateinit var serialInt: EditText
    lateinit var brand: EditText
    lateinit var model: EditText
    lateinit var admission: DatePicker
    lateinit var egress: DatePicker
    lateinit var quantity: EditText
    lateinit var typeEquipment: EditText
    lateinit var state: EditText

    private var presenter: AddEquipmentPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_equipment)

        //bindings
        btnSubmitEq = findViewById(R.id.btnSubmitEq)
        name = findViewById(R.id.edtName)
        identify = findViewById(R.id.edtId)
        serial = findViewById(R.id.edtSerial)
        serialInt = findViewById(R.id.edtInventory)
        brand = findViewById(R.id.edtBrand)
        admission = findViewById(R.id.edtAdmission)
        egress = findViewById(R.id.edtEgress)
        quantity = findViewById(R.id.edtQuantity)
        typeEquipment = findViewById(R.id.edtType)
        state = findViewById(R.id.edtState)

        presenter = AddEquipmentPresenter(this)

        try {
            val rawQrString = intent.getStringExtra("newQrString")
            if(rawQrString != null){
                presenter?.getEquipmentFromQrString(rawQrString)
            }
        }catch (t: Throwable){
            Log.i("AddEquip", "No Intent Data -> " + t.message)
        }


    }

    override fun showFormWithData(equipment: Equipment) {
        name.setText(equipment.name)
        identify.setText(equipment.identify)
        serial.setText(equipment.serial)
        serialInt.setText(equipment.serialInt)
        brand.setText(equipment.brand)
        model.setText(equipment.model)
    }

    override fun onAddError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAddComplete() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

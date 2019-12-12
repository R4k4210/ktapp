package com.example.ktapp.view

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ktapp.R
import com.example.ktapp.contract.AddEquipmentContract
import com.example.ktapp.model.Equipment
import com.example.ktapp.presenter.AddEquipmentPresenter
import java.util.*


class AddEquipmentActivity : AppCompatActivity(), AddEquipmentContract.View {

    lateinit var btnSubmitEq : Button
    lateinit var name: EditText
    lateinit var identify: EditText
    lateinit var serial: EditText
    lateinit var serialInt: EditText
    lateinit var brand: EditText
    lateinit var model: EditText
    lateinit var admission: EditText
    lateinit var egress: EditText
    lateinit var quantity: EditText
    lateinit var typeEquipment: EditText
    lateinit var state: EditText
    var picker: DatePickerDialog? = null

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
        model = findViewById(R.id.edtModel)
        admission = findViewById(R.id.edtAdmission)
        egress = findViewById(R.id.edtEgress)
        quantity = findViewById(R.id.edtQuantity)
        typeEquipment = findViewById(R.id.edtType)
        state = findViewById(R.id.edtState)

        presenter = AddEquipmentPresenter(this)

        val departmentId = intent.getStringExtra("departmenId")

        try {
            val rawQrString = intent.getStringExtra("newQrString")
            if(rawQrString != null){
                presenter?.getEquipmentFromQrString(rawQrString)
            }
        }catch (t: Throwable){
            Log.i("AddEquip", "No Intent Data -> " + t.message)
        }

        admission.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val cldr: Calendar = Calendar.getInstance()
                val day: Int = cldr.get(Calendar.DAY_OF_MONTH)
                val month: Int = cldr.get(Calendar.MONTH)
                val year: Int = cldr.get(Calendar.YEAR)
                // date picker dialog
                picker = DatePickerDialog(this@AddEquipmentActivity, OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    admission.setText(
                        dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year
                    )
                }, year, month, day
                )
                picker!!.show()
            }
        })

        egress.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val cldr: Calendar = Calendar.getInstance()
                val day: Int = cldr.get(Calendar.DAY_OF_MONTH)
                val month: Int = cldr.get(Calendar.MONTH)
                val year: Int = cldr.get(Calendar.YEAR)
                // date picker dialog
                picker = DatePickerDialog(this@AddEquipmentActivity, OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    egress.setText(
                        dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year
                    )
                }, year, month, day
                )
                picker!!.show()
            }
        })

        btnSubmitEq.setOnClickListener(View.OnClickListener {
            presenter?.addNewEquipment(Equipment(name.text.toString(),
                                                identify.text.toString(),
                                                serial.text.toString(),
                                                serialInt.text.toString(),
                                                brand.text.toString(),
                                                model.text.toString(),
                                                Date(admission.text.toString()),
                                                Date(egress.text.toString()),
                                                quantity.text.toString().toInt(),
                                                typeEquipment.text.toString(),
                                                state.text.toString()), departmentId.toInt())
        })
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
        Toast.makeText(getApplicationContext(), "Could not connect, please try again later.", Toast.LENGTH_SHORT).show()
    }

    override fun onAddComplete(status: String) {
        Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show()
        finish()
    }
}

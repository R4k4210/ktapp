package com.example.ktapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ktapp.R
import com.example.ktapp.adapter.DepartmentRecyclerAdapter
import com.example.ktapp.model.Department
import kotlinx.android.synthetic.main.activity_user_profile.*

class DepartmentsActivity : AppCompatActivity(), DepartmentRecyclerAdapter.OnCardViewListener{

    private lateinit var departmentAdapter: DepartmentRecyclerAdapter
    private var items: List<Department> = ArrayList()
    private lateinit var btnScan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val departments = intent.getSerializableExtra("departments") as? List<Department>
        if (departments != null) {
            items = departments
        }
        Log.i("DepartmentActivity", departments.toString())
        initReclyclerView()
        departmentAdapter.submitList(departments!!)

        btnScan = findViewById(R.id.d_btn_Scan)
        btnScan.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@DepartmentsActivity, ScanQrActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        })

    }

    private fun initReclyclerView(){
        deparetment_recycler_view.apply {
            deparetment_recycler_view.layoutManager = LinearLayoutManager(this@DepartmentsActivity)
            departmentAdapter = DepartmentRecyclerAdapter()
            deparetment_recycler_view.adapter = departmentAdapter
       }

    }

    override fun onCardViewClicked(position: Int) {
        items.get(position)
    }


}
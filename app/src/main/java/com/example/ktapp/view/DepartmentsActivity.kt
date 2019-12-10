package com.example.ktapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ktapp.R
import com.example.ktapp.adapter.DepartmentRecyclerAdapter
import com.example.ktapp.model.Department
import kotlinx.android.synthetic.main.activity_user_profile.*

class DepartmentsActivity : AppCompatActivity(), DepartmentRecyclerAdapter.OnCardViewListener{

    private lateinit var departmentAdapter: DepartmentRecyclerAdapter
    private var items: List<Department> = ArrayList()

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
    }

    private fun initReclyclerView(){
       recycler_view.apply {
           recycler_view.layoutManager = LinearLayoutManager(this@DepartmentsActivity)
           departmentAdapter = DepartmentRecyclerAdapter()
           recycler_view.adapter = departmentAdapter
       }

    }

    override fun onCardViewClicked(position: Int) {
        items.get(position)
    }


}
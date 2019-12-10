package com.example.ktapp.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ktapp.R
import com.example.ktapp.model.Department
import com.example.ktapp.model.Equipment
import com.example.ktapp.view.EquipmentsActivity
import kotlinx.android.synthetic.main.department_card.view.*


class EquipmentRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Equipment> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return EquipmentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.equipment_card, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is EquipmentViewHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    class EquipmentViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        val description: TextView = itemView.view_description

        init{
            itemView.setOnClickListener {
                //Send to Form Activity
                /*
                val description = this.itemView.view_description.text.toString()
                val intent = Intent(itemView.context, EquipmentsActivity::class.java)
                intent.putExtra("description", description)
                itemView.context.startActivity(intent)
                */
            }
        }

        fun bind(equipment: Equipment){
            //Bind Equipment to view
            //description.text = department.description
        }
    }

    fun submitList(equipment: List<Equipment>){
        items = equipment
    }

}
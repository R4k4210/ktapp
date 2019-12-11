package com.example.ktapp.adapter

import android.graphics.Bitmap
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ktapp.R
import com.example.ktapp.model.Equipment
import kotlinx.android.synthetic.main.equipment_card.view.*
import net.glxn.qrgen.android.QRCode
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


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
        val qrImage: ImageView = itemView.qr_image
        val name: TextView = itemView.e_name
        val identify: TextView = itemView.e_id
        val serial: TextView = itemView.e_serial
        val serialInt: TextView = itemView.e_inventory
        val brand: TextView = itemView.e_brand
        val model: TextView = itemView.e_model
        val admission: TextView = itemView.e_admission
        val egress: TextView = itemView.e_egress
        val quantity: TextView = itemView.e_quantity
        val type: TextView = itemView.e_type
        val state: TextView = itemView.e_state

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
            //Generate QrCode Img
            val imgBitmap: Bitmap = QRCode.from(equipment.getQrString()).bitmap()
            qrImage.setImageBitmap(imgBitmap)
            //Bind data
            name.text = equipment.name
            identify.text = equipment.identify
            serial.text = equipment.serial
            serialInt.text = equipment.serialInt
            brand.text = equipment.brand
            model.text = equipment.model
            admission.text = formatDate(equipment.admission)
            egress.text = formatDate(equipment.egress)
            quantity.text = equipment.quantity.toString()
            type.text = equipment.typeEquipment
            state.text = equipment.state
        }

        fun formatDate(date: Date): String{
            val pattern = "yyyy/MM/dd"
            val simpleDateFormat = SimpleDateFormat(pattern)
            var dateStr: String = ""
            if(date != null || !TextUtils.isEmpty(date)){
                dateStr = simpleDateFormat.format(date)
            }else{
                dateStr = "----/--/--"
            }

            return dateStr
        }
    }

    fun submitList(equipment: List<Equipment>){
        items = equipment
    }


}
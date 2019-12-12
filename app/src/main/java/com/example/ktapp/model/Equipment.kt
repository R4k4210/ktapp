package com.example.ktapp.model

import java.io.Serializable
import java.util.*

data class Equipment(
    var name: String,
    var identify: String,
    var serial: String,
    var serialInt: String,
    var brand: String,
    var model: String,
    var admission: Date,
    var egress: Date,
    var quantity: Int,
    var typeEquipment: String,
    var state: String

): Serializable{

    constructor(): this("", "", "", "", "", "", Date(), Date(), 0, "", "")

    fun getQrString(): String{
        return name + "-" +
               identify + "-" +
               serial + "-" +
               serialInt + "-" +
               brand + "-" +
               model
    }

}
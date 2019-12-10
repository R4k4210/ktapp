package com.example.ktapp.model

import java.io.Serializable

data class User(var email : String,
                var username : String,
                var first_name : String,
                var last_name : String,
                var password : String,
                var departments: List<Department>
) : Serializable
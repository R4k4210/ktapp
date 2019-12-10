package com.example.ktapp.utils

class LoginStatus {
    companion object {
        fun SUCESS(): Int = 0
        fun EMPTY_EMAIL(): Int = 1
        fun EMPTY_PASS(): Int = 2
        fun ERROR(): Int = 3
        fun BAD_CREDENTIALS(): Int = 4
    }
}
package com.example.crudbaju

data class User (
    val id : String,
    val nama : String,
    val alamat : String
){
    constructor(): this("","",""){

    }
}
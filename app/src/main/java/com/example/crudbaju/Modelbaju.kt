package com.example.crudbaju

data class Modelbaju (
    val id : String,
    val nama : String,
    val jumlah : Int
){
    constructor(): this("","",0){
    }
}
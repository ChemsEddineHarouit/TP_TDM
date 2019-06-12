package com.example.tp_tdm1.Models

class Pub(numero : Int, description : String){
    val numero = numero
    val description = description

    fun log(): Unit {
        println("------------------------------------------------------------------------------")
        println(this.numero)
        println(this.description)

    }
}
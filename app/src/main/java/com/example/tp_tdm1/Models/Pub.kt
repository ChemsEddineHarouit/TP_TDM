package com.example.tp_tdm1.Models

class Pub(numero : Int, name : String?, description : String?){
    val numero = numero
    val name = name
    val description = description

    fun log(): Unit {
        println("------------------------------------------------------------------------------")
        println(this.numero)
        println(this.name)
        println(this.description)

    }


}
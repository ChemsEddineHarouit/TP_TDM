package com.example.tp_tdm1.Models

class Pub(numero : Int, name : String?, description : String?, imgs : List<Int>?){
    val numero = numero
    val name = name
    val description = description
    val imgs = imgs

    fun log(): Unit {
        println("------------------------------------------------------------------------------")
        println(this.numero)
        println(this.name)
        println(this.description)

    }

    override fun toString(): String {
        return name.toString()
    }


}
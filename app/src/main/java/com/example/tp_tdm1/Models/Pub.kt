package com.example.tp_tdm1.Models

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class Pub(numero : Int, name : String?, description : String?,price : Int, imgs : List<Int>?, tel:String?){
    val numero = numero
    val name = name
    val description = description
    val price = price
    val date = Calendar.getInstance().time
    val imgs = imgs
    val tel = tel

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
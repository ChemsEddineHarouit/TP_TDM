package com.example.tp_tdm1.Controllers

import com.example.tp_tdm1.Models.Pub
import com.example.tp_tdm1.R.drawable.*

class   Controller private constructor(){
    public var pubs = mutableListOf<Pub>()
    //pubImgs and pubDescriptions is the DataBase
    val pubImgs = hashMapOf<Int,ArrayList<Int>>(
        1 to arrayListOf(p1_1, p1_2, p1_3),
        2 to arrayListOf(p2_1, p2_2, p2_3),
        3 to arrayListOf(p3_1),
        4 to arrayListOf(p4_1, p4_2, p4_3, p4_4),
        5 to arrayListOf(p5_1, p5_2),
        6 to arrayListOf(p6_1)
    )
    val pubDescriptions = hashMapOf<Int, List<String>>(
        1 to listOf("Samsung S10", "ceci est la pub 1 achetez svp"),
        2 to listOf("Nikon", "voici la pub 2 venez"),
        3 to listOf("Axa", "regardez la pub 3 et ahetez"),
        4 to listOf("Clio", "wellah regardez la pub 4 ahetez"),
        5 to listOf("Ecole", "prix choc de la pub 5"),
        6 to listOf("Voyage", "Ne ratez pas la pub 6")
    )
    fun addPub(pub : Pub){
        pubs.add(pub)
    }
    fun getPub(index : Int) : Pub {
        return pubs.get(index)
    }

    fun getImagesOfPub(pub:Pub?): ArrayList<Int>? {
        if(pub != null){
            val tag = pub.numero
            return pubImgs.get(tag)
        }
        return null
    }
    private object Holder{
        val INSTANCE= Controller()
    }
    companion object {
        val instance: Controller by lazy { Holder.INSTANCE }
    }

}
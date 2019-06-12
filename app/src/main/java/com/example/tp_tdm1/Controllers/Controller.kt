package com.example.tp_tdm1.Controllers

import com.example.tp_tdm1.Models.Pub
import com.example.tp_tdm1.R.drawable.*

class   Controller private constructor(){
    public var pubs = mutableListOf<Pub>()
    val pubImgs = hashMapOf<Int,ArrayList<Int>>(
        1 to arrayListOf(pub1_1, pub1_2),
        2 to arrayListOf(pub2_1)
    )
    val descriptions = listOf<String>(
        "ceci est la pub 1 achetez svp",
        "voici la pub 2 venez",
        "regardez la pub 3 et ahetez",
        "wellah regardez la pub 4 ahetez",
        "prix choc de la pub 5",
        "Ne ratez pas la pub 6"
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
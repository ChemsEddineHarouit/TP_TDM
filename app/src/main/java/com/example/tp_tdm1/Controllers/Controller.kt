package com.example.tp_tdm1.Controllers

import com.example.tp_tdm1.Models.Pub
import com.example.tp_tdm1.R.drawable.*

class   Controller private constructor(){
    public var pubs = mutableListOf<Pub>()
    val pubImgs = hashMapOf<Int,ArrayList<Int>>(
        1 to arrayListOf(pub1_1, pub1_2),
        2 to arrayListOf(pub2_1)
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
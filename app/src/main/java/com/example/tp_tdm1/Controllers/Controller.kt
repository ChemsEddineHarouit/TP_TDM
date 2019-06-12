package com.example.tp_tdm1.Controllers

import com.example.tp_tdm1.Models.Pub

class   Controller private constructor(){
    public var pubs = mutableListOf<Pub>()
    fun addPub(pub : Pub){
        pubs.add(pub)
    }
    fun getPub(index : Int) : Pub {
        return pubs.get(index)
    }
    private object Holder{
        val INSTANCE= Controller()
    }
    companion object {
        val instance: Controller by lazy { Holder.INSTANCE }
    }

}
package com.example.tp_tdm1.Controllers

import com.example.tp_tdm1.Models.Pub
import com.example.tp_tdm1.R.drawable.*

class   Controller private constructor(){
    public var pubs = mutableListOf<Pub>()
    //pubsImgs and pubDescriptions is the DataBase
    val pubsImgs = hashMapOf<Int,ArrayList<Int>>(
        1 to arrayListOf(p1_1, p1_2, p1_3),
        2 to arrayListOf(p2_1, p2_2),
        3 to arrayListOf(p3_1, p3_2, p3_3, p3_4),
        4 to arrayListOf(p4_1),
        5 to arrayListOf(p5_1, p5_2, p5_3, p5_4),
        6 to arrayListOf(p6_1, p6_2)
    )
    val pubDescriptions = hashMapOf<Int, List<String>>(
        1 to listOf("Villa moderne F3 Kouba", "Très jolie villa, je la mets en vente par besoin d'agent. Attention on a découvert un fantome dedans"),
        2 to listOf("3 Villas Tlemcen", "3 Villas avec piscine, très cher. On veut vous arnaquer"),
        3 to listOf("Appartement F4 Oued Semar", "Besoin d'un séjour à Oued Semar? Un appartement au dessus de Hamza. Un complet poulet vous est offert!"),
        4 to listOf("Dar el Badji F3", "El badji habitait cette maison, câlme et pas chère"),
        5 to listOf("Villa F6 Bordj", "Villa mebnya ki lkwava, ghir siman. Wer9a mates3a"),
        6 to listOf("Villa à Hassi Messaoud F8", "Vous êtes sérieux? vous avez cru que c'était à Hassi Messaoud?")
    )

    val pubPrices = hashMapOf(
        1 to 120000.toFloat(),
        2 to 150000.toFloat(),
        3 to 100000.toFloat(),
        4 to 180000.toFloat(),
        5 to 200000.toFloat(),
        6 to 50000.toFloat()

    )

    fun addPub(pub : Pub){
        pubs.add(pub)
    }
    fun getPub(index : Int) : Pub? {
        for (pub in pubs){
            if (pub.numero == index) return pub
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
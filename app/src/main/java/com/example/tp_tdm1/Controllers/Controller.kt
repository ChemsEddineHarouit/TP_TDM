package com.example.tp_tdm1.Controllers

import com.example.tp_tdm1.Models.Pub
import com.example.tp_tdm1.R.drawable.*

class   Controller private constructor(){
    public var pubs = mutableListOf<Pub>()
    //pubsImgs and pubDescriptions is the DataBase
    val pubsImgs = hashMapOf<Int,ArrayList<Int>>(
        1 to arrayListOf(p1_1, p1_2, p1_3),
        2 to arrayListOf(p2_1, p2_2, p2_3),
        3 to arrayListOf(p3_1),
        4 to arrayListOf(p4_1, p4_2, p4_3, p4_4),
        5 to arrayListOf(p5_1, p5_2),
        6 to arrayListOf(p6_1)
    )
    val pubDescriptions = hashMapOf<Int, List<String>>(
        1 to listOf("Samsung S10", "Pour le 10è anniversaire, Samsung nous offre son dernier Smartphone qui côute un rein et 1/2 poumon, avec les fonctionnalités qu'il contient ça en vaut la peine :D"),
        2 to listOf("Nikon", "Dernier appareil à photo qui peut détecter les poils de vos nez, une résolution insroyablement croissante comme un pain au chocolat"),
        3 to listOf("Axa", "AXA est un groupe international français spécialisé dans l'assurance depuis sa création, et dans la gestion d'actifs depuis 1994. La marque AXA est la première marque mondiale d’assurance pour la 10ᵉ année consécutive en 2018."),
        4 to listOf("Clio", "La Renault Clio est une gamme d'automobile polyvalente du constructeur français Renault qui nou dévoile la 5è version de son oeuvre"),
        5 to listOf("Ecole", "Les écoles privées sont des écoles qui ne sont pas administrées par leur gouvernement local, étatique ou national et qui conservent donc le droit de pratiquer la religion"),
        6 to listOf("Voyage", "Un voyage pour famille dans des hotels 5 étoiles, Piscine, Spa, khoudra et même à oued semar. Composez le 000")
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
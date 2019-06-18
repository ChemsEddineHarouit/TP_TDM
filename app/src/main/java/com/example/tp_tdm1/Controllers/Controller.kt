package com.example.tp_tdm1.Controllers

import android.net.Uri
import android.widget.Toast
import com.example.tp_tdm1.Models.Pub
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class   Controller private constructor(){
    public var pubs = ArrayList<Pub>()

    fun  uriOf(str : String): Uri{
        return Uri.parse("android.resource://com.example.tp_tdm1/drawable/$str")
    }

    fun initPubs(){

        pubs = arrayListOf<Pub>()

        val dateFormat = SimpleDateFormat("dd MMMM yyyy à hh:mm")

        var pub = Pub(1, "3 Villas Tlemcen",
                "3 Villas avec piscine, très cher. On veut vous arnaquer",
                120000, arrayListOf(uriOf("p1_1"), uriOf("p1_2"), uriOf("p1_3")),
                "054 065 1804", dateFormat.format(Calendar.getInstance().time))
        addPub(pub)

        pub = Pub(2, "Villa à Hassi Messaoud F8",
                "Vous êtes sérieux? vous avez cru que c'était à Hassi Messaoud?",
                150000, arrayListOf(uriOf("p2_1"), uriOf("p2_2")),
                "054 197 3796", dateFormat.format(Calendar.getInstance().time))
        addPub(pub)

        pub = Pub(3, "Villa moderne F3 Kouba",
                "Très jolie villa, je la mets en vente par besoin d'agent. Attention on a découvert un fantome dedans",
                100000, arrayListOf(uriOf("p4_1")),
                "077 999 5317", dateFormat.format(Calendar.getInstance().time))
        addPub(pub)

        pub = Pub(4, "Villa F6 Bordj",
                "Villa mebnya ki lkwava, ghir siman. Wer9a mates3a",
                    180000, arrayListOf(uriOf("p3_1"), uriOf("p3_2"), uriOf("p3_3"), uriOf("p3_4")),
                "066 201 9594", dateFormat.format(Calendar.getInstance().time))
        addPub(pub)

        pub = Pub(5, "Appartement F4 Oued Semar",
                "Besoin d'un séjour à Oued Semar? Un appartement au dessus de Hamza. Un complet poulet vous est offert!",
                200000, arrayListOf(uriOf("p5_1"), uriOf("p5_2"), uriOf("p5_3"), uriOf("p5_4")),
                "055 167 7927", dateFormat.format(Calendar.getInstance().time))
        addPub(pub)

        pub = Pub(6, "Dar el Badji F3",
                "El badji habitait cette maison, câlme et pas chère",
                50000, arrayListOf(uriOf("p6_1"), uriOf("p6_2")),
            "055 415 4437", dateFormat.format(Calendar.getInstance().time))
        addPub(pub)
    }

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
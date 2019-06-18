package com.example.tp_tdm1.Models


import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import java.util.*

class Pub(numero : Int, name : String?, description : String?,price : Int?, imgs : List<Uri>?, tel:String?, date:String) : Parcelable{



    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, p1: Int) {
        dest?.writeInt( numero)
        dest?.writeString( name)
        dest?.writeString( description)
//        dest?.writeInt( price)
        dest?.writeStringList( imgs?.map { it.toString() } )
        dest?.writeString( tel)
        dest?.writeString ( date)
    }

    val numero = numero
    val name = name
    val description = description
    val price = price
    val date = date
    val imgs = imgs
    val tel = tel

    constructor(parcel: Parcel) : this(
        parcel.readInt(), // numero
        parcel.readString(), // name
        parcel.readString(), // desc
        parcel.readInt(), // price
        parcel.readArrayList(null).map { Uri.parse(it.toString()) }, //imgs
        parcel.readString(), // tel
        parcel.readString() // date
        )


    fun log(): Unit {
        println("------------------------------------------------------------------------------")
        println(this.numero)
        println(this.name)
        println(this.description)

    }

    override fun toString(): String {
        return name.toString()
    }

    companion object CREATOR : Parcelable.Creator<Pub> {
        override fun createFromParcel(parcel: Parcel): Pub {
            return Pub(parcel)
        }

        override fun newArray(size: Int): Array<Pub?> {
            return arrayOfNulls(size)
        }
    }


}
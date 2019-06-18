package com.example.tp_tdm1.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.tp_tdm1.Models.Pub
import com.example.tp_tdm1.R
import java.text.SimpleDateFormat


class PubAdapter(var pubList: List<Pub>): RecyclerView.Adapter<PubAdapter.ViewHolder>(), Filterable {

    var pubSearchList : List<Pub>? = null
    var allPubs = pubList



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pub = pubList[position]
        holder?.name?.text = pub.name
        holder?.description?.text = pub.description
        val img_id = pub.imgs?.first()

//        println("${pub.name} ++++++++ ${img_id.toString()}")

        if(img_id != null)  holder.img?.setImageURI(img_id)
        holder?.img?.setTag(pub.numero)

//        holder.price.text = "Prix: ${pub.price}"
//        holder.date.text = "Date: ${pub.date}"
        holder.price.text = "${pub.price} DA"
//        val dateFormat = SimpleDateFormat("dd MMMM yyyy à hh:mm")
        holder.date.text = "Ajouté le: ${pub.date}"
        holder.tel.text = "Tél: ${pub.tel}"

        //pubList.sortedWith(compareBy({it.name}))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.row_pub, parent, false)
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return pubList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.pubRowName)
        val description = itemView.findViewById<TextView>(R.id.pubRowDesc)
        val img = itemView.findViewById<ImageView>(R.id.pubRowImg)
        val price = itemView.findViewById<TextView>(R.id.pubRowPrice)
        val date = itemView.findViewById<TextView>(R.id.pubRowDate)
        val tel = itemView.findViewById<TextView>(R.id.pubRowTel)
    }

    fun addPub(pub: Pub) {
        var newList = arrayListOf<Pub>()

        for (p in pubList){
            newList.add(p)
        }
        newList.add(pub)

        pubList = newList
        pubSearchList = newList
        allPubs = newList
        notifyDataSetChanged()
    }

    fun sort(attr : String, asc :Boolean = true) : Boolean{

        when (attr) {
            "name" -> {
                pubList = pubList.sortedWith(compareBy {it.name})
            }
            "price" -> {
                pubList = pubList.sortedWith(compareBy {it.price})
            }
            "date" -> {
                pubList = pubList.sortedWith(compareBy {it.date})
            }
        }

        if (!asc){
            pubList = pubList.reversed()
        }
        notifyDataSetChanged()
        return true
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    pubSearchList = allPubs
                } else {
                    val filteredList = ArrayList<Pub>()
                    for (pub in allPubs) {
                        if (pub.name!!.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(pub)
                        }
                    }
                    pubSearchList = filteredList
                }
                val filterResults = Filter.FilterResults()
                filterResults.values = pubSearchList
                return filterResults
            }
            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
                pubSearchList = filterResults.values as ArrayList<Pub>
                println("---------------------------- $pubSearchList")
                if(pubSearchList != null) pubList = pubSearchList!!
                else                        pubList = allPubs
                notifyDataSetChanged()
            }
        }
    }


}

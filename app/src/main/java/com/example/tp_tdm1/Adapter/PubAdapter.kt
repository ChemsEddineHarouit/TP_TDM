package com.example.tp_tdm1.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import com.example.tp_tdm1.Models.Pub
import com.example.tp_tdm1.R


class PubAdapter(var pubList: List<Pub>): RecyclerView.Adapter<PubAdapter.ViewHolder>(), Filterable {

    var pubSearchList : List<Pub>? = null
    val allPubs = pubList



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pub = pubList[position]
        holder?.name?.text = pub.name
        holder?.description?.text = pub.description
        val img_id = pub.imgs?.first()
        if(img_id != null)  holder?.img?.setImageResource(img_id)
        holder?.img?.setTag(pub.numero)
        holder.price.text = "Prix: ${pub.price}"
        holder.date.text = "Prix: ${pub.date}"
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

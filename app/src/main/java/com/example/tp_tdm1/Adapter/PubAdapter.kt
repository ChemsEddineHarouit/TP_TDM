package com.example.tp_tdm1.Adapter

import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.tp_tdm1.Fragments.Pub_fragment
import com.example.tp_tdm1.MainActivity
import com.example.tp_tdm1.Models.Pub
import com.example.tp_tdm1.R
import java.security.AccessController.getContext



class PubAdapter(val pubList: ArrayList<Pub>): RecyclerView.Adapter<PubAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pub = pubList[position]
        holder?.name?.text = pub.name
        val img_id = pub.imgs?.first()
        if(img_id != null)  holder?.img?.setImageResource(img_id)
        holder?.img?.setTag(pub.numero)
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
        val img = itemView.findViewById<ImageView>(R.id.pubRowImg)

    }
}

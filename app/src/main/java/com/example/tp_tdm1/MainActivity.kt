package com.example.tp_tdm1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.tp_tdm1.Adapter.PubAdapter
import com.example.tp_tdm1.Controllers.Controller
import com.example.tp_tdm1.Fragments.Pub_fragment
import com.example.tp_tdm1.Models.Pub
import com.example.tp_tdm1.R.drawable.*


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class MainActivity : AppCompatActivity()  {

    lateinit var pubFragment : Pub_fragment
    lateinit var pubAdapter : PubAdapter

    val GET_FORM_DATA = 10


    override fun onCreate(savedInstanceState: Bundle?) {
        val controller = Controller.instance
//        Toast.makeText(this, "entered", Toast.LENGTH_SHORT).show()


        controller.initPubs()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        add_pub.setOnClickListener { view ->
            val intent = Intent(this, AddPub::class.java)
            //sauvegardi l' etat
            startActivityForResult(intent, GET_FORM_DATA)
        }

        pubsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false) as RecyclerView.LayoutManager?

        pubAdapter = PubAdapter(ArrayList(controller.pubs))
        pubsRecyclerView.adapter = pubAdapter
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode != GET_FORM_DATA || resultCode != RESULT_OK) {
//            // didn't pick image
//            Toast.makeText(this, "Veuillez selectionner des données", Toast.LENGTH_SHORT).show()
//            return
//        }
//
//
//        val name = data?.getStringExtra("name")
//        val description = data?.getStringExtra("description")
//        val price = data?.getIntExtra("price", 50000)
//
//        val tel = data?.getStringExtra("tel")
//        val imgsStr = data?.getStringArrayListExtra("uris")
//
//        if(name == null) return
//        if(imgsStr?.size == 0){
//            Toast.makeText(this, "no pics", Toast.LENGTH_LONG).show()
//            return
//        }
//
//        val imgs = imgsStr?.map { Uri.parse(it) }
//
//        Toast.makeText(this, imgs?.get(0).toString(), Toast.LENGTH_LONG).show()
//
//        val dateFormat = SimpleDateFormat("dd MMMM yyyy à hh:mm")
//        var controller = Controller.instance
//
//        val pub = Pub(controller.pubs.size + 1, name, description, price, imgs, tel, dateFormat.format(Calendar.getInstance().time))
//
////        controller.addPub(pub)
////        pubAdapter.addPub(pub)
//        pubAdapter = PubAdapter(ArrayList(controller.pubs))
//        pubsRecyclerView.adapter = pubAdapter
//
//    }

    override fun onPause() {
        super.onPause()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)

        val pubs = savedInstanceState?.getParcelableArray("pubs")

        Toast.makeText(this, pubs?.size.toString(), Toast.LENGTH_LONG).show()
    }
    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        //outState.putStringArrayList()

        outState?.putParcelableArray("pubs", pubAdapter.pubList.toTypedArray())

    }

    override fun onResume() {
        super.onResume()

        val name = intent.getStringExtra("name")
        val description = intent.getStringExtra("description")
        val price = intent.getStringExtra("price")
        val tel = intent.getStringExtra("tel")
        val imgsStr = intent.getStringArrayListExtra("uris")

        if(name == null) return
        if(imgsStr.size == 0){
            Toast.makeText(this, "no pics", Toast.LENGTH_LONG).show()
            return
        }

        val imgs = imgsStr.map { Uri.parse(it) }


        val dateFormat = SimpleDateFormat("dd MMMM yyyy à hh:mm")
        var controller = Controller.instance

        val pub = Pub(controller.pubs.size + 1, name, description, price.toInt(), imgs, tel, dateFormat.format(Calendar.getInstance().time))

        controller.addPub(pub)
        pubAdapter.addPub(pub)
//        pubAdapter = PubAdapter(ArrayList(controller.pubs))
//        pubsRecyclerView.adapter = pubAdapter
//        Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        search_view.queryHint = "Votre Recherche"

        search_view.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextChange(query: String): Boolean {
                pubAdapter!!.filter.filter(query)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                pubAdapter!!.filter.filter(query)
                return false
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        when (item.itemId) {
            R.id.action_sort_price_desc -> {
                pubAdapter.sort("price", asc = false)
            }
            R.id.action_sort_price_asc -> {
                pubAdapter.sort("price")
            }
            R.id.action_sort_date_desc -> {
                pubAdapter.sort("date", asc = false)
            }
            R.id.action_sort_date_asc -> {
                pubAdapter.sort("date")
            }
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }





    public fun pubClick(view : View): Unit {
        var tag :Int = view.getTag() as Int
        val position= tag
        pubFragment = Pub_fragment.newInstance(position)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, pubFragment)
            .addToBackStack(pubFragment.toString())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()


    }
}

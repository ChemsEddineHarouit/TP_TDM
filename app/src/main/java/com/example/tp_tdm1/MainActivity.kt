package com.example.tp_tdm1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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
import kotlin.random.Random

class MainActivity : AppCompatActivity()  {

    lateinit var pubFragment : Pub_fragment
    lateinit var pubAdapter : PubAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        val controller = Controller.instance
        controller.initPubs()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        add_pub.setOnClickListener { view ->
            val intent = Intent(this, AddPub::class.java)
            startActivity(intent)
        }

        pubsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false) as RecyclerView.LayoutManager?

        pubAdapter = PubAdapter(ArrayList(controller.pubs))
        pubsRecyclerView.adapter = pubAdapter
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)
    }
    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        //outState.putStringArrayList()

        val names = ArrayList<String>()
        val descs = ArrayList<String>()
        val prices = ArrayList<String>()
        

    }

    override fun onResume() {
        super.onResume()

        val name = intent.getStringExtra("name")
        val description = intent.getStringExtra("description")
        val price = intent.getStringExtra("price")
        val imgsStr = intent.getStringArrayListExtra("uris")

        if(name == null) return
        if(imgsStr.size == 0){
            Toast.makeText(this, "no pics", Toast.LENGTH_LONG).show()
            return
        }

        val imgs = imgsStr.map { Uri.parse(it) }



        var controller = Controller.instance

        val pub = Pub(controller.pubs.size + 1, name, description, price.toInt(), imgs, "0556 27 64 61")

        controller.addPub(pub)
        pubAdapter.addPub(pub)
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
//
//private fun android.widget.SearchView.setOnQueryTextListener() {
//    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//}

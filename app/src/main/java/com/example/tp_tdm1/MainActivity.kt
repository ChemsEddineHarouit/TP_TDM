package com.example.tp_tdm1

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.Snackbar
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

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity()  {

    lateinit var pubFragment : Pub_fragment
    lateinit var pubAdapter : PubAdapter


    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)


        outState.putStringArrayList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val controller = Controller.instance
        val pubDescriptions = controller.pubDescriptions
        val pubsImgs = controller.pubsImgs
        val pubPrices = controller.pubPrices

        for (pubNum in pubDescriptions.keys){
            val pubName = pubDescriptions.get(pubNum)?.get(0)
            val pubDescription = pubDescriptions.get(pubNum)?.get(1)
            val price = pubPrices.get(pubNum) as Float

            var pub : Pub = Pub(pubNum, pubName, pubDescription, price, pubsImgs.get(pubNum))
//            val controller = Controller.instance
            controller.addPub(pub)

            // to test sort by date: else : all pubs will have the same date
            //Thread.sleep(500)
        }
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


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        search_view.setQueryHint("Votre Recherche")

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

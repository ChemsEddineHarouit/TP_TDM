package com.example.tp_tdm1

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.tp_tdm1.Controllers.Controller
import com.example.tp_tdm1.Fragments.Pub_fragment
import com.example.tp_tdm1.Models.Pub

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_pub.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity()  {

    lateinit var pubFragment : Pub_fragment

    override fun onCreate(savedInstanceState: Bundle?) {

        val descriptions = listOf<String>(
            "ceci est la pub 1 ahetez svp",
            "voici la pub 2 venez",
            "regardez la pub 3 et ahetez",
            "wellah regardez la pub 4 ahetez",
            "prix choc de la pub 5",
            "Ne ratez pas la pub 6")
        var i : Int = 0
        for (description in descriptions){
            i++
            var pub : Pub = Pub(i, description)
            val controller = Controller.instance
            controller.addPub(pub)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun pubClick(view : View): Unit {
        val controller = Controller.instance
        var tag :Any = view.getTag()
        if(tag is String){
            tag = tag.toInt() -1
            pubFragment = Pub_fragment.newInstance(tag)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, pubFragment)
                .addToBackStack(pubFragment.toString())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        }


    }
}

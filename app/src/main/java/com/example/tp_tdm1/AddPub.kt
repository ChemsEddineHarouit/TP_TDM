package com.example.tp_tdm1

import android.app.Activity
import android.content.ClipData
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_add_pub.*
import kotlinx.android.synthetic.main.content_add_pub.*

class AddPub : AppCompatActivity() {

    val CODE_MULTIPLE_IMAGE = 1
    val uri_list = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pub)
        setSupportActionBar(toolbar)

        add_pub_pick_imgs.setOnClickListener {

            val intent = Intent()

            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_GET_CONTENT

            startActivityForResult(Intent.createChooser(intent,
                "Veuillez selectionner des images"), CODE_MULTIPLE_IMAGE)
        }

        add_pub_btn.setOnClickListener {

            // get the values
            val name = add_pub_name.text.toString()
            val description = add_pub_description.text.toString()
            val price = add_pub_price.text.toString()

            // control missing values
            if (name == ""){
                Toast.makeText(this, "Veuillez introduire le nom", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (description == ""){
                Toast.makeText(this, "Veuillez introduire une description", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (price == ""){
                Toast.makeText(this, "Veuillez introduire le prix", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // create the intent and push the values into it
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("description", description)
            intent.putExtra("price", price)
            intent.putStringArrayListExtra("uris", uri_list)

            // go to MainActivity
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode != CODE_MULTIPLE_IMAGE || resultCode != RESULT_OK){
            // didn't pick image
//            Toast.makeText(this, "Veuillez selectionner une image", Toast.LENGTH_SHORT).show()
            return
        }

        // default height and width for scrollview display images
        val h = resources.getDimension(R.dimen.imageview_height).toInt()
        val w = resources.getDimension(R.dimen.imageview_width).toInt()

        // picked 1 image
        if (data?.data != null){

            var image = ImageView(this)
            image.setImageURI(data?.data)
            add_pub_imgs.addView(image)
            image.requestLayout()

            image.layoutParams.height = h
            image.layoutParams.width = w

            uri_list.add(data?.data.toString())

            return
        }

        // picked multiple images
        var clipdata = data?.clipData

        if (clipdata != null){

            for (i in clipdata.itemCount - 1 downTo  0){
                // add uri to list
                val uri =  clipdata.getItemAt(i).uri
                uri_list.add(uri.toString())

                //add image uri to scrollview
                val image = ImageView(this)
                image.setImageURI(uri)
                add_pub_imgs.addView(image)

                image.requestLayout()

                image.layoutParams.height = h
                image.layoutParams.width = w

//                Toast.makeText(this, "lol", Toast.LENGTH_SHORT).show()
            }

        }
    }

}

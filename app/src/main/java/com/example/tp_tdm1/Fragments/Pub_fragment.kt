package com.example.tp_tdm1.Fragments

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.example.tp_tdm1.Controllers.Controller
import com.example.tp_tdm1.Models.Pub

import com.example.tp_tdm1.R
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_pub.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Pub_fragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Pub_fragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class Pub_fragment : Fragment(){
    // TODO: Rename and change types of parameters
    private var pub: Pub? = null
    private var num: Int = 0
    private var listener: OnFragmentInteractionListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            num = it.getInt(ARG_PARAM1)
            this.pubInit()

        }

    }

    private fun pubInit(): Unit {
        val tag : Int = this.num
        val controller = Controller.instance
        val pub : Pub = controller.getPub(tag)
        pub.log()
        this.pub = pub
    }

    private fun pubDisplay() {
        description_id.setText(pub?.description)
        val controller = Controller.instance
        val imgs = controller.getImagesOfPub(this.pub)
        println("---------------------------------------")
        println(imgs)
        if(imgs != null){
            for (img in imgs){
                val imageView = ImageView(context)
                imageView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
//                imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                imageView.setImageResource(img)
                pubFragment_id.addView(imageView, 0)
            }

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pubDisplay()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pub, container, false)
    }


    override fun onDetach() {
        super.onDetach()
        listener = null
    }
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int) =
            Pub_fragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }
}

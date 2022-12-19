package com.example.sharememe

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.View
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var currentUrl:String? = null
    var data:String?=null
//    Null safety (?) is a feature of modern programming languages created
//    to reduce (or eliminate) the danger of referencing null values in your source code.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadmeme() /*Calling load meme function*/
    }

    private fun loadmeme(){
//        Instantiate the request queue
//        val queue = Volley.newRequestQueue(this)
        currentUrl = "https://meme-api.com/gimme"

        buffer.visibility=View.VISIBLE /*Whenever the function is called buffer would be visible*/
// Request a Json response from the provided URL.
        val jsonReq = JsonObjectRequest(
            Request.Method.GET, currentUrl,null,
            { response ->
                 data=response.getString("url")

                Glide.with(this).load(data).listener(object:RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        buffer.visibility=View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        buffer.visibility=View.GONE
                        return false
                    }
                }).into(memeImg)
            },
            { })

// Add the request to the RequestQueue.
//        queue.add(jsonReq)
        MySingleton.getInstance(this).addToRequestQueue(jsonReq)
    }

    fun funcshare(view: View) {

        val intent = Intent(Intent.ACTION_SEND)
        intent.type="text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "Hey check out this cool meme from Reddit $data")

        val chooser =Intent.createChooser(intent,"Share this meme using..")
        startActivity(chooser)
    }

    fun funcnext(view: View) {
        loadmeme()
    }
}
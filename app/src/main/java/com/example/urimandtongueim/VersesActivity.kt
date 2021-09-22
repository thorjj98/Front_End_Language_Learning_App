package com.example.urimandtongueim

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class VersesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verses)

        //declare the content of the textview
        val content: TextView = findViewById(R.id.verses) as TextView
        content.movementMethod = LinkMovementMethod()

        //get a string containing every verse in the chapter
        val contentSpannableString = SpannableString("At ito ay nangyari na")

        //iterate over each segment
        for (i in 1..3){
            if (i == 1){
                val clickableSpan = object: ClickableSpan(){
                    override fun onClick(widget: View) {
                        //pop up menu
                    }
                }
                //set the length of the span
                contentSpannableString.setSpan(clickableSpan, 0, 2, 0)
            }
            else if (i == 2){
                val clickableSpan = object: ClickableSpan(){
                    override fun onClick(widget: View) {
                        //pop up menu
                    }
                }
                //set the length of the span
                contentSpannableString.setSpan(clickableSpan, 3, 6, 0)
            }
            else {
                val clickableSpan = object: ClickableSpan(){
                    override fun onClick(widget: View) {
                        //pop up menu
                    }
                }
                //set the length of the span
                contentSpannableString.setSpan(clickableSpan, 7, 21, 0)
            }
        }

        //set the textview to the string containing the segmented chapter
        content.setText(contentSpannableString).toString()
    }
}
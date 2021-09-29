package com.example.urimandtongueim

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginLeft
import kotlinx.android.synthetic.main.activity_verses.*

class VersesActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verses)

        //declare the content of the textview
        val content: TextView = findViewById(R.id.verses) as TextView
        content.movementMethod = LinkMovementMethod()


        //get a string containing every verse in the chapter
        val contentSpannableString = SpannableString("At ito ay nangyari na")

        //call popup window
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.popup_translation,null)
        val popupWindow = PopupWindow(
            view, // Custom view to show in popup window
            LinearLayout.LayoutParams.WRAP_CONTENT, // Width of popup window
            LinearLayout.LayoutParams.WRAP_CONTENT // Window height
        )
        val translation = view.findViewById<TextView>(R.id.translation_popup)
        var popupDisplayed = false
        view.setOnClickListener{
            if(popupDisplayed){
                popupWindow.dismiss()
                popupDisplayed = false
            }
        }

        //iterate over each segment
        for (i in 1..3){
            if (i == 1){
                val clickableSpan = object: ClickableSpan(){
                    @RequiresApi(Build.VERSION_CODES.KITKAT)
                    override fun onClick(widget: View) {
                        if (popupDisplayed){
                            popupWindow.dismiss()
                        }
                        translation.setText("And")
                        TransitionManager.beginDelayedTransition(root_layout)
                        popupWindow.showAtLocation(
                            root_layout, // Location to display popup window
                            Gravity.CENTER, // Exact position of layout to display popup
                            0, // X offset
                            0 // Y offset
                        )
                        popupDisplayed = true
                    }

                    override fun updateDrawState(ds: TextPaint) {
                        super.updateDrawState(ds)
                        ds.setColor(Color.parseColor("#000000"))
                        ds.setUnderlineText(false);
                    }
                }
                //set the length of the span
                contentSpannableString.setSpan(clickableSpan, 0, 2, 0)
            }
            else if (i == 2){
                val clickableSpan = object: ClickableSpan(){
                    @RequiresApi(Build.VERSION_CODES.KITKAT)
                    override fun onClick(widget: View) {
                        if (popupDisplayed){
                            popupWindow.dismiss()
                        }
                        translation.setText("it")
                        TransitionManager.beginDelayedTransition(root_layout)
                        popupWindow.showAtLocation(
                            root_layout, // Location to display popup window
                            Gravity.CENTER, // Exact position of layout to display popup
                            0, // X offset
                            0 // Y offset
                        )
                        popupDisplayed = true
                    }

                    override fun updateDrawState(ds: TextPaint) {
                        super.updateDrawState(ds)
                        ds.setColor(Color.parseColor("#000000"))
                        ds.setUnderlineText(false);
                    }
                }
                //set the length of the span
                contentSpannableString.setSpan(clickableSpan, 3, 6, 0)
            }
            else if (i == 3){
                val clickableSpan = object: ClickableSpan(){
                    @RequiresApi(Build.VERSION_CODES.KITKAT)
                    override fun onClick(widget: View) {
                        if (popupDisplayed){
                            popupWindow.dismiss()
                        }
                        translation.setText("came to pass")
                        TransitionManager.beginDelayedTransition(root_layout)
                        popupWindow.showAtLocation(
                            root_layout, // Location to display popup window
                            Gravity.CENTER, // Exact position of layout to display popup
                            0, // X offset
                            0 // Y offset
                        )
                        popupDisplayed = true
                    }

                    override fun updateDrawState(ds: TextPaint) {
                        super.updateDrawState(ds)
                        ds.setColor(Color.parseColor("#000000"))
                        ds.setUnderlineText(false);
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
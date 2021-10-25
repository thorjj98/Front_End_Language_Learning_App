package com.example.urimandtongueim

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.urimandtongueim.model.JsonParser
import kotlinx.android.synthetic.main.activity_verses.*

class VersesActivity : AppCompatActivity() {
    @SuppressLint("InflateParams")
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verses)

        val book = intent.getStringExtra("book")
        val standardWork = intent.getIntExtra("standardWork", -1)
        val chapter = intent.getIntExtra("chapter", -1)

        var isTextClicked = false
        val blueHighlight = verses.highlightColor

        //declare the content of the textview
        val content: TextView = findViewById(R.id.verses)
        content.movementMethod = LinkMovementMethod()

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
            isTextClicked = false
        }

        val wholePage: ImageView = findViewById(R.id.fullPage)
        wholePage.setOnClickListener{
            if(popupDisplayed && !isTextClicked){
                popupWindow.dismiss()
                popupDisplayed = false
                verses.highlightColor = (Color.TRANSPARENT)
                verses.highlightColor = blueHighlight
            }
            isTextClicked = false

        }

        val jsonParser = JsonParser()
        val learningVerses = book?.let { jsonParser.getVerses(this, chapter, it, standardWork, 1) }
        val nativeVerses = book?.let { jsonParser.getVerses(this, chapter, it, standardWork, 0) }
        val mapVerses = book?.let { jsonParser.getMapping(this, chapter, it, standardWork, 2) }

        if (nativeVerses != null && learningVerses != null && mapVerses != null) {
            for (i in nativeVerses.indices){
                val english = nativeVerses[i]
                val spanish = learningVerses[i]
                val translationCode = mapVerses[i]

                //get a string containing every verse in the chapter
                val contentSpannableString = SpannableString(spanish)
                for (link in translationCode){
                    val engStart : Int = link[0]
                    val engEnd : Int = link[1]
                    val spaStart : Int = link[2]
                    val spaEnd : Int = link[3]
                    val id : Int = link[4]
                    val eng = english.subSequence(engStart, engEnd)
                    val clickableSpan = object: ClickableSpan(){
                        @RequiresApi(Build.VERSION_CODES.KITKAT)
                        override fun onClick(widget: View) {
                            if (popupDisplayed){
                                popupWindow.dismiss()
                            }
                            widget.invalidate()
                            translation.text = eng
                            TransitionManager.beginDelayedTransition(root_layout)
                            popupWindow.showAtLocation(
                                root_layout, // Location to display popup window
                                Gravity.CENTER, // Exact position of layout to display popup
                                0, // X offset
                                0 // Y offset
                            )
                            popupDisplayed = true
                            isTextClicked = true

                        }

                        override fun updateDrawState(ds: TextPaint) {
                            super.updateDrawState(ds)
                            ds.color = Color.parseColor("#000000")
                            ds.isUnderlineText = false
                        }
                    }
                    //set the length of the span
                    contentSpannableString.setSpan(clickableSpan, spaStart, spaEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
                //set the textview to the string containing the segmented chapter
                content.append(contentSpannableString).toString()
                content.append("\n\n").toString()

            }
        }
    }
}
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

        var isTextClicked = false;
        val BLUE_HIGHLIGHT = verses.highlightColor;

        //declare the content of the textview
        val content: TextView = findViewById(R.id.verses) as TextView
        content.movementMethod = LinkMovementMethod()

        //call popup window
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.popup_translation,null)
        val popupWindow = PopupWindow(
            view, // Custom view to show in popup window
            LinearLayout.LayoutParams.WRAP_CONTENT, // Width of popup window
            LinearLayout.LayoutParams.WRAP_CONTENT // Window height
        )
        val translation = view.findViewById<TextView>(R.id.bubble)
        var popupDisplayed = false
        view.setOnClickListener{
            if(popupDisplayed){
                popupWindow.dismiss()
                popupDisplayed = false
            }
            isTextClicked = false
        }

        val wholePage: ImageView = findViewById<ImageView>(R.id.fullPage)
        wholePage.setOnClickListener{
            if(popupDisplayed && !isTextClicked){
                popupWindow.dismiss()
                popupDisplayed = false
                verses.highlightColor = (Color.TRANSPARENT)
                verses.highlightColor = BLUE_HIGHLIGHT
            }
            isTextClicked = false

        }

        var english = "Nephi begins the record of his people—Lehi sees in vision a pillar of fire and reads from a book of prophecy—He praises God, foretells the coming of the Messiah, and prophesies the destruction of Jerusalem—He is persecuted by the Jews. About 600 B.C."

        var spanish = "Nefi da principio a la historia de su pueblo — Lehi ve en visión un pilar de fuego y lee en un libro de profecías — Alaba a Dios, predice la venida del Mesías y profetiza la destrucción de Jerusalén — Es perseguido por los judíos. Aproximadamente 600 a.C."

        //get a string containing every verse in the chapter
        val contentSpannableString = SpannableString(spanish)


        var translationCode = arrayOf(arrayOf(0,5,0,4,-1), arrayOf(6,12,5,19,-1), arrayOf(13,16,20,22,-1),
            arrayOf(17, 23, 23, 31, -1), arrayOf(24, 26, 32, 34, -1), arrayOf(27, 30, 35, 37, -1), arrayOf(31, 37, 38, 44, -1),
            arrayOf(37, 38, 45, 46, -1), arrayOf(38, 42, 47, 51, -1), arrayOf(43, 47, 52, 54, -1), arrayOf(48, 50, 55, 57, -1),
            arrayOf(51, 57, 58, 64, -1), arrayOf(58, 59, 65, 67, -1), arrayOf(60, 66, 68, 73, -1), arrayOf(67, 69, 74, 76, -1),
            arrayOf(70, 74, 77, 82, -1), arrayOf(75, 78, 83, 84, -1), arrayOf(79, 84, 85, 88, -1), arrayOf(85, 89, 89, 91, -1),
            arrayOf(90, 91, 92, 94, -1), arrayOf(92, 96, 95, 100, -1), arrayOf(97, 99, 101, 103, -1), arrayOf(100, 108, 104, 113, -1),
            arrayOf(108, 109, 114, 115, -1), arrayOf(109, 123, 116, 128, -1), arrayOf(123, 124, 128, 129, -1), arrayOf(125, 134, 130, 137, -1),
            arrayOf(135, 138, 138, 140, -1), arrayOf(139, 145, 141, 147, -1), arrayOf(146, 152, 148, 151, -1), arrayOf(153, 165, 152, 160, -1),
            arrayOf(166, 176, 161, 170, -1), arrayOf(177, 180, 171, 173, -1), arrayOf(181, 192, 174, 185, -1), arrayOf(193, 195, 186, 188, -1),
            arrayOf(196, 205, 189, 198, -1), arrayOf(205, 211, 199, 203, -1), arrayOf(212, 222, 204, 214, -1), arrayOf(223, 225, 215, 218, -1),
            arrayOf(226, 229, 219, 222, -1), arrayOf(230, 234, 223, 229, -1), arrayOf(234, 235, 229, 230, -1), arrayOf(236, 241, 231, 246, -1),
            arrayOf(242, 245, 247, 250, -1), arrayOf(246, 247, 251, 252, -1), arrayOf(247, 248, 252, 253, -1), arrayOf(248, 249, 253, 254, -1),
            arrayOf(249, 250, 254, 255, -1))


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
                    translation.setText(eng)
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
                    ds.setColor(Color.parseColor("#000000"))
                    ds.setUnderlineText(false);
                }
            }
            //set the length of the span
            contentSpannableString.setSpan(clickableSpan, spaStart, spaEnd, 0)
        }

        //set the textview to the string containing the segmented chapter
        content.setText(contentSpannableString).toString()
    }
}
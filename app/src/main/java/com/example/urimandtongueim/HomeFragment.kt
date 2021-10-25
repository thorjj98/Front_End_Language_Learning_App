package com.example.urimandtongueim

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        val buttonAddLanguage = view.findViewById<Button>(R.id.addLanguage)
        buttonAddLanguage.setOnClickListener {
            val intent = Intent(activity, AddLanguageActivity::class.java)
            startActivity(intent)
        }

        val buttonSpanish = view.findViewById<Button>(R.id.languageSpanish)
        buttonSpanish.setOnClickListener {
            val intent = Intent(activity, StudyActivity::class.java)
            startActivity(intent)
        }

        val buttonUserSettings = view.findViewById<Button>(R.id.user)
        buttonUserSettings.setOnClickListener {
            val intent = Intent(activity, UserSettingsActivity::class.java)
            startActivity(intent)
        }
        return view
    }
}
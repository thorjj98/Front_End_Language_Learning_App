package com.example.urimandtongueim

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.urimandtongueim.model.DataCache
import com.example.urimandtongueim.model.requests.LoginRequest
import com.example.urimandtongueim.model.requests.RegisterRequest
import com.example.urimandtongueim.model.service.LoginService
import com.example.urimandtongueim.model.service.RegisterService

class RegisterFragment : Fragment()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    var registerService = RegisterService()


    @SuppressLint("CutPasteId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_register, container, false)

        val languageArray = arrayListOf("English", "Spanish")

        val nativeLanguageAdapter: ArrayAdapter<*>
        val nativeLanguageSpinner: Spinner = view.findViewById(R.id.nativeLanguageSpinner)
        nativeLanguageAdapter = context?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_list_item_1,
                languageArray
            )
        }!!
        nativeLanguageSpinner.adapter = nativeLanguageAdapter

        val learningLanguageAdapter: ArrayAdapter<*>
        val learningLanguageSpinner: Spinner = view.findViewById(R.id.learningLanguageSpinner)
        learningLanguageAdapter = context?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_list_item_1,
                languageArray
            )
        }!!
        learningLanguageSpinner.adapter = learningLanguageAdapter

        val registerButton = view.findViewById<Button>(R.id.register)
        registerButton.setOnClickListener {

            val userName = view.findViewById<EditText>(R.id.editUsernameText).toString()
            val password = view.findViewById<EditText>(R.id.editPasswordText).toString()
            val nativeLanguage = view.findViewById<Spinner>(R.id.nativeLanguageSpinner).selectedItem.toString()
            val learnerLanguage = view.findViewById<Spinner>(R.id.learningLanguageSpinner).selectedItem.toString()
            val response = registerService.register(RegisterRequest(userName, password, nativeLanguage, learnerLanguage))

            if (response.isSuccess()){
                val fm: FragmentManager? = fragmentManager
                val homeFragment: HomeFragment = HomeFragment()
                val args = Bundle()
                homeFragment.arguments = args
                if (fm != null) {
                    fm.beginTransaction()
                        .replace(R.id.fragmentContainer, homeFragment)
                        .commit()
                }
            }
        }

        val loginButton = view.findViewById<Button>(R.id.loginReturn)
        loginButton.setOnClickListener{
            val fm: FragmentManager? = fragmentManager
            val loginFragment: LoginFragment = LoginFragment()
            val args = Bundle()
            loginFragment.arguments = args
            if (fm != null) {
                fm.beginTransaction()
                    .replace(R.id.fragmentContainer, loginFragment)
                    .commit()
            }
        }

        return view
    }
}
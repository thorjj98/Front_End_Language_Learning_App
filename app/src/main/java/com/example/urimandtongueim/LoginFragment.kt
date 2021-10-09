package com.example.urimandtongueim

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentManager
import com.example.urimandtongueim.model.DataCache
import com.example.urimandtongueim.model.requests.LoginRequest
import com.example.urimandtongueim.model.service.LanguageService
import com.example.urimandtongueim.model.service.LoginService

class LoginFragment : Fragment() {

    var loginService = LoginService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_login, container, false)

        val loginButton = view.findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {

            val userName = view.findViewById<EditText>(R.id.editUsernameText).toString()
            val password = view.findViewById<EditText>(R.id.editPasswordText).toString()

            val response = loginService.login(LoginRequest(userName, password))
            if (response.isSuccess()){
                DataCache.setLoggedInStatus(true)
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

        val registerButton = view.findViewById<Button>(R.id.registerButton)
        registerButton.setOnClickListener{
            val fm: FragmentManager? = fragmentManager
            val registerFragment: RegisterFragment = RegisterFragment()
            val args = Bundle()
            registerFragment.arguments = args
            if (fm != null) {
                fm.beginTransaction()
                    .replace(R.id.fragmentContainer, registerFragment)
                    .commit()
            }
        }

        return view
    }
}
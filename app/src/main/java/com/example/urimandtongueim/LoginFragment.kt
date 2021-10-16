package com.example.urimandtongueim

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentManager
import com.example.urimandtongueim.model.DataCache
import com.example.urimandtongueim.model.requests.LoginRequest
import com.example.urimandtongueim.model.requests.RegisterRequest
import com.example.urimandtongueim.model.responses.LoginResponse
import com.example.urimandtongueim.model.responses.RegisterResponse
import com.example.urimandtongueim.model.service.LanguageService
import com.example.urimandtongueim.model.service.LoginService
import com.example.urimandtongueim.model.service.RegisterService

class LoginFragment : Fragment() {

    var loginService = LoginService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.N)
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

            LoginTask().execute(LoginRequest(userName, password))
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

    fun login(response: LoginResponse) {
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

    @SuppressLint("StaticFieldLeak")
    inner class LoginTask @RequiresApi(Build.VERSION_CODES.N) constructor() :
        AsyncTask<LoginRequest, Void, LoginResponse>() {

        @RequiresApi(Build.VERSION_CODES.N)
        override fun onPostExecute(response: LoginResponse) {
            super.onPostExecute(response)
            login(response)
        }

        override fun doInBackground(vararg request: LoginRequest): LoginResponse? {
            val loginService = LoginService()
            return loginService.login(request[0])
        }

    }
}
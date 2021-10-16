package com.example.urimandtongueim

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.urimandtongueim.model.DataCache
import com.example.urimandtongueim.model.requests.LanguageRequest
import com.example.urimandtongueim.model.requests.RegisterRequest
import com.example.urimandtongueim.model.responses.LanguageResponse
import com.example.urimandtongueim.model.responses.RegisterResponse
import com.example.urimandtongueim.model.service.LanguageService
import com.example.urimandtongueim.model.service.RegisterService
import com.example.urimandtongueim.net.RegisterTask

class RegisterFragment : Fragment()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    var registerService = RegisterService()

    private val languageAsyncTask = LanguageTask()
    @RequiresApi(Build.VERSION_CODES.N)
    val registerAsyncTask = RegisterTask()

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("CutPasteId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_register, container, false)

        languageAsyncTask.execute()

        val registerButton = view.findViewById<Button>(R.id.register)
        registerButton.setOnClickListener {

            val userName = view.findViewById<EditText>(R.id.editUsernameText).toString()
            val password = view.findViewById<EditText>(R.id.editPasswordText).toString()
            val nativeLanguage = view.findViewById<Spinner>(R.id.nativeLanguageSpinner).selectedItem.toString()
            val learnerLanguage = view.findViewById<Spinner>(R.id.learningLanguageSpinner).selectedItem.toString()
            val response = registerService.register(RegisterRequest(userName, password, nativeLanguage, learnerLanguage))

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

    fun setLanguages(languages: Array<String>){
        val nativeLanguageSpinner: Spinner = view!!.findViewById(R.id.nativeLanguageSpinner)
        val nativeLanguageAdapter: ArrayAdapter<*> = context?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_list_item_1,
                languages
            )
        }!!
        nativeLanguageSpinner.adapter = nativeLanguageAdapter

        val learningLanguageSpinner: Spinner = view!!.findViewById(R.id.learningLanguageSpinner)
        val learningLanguageAdapter: ArrayAdapter<*> = context?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_list_item_1,
                languages
            )
        }!!
        learningLanguageSpinner.adapter = learningLanguageAdapter
    }

    fun register(response: RegisterResponse) {
        if (response.isSuccess()){
            DataCache.setLoggedInStatus(true)
            val fm: FragmentManager? = fragmentManager
            val homeFragment: HomeFragment = HomeFragment()
            val args = Bundle()
            homeFragment.arguments = args
            fm?.beginTransaction()?.replace(R.id.fragmentContainer, homeFragment)?.commit()
        }
    }

    @SuppressLint("StaticFieldLeak")
    inner class LanguageTask: AsyncTask<LanguageRequest, Void, LanguageResponse>(){
        @RequiresApi(Build.VERSION_CODES.N)
        override fun doInBackground(vararg params: LanguageRequest?): LanguageResponse? {
            val languageService = LanguageService()
            return languageService.getLanguages(LanguageRequest())
        }

        override fun onPostExecute(result: LanguageResponse?) {
            super.onPostExecute(result)
            if (result != null && result.isSuccess()) {
                setLanguages(result.getLanguages())
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    inner class RegisterTask @RequiresApi(Build.VERSION_CODES.N) constructor() :
        AsyncTask<RegisterRequest, Void, RegisterResponse>() {

        @RequiresApi(Build.VERSION_CODES.N)
        override fun onPostExecute(response: RegisterResponse) {
            super.onPostExecute(response)
            register(response)
        }

        override fun doInBackground(vararg request: RegisterRequest): RegisterResponse {
            val registerService = RegisterService()
            return registerService.register(request[0])
        }

    }
}
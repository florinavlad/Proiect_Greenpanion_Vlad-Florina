package com.example.greenpanion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation

class IntroFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginButton = view.findViewById<Button>(R.id.buttonLogin)

        val signupButton = view.findViewById<Button>(R.id.buttonSignUp)

        loginButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_introFragment_to_loginFragment)
        }

        signupButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_introFragment_to_registerFragment)
        }
    }

}
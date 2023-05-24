package com.example.greenpanion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class StatisticsFragment : Fragment() {

    private lateinit var tvFirstName: TextView
    private lateinit var tvLastName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvFirstName = view.findViewById(R.id.tv_firstName)
        tvLastName = view.findViewById(R.id.tv_lastName)

        val firstName = arguments?.getString("firstName")
        val lastName = arguments?.getString("lastName")

        tvFirstName.text = firstName
        tvLastName.text = lastName

    }

}
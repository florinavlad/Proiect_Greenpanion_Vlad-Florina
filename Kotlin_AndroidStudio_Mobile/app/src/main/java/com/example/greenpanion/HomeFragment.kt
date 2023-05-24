package com.example.greenpanion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation


class HomeFragment : Fragment() {

    private lateinit var logoutBtn: Button
    private lateinit var statsBtn: Button
    private lateinit var mapBtn: Button
    private lateinit var howRecycleBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        howRecycleBtn = view.findViewById(R.id.howRecycle_btn)
        mapBtn = view.findViewById(R.id.map_btn)
        statsBtn = view.findViewById(R.id.stats_btn)
        logoutBtn = view.findViewById(R.id.logout_btn)

        logoutBtn.setOnClickListener {
            Toast.makeText(requireContext(), "Delogare cu succes!", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_loginFragment)
        }
        statsBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_statisticsFragment)
        }
        mapBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_mapPrizesFragment)
        }
        howRecycleBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_howRecycleFragment)
        }


    }

    fun signUserOut(){
//Do smth
    }

}
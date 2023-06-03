package com.example.greenpanion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation

class CalculatePointsFragment : Fragment() {

    private lateinit var totalPointsTextView: TextView
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var exitBtn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculate_points, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        totalPointsTextView = view.findViewById(R.id.tv_totalPoints)
        sharedViewModel.points.observe(viewLifecycleOwner, Observer {
            totalPointsTextView.text = it.toString()
        })

        exitBtn = view.findViewById(R.id.exitPoints_Btn)
        exitBtn.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_calculatePointsFragment_to_manuallyAddPointsFragment)
        }
    }

}

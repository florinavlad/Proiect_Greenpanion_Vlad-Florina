package com.example.greenpanion

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class CalculatePointsFragment : Fragment() {

    private lateinit var totalPointsTextView: TextView
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var exitBtn: ImageButton
    private lateinit var validatePctBtn: Button
    private lateinit var validatePctTxt: EditText



    private fun checkValidationCode(code: String) {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "http://192.168.43.195:8080/points/check?code=$code"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val isValid = response.toBoolean()
                if (isValid) {
                    totalPointsTextView = requireView().findViewById(R.id.tv_totalPoints)
                    val validatedPoints = totalPointsTextView.text.toString().toInt()

//                    val bundle = Bundle()
//                    bundle.putInt("validatedPoints", validatedPoints)
                    saveValidatedPoints(validatedPoints)
                    requireView().findNavController().navigate(R.id.action_calculatePointsFragment_to_statisticsFragment)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Cod invalid!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            { error ->
                Toast.makeText(
                    requireContext(),
                    "Eroare!",
                    Toast.LENGTH_SHORT
                ).show()
            })
        queue.add(stringRequest)
    }

    private fun saveValidatedPoints(points: Int) {
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val totalPoints = sharedPreferences.getInt("totalPoints", 0)

        val editor = sharedPreferences.edit()
        editor.putInt("totalPoints", totalPoints + points)
        editor.apply()
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
        exitBtn.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_calculatePointsFragment_to_manuallyAddPointsFragment)
        }

        validatePctBtn = view.findViewById(R.id.validatePct_Btn)
        validatePctTxt = view.findViewById(R.id.validatePct_EditText)

        validatePctBtn.setOnClickListener {
            val code = validatePctTxt.text.toString()
            checkValidationCode(code)

        }

    }
}

package com.example.greenpanion

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.navigation.Navigation
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

class StatisticsFragment : Fragment() {

    private lateinit var tvFirstName: TextView
    private lateinit var tvLastName: TextView

    private val barLauncher: ActivityResultLauncher<ScanOptions> = registerForActivityResult(
        ScanContract()
    ) { result ->
        if (result.contents != null) {
//            parseScanningResultAndAddStats(result.contents)
        }
    }

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

        val btnScanCode = view.findViewById<Button>(R.id.scan_btn)
        btnScanCode.setOnClickListener{
            scanCode()
        }
        val btnManualPoints = view.findViewById<Button>(R.id.manualPoints_btn)
        btnManualPoints.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_statisticsFragment_to_manuallyAddPointsFragment)
        }

        tvFirstName = view.findViewById(R.id.tv_firstName)
        tvLastName = view.findViewById(R.id.tv_lastName)

        val firstName = arguments?.getString("firstName")
        val lastName = arguments?.getString("lastName")

        tvFirstName.text = firstName
        tvLastName.text = lastName

    }

    private fun scanCode() {
        val options = ScanOptions()
        options.setPrompt("Apasă pe volum în sus pentru a activa lanterna!")
        options.setBeepEnabled(false)
        options.setOrientationLocked(true)
        options.captureActivity = CaptureAct::class.java
        barLauncher.launch(options)

    }

}
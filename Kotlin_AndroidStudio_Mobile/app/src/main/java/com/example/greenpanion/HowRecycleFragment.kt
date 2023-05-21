package com.example.greenpanion

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

class HowRecycleFragment : Fragment() {
    private val barLauncher: ActivityResultLauncher<ScanOptions> = registerForActivityResult(ScanContract()) { result ->
        if (result.contents != null) {
//            parseScanningResultAndAddStats(result.contents)
            Log.v("FlorinaTAG", "Added stats to db!")
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
        return inflater.inflate(R.layout.fragment_how_recycle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnScanCode = view.findViewById<Button>(R.id.scan_btn)
        btnScanCode.setOnClickListener() {
            scanCode()
        }
    }

    private fun scanCode() {
        val options = ScanOptions()
        options.setPrompt("Volume up to turn on flashlight!")
        options.setBeepEnabled(false)
        options.setOrientationLocked(true)
        options.captureActivity = CaptureAct::class.java
        barLauncher.launch(options)

    }

}
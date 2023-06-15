package com.example.greenpanion

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import org.json.JSONObject

class StatisticsFragment : Fragment() {

    //    private lateinit var tvFirstName: TextView
//    private lateinit var tvLastName: TextView
    private lateinit var tvUserPoints: TextView

    private val barLauncher: ActivityResultLauncher<ScanOptions> = registerForActivityResult(
        ScanContract()
    ) { result ->
        if (result.contents != null) {
//            parseScanningResultAndAddStats(result.contents)
        }
    }

    private fun saveUserPoints(userPoints: Int, userEmail: String) {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "http://192.168.43.195:8080/points/savePoints"

        val jsonObject = JSONObject().apply {
            put("points", userPoints)
            put("email", userEmail)
        }

        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            { response ->
                val message = response.getString("message")

                if (message == "Success") {
                    showToast("Puncte salvate cu succes")
                } else if (message == "No user in database") {
                    showToast("Utilizatorul nu a fost găsit în baza de date.")
                } else {
                    showToast("A apărut o eroare la salvarea punctelor utilizatorului.")
                }
            },
            { error ->
                error.printStackTrace()
                showToast("Eroare server")
            }
        )

        queue.add(request)
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
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
        btnScanCode.setOnClickListener {
            scanCode()
        }
        val btnManualPoints = view.findViewById<Button>(R.id.manualPoints_btn)
        btnManualPoints.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_statisticsFragment_to_manuallyAddPointsFragment)
        }

//        tvFirstName = view.findViewById(R.id.tv_firstName)
//        tvLastName = view.findViewById(R.id.tv_lastName)
//
//        val firstName = arguments?.getString("firstName")
//        val lastName = arguments?.getString("lastName")
//
//        tvFirstName.text = firstName
//        tvLastName.text = lastName

        val totalPoints = getSavedPoints()
        tvUserPoints = view.findViewById(R.id.tv_userPoints)
        tvUserPoints.text = "$totalPoints"
        val userPointsString: String = tvUserPoints.text.toString()
        val userPoints: Int? = userPointsString.toIntOrNull()

        val sharedPreferences =
            requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userEmail = sharedPreferences.getString("email", null)

        if (userEmail != null && userPoints != null) {
            saveUserPoints(userPoints, userEmail)
        }
    }

    private fun getSavedPoints(): Int {
        val sharedPreferences =
            requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val totalPoints = sharedPreferences.getInt("totalPoints", 0)

        when {
            totalPoints >= 4500 && !sharedPreferences.getBoolean("congrats4500", false) -> {
                showCongratulationsPopup(
                    "Bravo!",
                    "Voucher trotinetă electrică, 50% reducere un an, nu e minunat?"
                )
                sharedPreferences.edit().putBoolean("congrats4500", true).apply()
            }

            totalPoints >= 3500 && !sharedPreferences.getBoolean("congrats3500", false) -> {
                showCongratulationsPopup("Felicitări!", "Pregătit să zbori cu balonul cu aer cald?")
                sharedPreferences.edit().putBoolean("congrats3500", true).apply()
            }

            totalPoints >= 2100 && !sharedPreferences.getBoolean("congrats2100", false) -> {
                showCongratulationsPopup("Bravo!", "Fugi la Kaufland, ai voucher 300 RON!")
                sharedPreferences.edit().putBoolean("congrats2100", true).apply()
            }

            totalPoints >= 1500 && !sharedPreferences.getBoolean("congrats1500", false) -> {
                showCongratulationsPopup("Super!", "Ai câștigat un CORT CAMPING de 5 persoane!")
                sharedPreferences.edit().putBoolean("congrats1500", true).apply()
            }
        }

        return totalPoints
    }


    private fun showCongratulationsPopup(title: String, message: String) {
        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
        alertDialog.show()
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
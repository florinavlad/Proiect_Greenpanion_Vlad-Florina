package com.example.greenpanion

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
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
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import org.json.JSONObject

class StatisticsFragment : Fragment() {

    private lateinit var tvUserPoints: TextView

    private val barLauncher: ActivityResultLauncher<ScanOptions> = registerForActivityResult(
        ScanContract()
    ) { result ->
        if (result.contents != null) {
            // parseScanningResultAndAddStats(result.contents)
        }
    }

    private fun saveUserPoints(userPoints: Int, userEmail: String) {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "http://192.168.43.195:8080/points/savePoints"

        val jsonObject = JSONObject().apply {
            put("points", userPoints)
            put("email", userEmail)
        }

        val request = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response ->
                if (response == "Success") {
                    val toast = Toast.makeText(
                        requireContext(),
                        "Puncte salvate",
                        Toast.LENGTH_LONG
                    )
                    toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 140)
                    toast.show()
                } else if (response == "No user in database") {
                    showToast("Utilizatorul nu a fost găsit în baza de date.")
                } else {
                    showToast("A apărut o eroare la salvarea punctelor utilizatorului.")
                }
            },
            Response.ErrorListener { error ->
                error.printStackTrace()
                showToast("Eroare server")
            }) {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                return jsonObject.toString().toByteArray()
            }
        }

        queue.add(request)
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

        tvUserPoints = view.findViewById(R.id.tv_userPoints)
        var savedPoints = -1
        getSavedPoints { num -> savedPoints = num }
        Log.d("StatisticsFragment", "Saved points: $savedPoints")
        val arguments = arguments
        if (arguments != null && arguments.containsKey("totalPoints")) {
            val totalPoints = arguments.getInt("totalPoints", 0)
            val currentPoints = savedPoints?.toString()?.toInt() ?: 0
            val newPoints = currentPoints + totalPoints
            tvUserPoints.text = newPoints.toString()
        }
    }

    private fun getEmailFromSharedPreferences(): String? {
        val sharedPreferences =
            requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("email", null)
    }

    private fun updatePointsUI(points: Int) {
        val tvUserPoints = view?.findViewById<TextView>(R.id.tv_userPoints)
        tvUserPoints?.text = points.toString()
    }


    private fun getSavedPoints(param: (Int) -> Unit) {
        val userEmail = getEmailFromSharedPreferences()
        val queue = Volley.newRequestQueue(requireContext())
        val url = "http://192.168.43.195:8080/points/getPoints?email=$userEmail"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val pointsString = response.trim()
                val points = pointsString.toIntOrNull()
                if (points != null) {
                    updatePointsUI(points)
                    val arguments = arguments
                    if (arguments != null && arguments.containsKey("totalPoints")) {
                        val totalPoints = arguments.getInt("totalPoints", 0)

                        if (userEmail != null && points != null) {

                            saveUserPoints(points + totalPoints, userEmail)
                            val sumPoints = points + totalPoints
                            updatePointsUI(sumPoints)

                            if (sumPoints >= 1500 && sumPoints - totalPoints < 1500) {
                                showCongratulationsPopup(
                                    "Super, verifică email!",
                                    "Ai câștigat un CORT CAMPING de 5 persoane!"
                                )
                                sendEmailToUser(userEmail)
                            } else if (sumPoints >= 2100 && sumPoints - totalPoints < 2100) {
                                showCongratulationsPopup(
                                    "Bravo, verifică email!",
                                    "Fugi la Kaufland, ai voucher 300 RON!"
                                )
                                sendEmailToUser(userEmail)
                            } else if (sumPoints >= 3500 && sumPoints - totalPoints < 3500) {
                                showCongratulationsPopup(
                                    "Felicitări, verifică email!",
                                    "Pregătit să zbori cu balonul cu aer cald?"
                                )
                                sendEmailToUser(userEmail)
                            } else if (sumPoints >= 4500 && sumPoints - totalPoints < 4500) {
                                showCongratulationsPopup(
                                    "Bravo, verifică email!",
                                    "Voucher trotinetă electrică, 50% reducere un an, nu e minunat?"
                                )
                                sendEmailToUser(userEmail)
                            } else {
                            }
                        }
                    }
                }
            },
            { error ->
                showToast("Error retrieving points: ${error.message}")
            }
        )
        queue.add(stringRequest)
    }

    private fun sendEmailToUser(userEmail: String) {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "http://192.168.43.195:8080/points/send-congratulations-email?email=$userEmail"

        val jsonObject = JSONObject().apply {
            put("email", userEmail.toString())
        }

        val request = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response ->

            },
            Response.ErrorListener { error ->
                error.printStackTrace()
                showToast("Eroare server")
            }) {
        }

        queue.add(request)
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

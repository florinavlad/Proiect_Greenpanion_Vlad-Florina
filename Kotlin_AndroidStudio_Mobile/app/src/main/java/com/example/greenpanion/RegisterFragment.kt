package com.example.greenpanion

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.VolleyLog
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.io.UnsupportedEncodingException


class RegisterFragment : Fragment() {

    private lateinit var stateSpinner: Spinner
    private lateinit var citySpinner: Spinner
    private lateinit var stateAdapter: ArrayAdapter<CharSequence>
    private lateinit var cityAdapter: ArrayAdapter<CharSequence>
    private lateinit var selectedState: String
    private lateinit var selectedCity: String
    private lateinit var registerBtn: Button
    private lateinit var tvState: TextView
    private lateinit var tvCity: TextView
    private lateinit var tvName1: TextView
    private lateinit var tvName2: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvPassword: TextView
    private lateinit var tvConfPass: TextView
    private lateinit var etRegisterName1: EditText
    private lateinit var etRegisterName2: EditText
    private lateinit var etRegisterEmail: EditText
    private lateinit var etRegisterPass: EditText
    private lateinit var etRegisterConfirmPass: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun registerUser() {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "http://192.168.43.195:8080/api/v1/auth/register"

        val jsonObject = JSONObject().apply {
            put("firstName", etRegisterName1.text.toString())
            put("lastName", etRegisterName2.text.toString())
            put("email", etRegisterEmail.text.toString())
            put("state", selectedState)
            put("city", selectedCity)
            put("password", etRegisterPass.text.toString())
        }

        val stringRequest = object : StringRequest(
            Method.POST, url,
            Response.Listener<String> { response ->
                if (response.contains("User registered successfully", ignoreCase = true)) {
                    etRegisterName1.setText("")
                    etRegisterName2.setText("")
                    etRegisterEmail.setText("")
                    etRegisterPass.setText("")
                    etRegisterConfirmPass.setText("")
                    stateSpinner.setSelection(0)
                    citySpinner.setSelection(0)
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG).show()
                    requireView().findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                }
            },

            Response.ErrorListener { error ->
                Toast.makeText(
                    requireContext(),
                    "Înregistrare ERROR ${error.message}",
                    Toast.LENGTH_LONG
                ).show()
            }) {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            @Throws(AuthFailureError::class)
            override fun getBody(): ByteArray? {
                return try {
                    jsonObject.toString().toByteArray(Charsets.UTF_8)
                } catch (uee: UnsupportedEncodingException) {
                    VolleyLog.wtf(
                        "Unsupported Encoding while trying to get the bytes of %s using %s",
                        jsonObject.toString(),
                        "utf-8"
                    )
                    null
                }
            }
        }
        queue.add(stringRequest)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stateSpinner = view.findViewById(R.id.state_Spinner)
        stateAdapter = ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.array_of_states,
            R.layout.spinner_layout
        )

        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        stateSpinner.adapter = stateAdapter

        stateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                citySpinner = requireView().findViewById(R.id.city_Spinner)
                selectedState = stateSpinner.selectedItem.toString()

                if (parent?.id == R.id.state_Spinner) {

                    when (selectedState) {
                        "Selectează județul" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.array_of_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Alba" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Alba_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Arad County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Arad_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Arges" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Arges_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Bacău County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Bacau_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Bihor County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Bihor_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Bistrița-Năsăud County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Bistrita_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Botoșani County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Botosani_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Braila" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Braila_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Brașov County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Brasov_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Bucharest" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Bucuresti_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Buzău County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Buzau_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Caraș-Severin County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.CarașSeverin_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Cluj County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Cluj_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Constanța County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Constanta_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Covasna County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Covasna_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Călărași County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Calarasi_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Dolj County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Dolj_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Dâmbovița County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Dambovita_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Galați County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Galati_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Giurgiu County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Giurgiu_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Gorj County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Gorj_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Harghita County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Harghita_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Hunedoara County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Hunedoara_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Ialomița County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Ialomita_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Iași County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Iasi_citites,
                                R.layout.spinner_layout
                            )
                        }

                        "Ilfov County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Ilfov_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Maramureș County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Maramures_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Mehedinți County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Mehedinti_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Mureș County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Mures_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Neamț County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Neamt_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Olt County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Olt_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Prahova County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Prahova_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Satu Mare County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.SatuMare_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Sibiu County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Sibiu_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Suceava County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Suceava_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Sălaj County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Salaj_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Teleorman County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Teleorman_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Timiș County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Timis_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Tulcea County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Tulcea_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Vaslui County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Vaslui_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Vrancea County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Vrancea_cities,
                                R.layout.spinner_layout
                            )
                        }

                        "Vâlcea County" -> {
                            cityAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.Valcea_cities,
                                R.layout.spinner_layout
                            )
                        }

                        else -> {
                            // code for handling other cases
                        }
                    }

                    cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    citySpinner.adapter = cityAdapter

                    citySpinner.onItemSelectedListener =
                        object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(
                                parent: AdapterView<*>?,
                                view: View?,
                                position: Int,
                                id: Long
                            ) {
                                selectedCity = citySpinner.selectedItem.toString()
                            }

                            override fun onNothingSelected(parent: AdapterView<*>?) {
                            }

                        }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        registerBtn = view.findViewById(R.id.registerButton)

        tvName1 = view.findViewById(R.id.tv_Name1)
        tvName2 = view.findViewById(R.id.tv_Name2)
        tvEmail = view.findViewById(R.id.tv_email)
        tvState = view.findViewById(R.id.tv_state)
        tvCity = view.findViewById(R.id.tv_city)
        tvPassword = view.findViewById(R.id.tv_password)
        tvConfPass = view.findViewById(R.id.tv_confirmPassword)
        etRegisterEmail = view.findViewById(R.id.registerEmailAddress)
        etRegisterPass = view.findViewById(R.id.registerPassword)
        etRegisterConfirmPass = view.findViewById(R.id.register_ConfirmPassword)
        etRegisterName1 = view.findViewById(R.id.registerName1)
        etRegisterName2 = view.findViewById(R.id.registerName2)

        registerBtn.setOnClickListener {

            val registerName1 = etRegisterName1.text.toString()
            val registerName2 = etRegisterName2.text.toString()
            val registerEmail = etRegisterEmail.text.toString()
            val registerPassword = etRegisterPass.text.toString()
            val confirmPassword = etRegisterConfirmPass.text.toString().trim()
            val passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$".toRegex()

            if (registerName1.isEmpty()) {
                tvName1.error = "Nume obligatoriu!"
                tvName1.requestFocus()
            } else if (registerName2.isEmpty()) {
                tvName2.error = "Prenume obligatoriu!"
                tvName2.requestFocus()
            } else if (registerEmail.isEmpty()) {
                tvEmail.error = "Email obligatoriu!"
                tvEmail.requestFocus()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(registerEmail).matches()) {
                Toast.makeText(requireContext(), "Introduceți un email valid!", Toast.LENGTH_LONG)
                    .show()
                tvEmail.error = "Email invalid!"
                tvEmail.requestFocus()
            } else if (selectedState == "Selectează județul") {
                Toast.makeText(
                    requireContext(),
                    "Selectează județul tău din listă",
                    Toast.LENGTH_LONG
                ).show()
                tvState.error = "Județ obligatoriu!"
                tvState.requestFocus()
            } else if (selectedCity == "Selectează orașul") {
                Toast.makeText(
                    requireContext(),
                    "Selectează orașul tău din listă",
                    Toast.LENGTH_LONG
                ).show()
                tvCity.error = "Oraș obligatoriu!"
                tvCity.requestFocus()
            } else if (registerPassword.isEmpty()) {
                tvPassword.error = "Parola obligatorie!"
                tvPassword.requestFocus()
            } else if (confirmPassword.isEmpty()) {
                tvConfPass.error = "Confirmare obligatorie!"
                tvConfPass.requestFocus()
            } else if (!passwordPattern.matches(registerPassword)) {
                Toast.makeText(
                    requireContext(),
                    "Parola trebuie să conțină cel puțin o literă și un număr, și să aibă cel puțin 6 caractere!",
                    Toast.LENGTH_LONG
                ).show()
                tvPassword.error = "Parolă invalidă!"
                tvPassword.requestFocus()
            } else if (registerPassword != confirmPassword) {
                Toast.makeText(
                    requireContext(),
                    "Parolele trebuie să fie identice!",
                    Toast.LENGTH_LONG
                ).show()
            }

                else {
                tvName1.error = null
                tvName2.error = null
                tvEmail.error = null
                tvState.error = null
                tvCity.error = null
                tvPassword.error = null
                tvConfPass.error = null
                registerUser()

            }
        }
    }
}

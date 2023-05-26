package com.example.greenpanion

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyLog
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.io.UnsupportedEncodingException

class LoginFragment : Fragment() {

    private lateinit var loginBtn: Button
    private lateinit var etLoginEmail: EditText
    private lateinit var etLoginPassword: EditText
    private lateinit var tvlogEmail: TextView
    private lateinit var tvlogPassword: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun loginUser() {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "http://192.168.43.195:8080/api/v1/auth/authenticate"

        val jsonObject = JSONObject().apply {
            put("email", etLoginEmail.text.toString())
            put("password", etLoginPassword.text.toString())

        }

        val stringRequest = object : StringRequest(
            Method.POST, url,
            Response.Listener<String> { response ->
                if (response.contains("User auth successfully", ignoreCase = true)) {
                    etLoginEmail.setText("")
                    etLoginPassword.setText("")

                    Toast.makeText(requireContext(), "Bine ai venit!", Toast.LENGTH_LONG).show()
                    requireView().findNavController()
                        .navigate(R.id.action_loginFragment_to_homeFragment)

                }
            },
            Response.ErrorListener
            { error ->
                Toast.makeText(
                    requireContext(),
                    "Autentificare ERROR",
                    Toast.LENGTH_LONG
                ).show()
            }
        ) {
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
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etLoginEmail = view.findViewById(R.id.loginEmailAddress)
        etLoginPassword = view.findViewById(R.id.loginPassword)
        loginBtn = view.findViewById(R.id.login_btn)
        tvlogEmail = view.findViewById(R.id.tv_logEmail)
        tvlogPassword = view.findViewById(R.id.tv_logPassword)

        loginBtn.setOnClickListener {
            val loginEmail = etLoginEmail.text.toString()
            val loginPassword = etLoginPassword.text.toString()

            if (loginEmail.isEmpty()) {
                tvlogEmail.error = "Email obligatoriu!"
                tvlogEmail.requestFocus()
            } else if (loginPassword.isEmpty()) {
                tvlogPassword.error = "Parolă obligatorie!"
                tvlogPassword.requestFocus()
            } else {
                tvlogEmail.error = null
                tvlogPassword.error = null
                loginUser()
            }
        }
    }
}
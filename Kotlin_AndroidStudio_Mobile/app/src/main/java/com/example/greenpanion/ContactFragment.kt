package com.example.greenpanion

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import java.util.Properties
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class ContactFragment : Fragment() {

    private lateinit var etEmailUser: EditText
    private lateinit var etSubject: EditText
    private lateinit var etMessage: EditText
    private lateinit var btnSendEmail: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etEmailUser = view.findViewById(R.id.et_emailUser)
        etSubject = view.findViewById(R.id.et_subject)
        etMessage = view.findViewById(R.id.et_message)
        btnSendEmail = view.findViewById(R.id.sendEmail_btn)

        btnSendEmail.setOnClickListener {
            val userEmail = etEmailUser.text.toString().trim()
            val subject = etSubject.text.toString().trim()
            val message = etMessage.text.toString().trim()

            if (validateInputs(userEmail, subject, message)) {
                val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:${Utils.EMAIL}")
                    putExtra(Intent.EXTRA_SUBJECT, subject)
                    putExtra(Intent.EXTRA_TEXT, message)
                }

                startActivity(Intent.createChooser(emailIntent, "Send Email"))
            }
        }
    }

    private fun validateInputs(email: String, subject: String, message: String): Boolean {
        if (email.isEmpty()) {
            etEmailUser.error = "Email obligatoriu!"
            etEmailUser.requestFocus()
            return false
        }

        if (subject.isEmpty()) {
            etSubject.error = "Subiect obligatoriu!"
            etSubject.requestFocus()
            return false
        }

        if (message.isEmpty()) {
            etMessage.error = "Mesaj obligatoriu!"
            etMessage.requestFocus()
            return false
        }

        return true
    }

    @SuppressLint("StaticFieldLeak")
    inner class JavaMailAPI(
        private val fromEmail: String,
        private val subject: String,
        private val message: String
    ) : AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg voids: Void): Void? {
            val properties = Properties()
            properties["mail.smtp.host"] = "smtp.gmail.com"
            properties["mail.smtp.socketFactory.port"] = "465"
            properties["mail.smtp.socketFactory.class"] = "javax.net.ssl.SSLSocketFactory"
            properties["mail.smtp.auth"] = "true"
            properties["mail.smtp.port"] = "465"
            properties["mail.smtp.ssl.trust"] = "smtp.gmail.com"

            val session = Session.getDefaultInstance(properties, object : javax.mail.Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(fromEmail, Utils.PASSWORD)
                }
            })

            val mimeMessage = MimeMessage(session)
            try {
                mimeMessage.setFrom(InternetAddress(fromEmail))
                mimeMessage.setRecipient(Message.RecipientType.TO, InternetAddress(Utils.EMAIL))
                mimeMessage.subject = subject
                mimeMessage.setText(message)
                Transport.send(mimeMessage)
            } catch (e: MessagingException) {
                e.printStackTrace()
            }

            return null
        }
    }
}
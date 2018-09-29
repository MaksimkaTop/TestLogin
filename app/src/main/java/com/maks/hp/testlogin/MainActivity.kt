package com.maks.hp.testlogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.maks.hp.testlogin.extensions.isValidEmail
import com.maks.hp.testlogin.extensions.isValidPassword
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var emailEnabled = false
    var passwordEnabled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkFocus()
    }

    private fun checkFocus() {
        tiet_email.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) validateEmail()
        }
        tiet_password.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) validatePassword()
        }
    }

    private fun validateEmail() {
        if (!tiet_email.text.toString().isValidEmail()) {
            til_email.error = getString(R.string.must_be_email)
            emailEnabled = false
        } else {
            emailEnabled = true
        }
    }

    private fun validatePassword() {
        if (!tiet_password.text.toString().isValidPassword()) {
            til_password.error = getString(R.string.password_error)
            passwordEnabled = false
        } else {
            passwordEnabled = true
        }
    }
}

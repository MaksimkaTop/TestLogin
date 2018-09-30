package com.maks.hp.testlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.content.ContextCompat
import com.maks.hp.testlogin.extensions.isValidEmail
import com.maks.hp.testlogin.extensions.isValidPassword
import kotlinx.android.synthetic.main.activity_main.*
import android.os.CountDownTimer
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    var emailEnabled = false
    var passwordEnabled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkFocusEmailAndPassword()
        handleEnterOnPassword()
        breakErrorEmail()
        breakErrorPassword()
    }

    // если теряем фокус, то производим поверку на ошибку
    private fun checkFocusEmailAndPassword() {
        tiet_email.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) checkOnErrorEmail()
        }
        tiet_password.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) checkOnErrorPassword()
        }
    }

    // проверка на ошибкку почты
    private fun checkOnErrorEmail(): Boolean {
        return if (!tiet_email.text.toString().isValidEmail()) {
            til_email.error = getString(R.string.email_error)
            emailEnabled = false
            handleLoginButtonState()
            true
        } else {
            false
        }
    }

    // проверка на ошибку пароля
    private fun checkOnErrorPassword(): Boolean {
        return if (!tiet_password.text.toString().isValidPassword()) {
            til_password.error = getString(R.string.password_error)
            passwordEnabled = false
            handleLoginButtonState()
            true
        } else {
            false
        }
    }

    // решил дедать снимать ошибку таким способом
    private fun breakErrorEmail() {
        tiet_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (tiet_email.text.toString().isValidEmail()) {
                    emailEnabled = true
                    til_email.isErrorEnabled = false
                    handleLoginButtonState()
                }
            }
        })
    }

    private fun breakErrorPassword() {
        tiet_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (tiet_password.editableText.isNotEmpty()) {
                    passwordEnabled = true
                    til_password.isErrorEnabled = false
                    handleLoginButtonState()
                }
            }
        })
    }

    // если в поле пароля нажать энтер то происходит проверка валидации пароля
    // потом можно сделать логин в случае успеха
    private fun handleEnterOnPassword() {
        tiet_password.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE)
                checkOnErrorPassword()
            false
        }
    }

    // состояния кнопки Login
    private fun handleLoginButtonState() {
        if (emailEnabled && passwordEnabled) {
            button_login.setBackgroundColor(ContextCompat.getColor(this, R.color.pass_green))
            button_login.setOnClickListener { setTimerBeforeLogin() }
        } else {
            button_login.setBackgroundColor(ContextCompat.getColor(this, R.color.error_red))
        }
    }

    //"кнопка отправляет запрос (запрос имитировать таймаутом),
    // в результате показывается окно ошибки либо приложение переходит на следующий экран"
    // отображать ошибку или переходить на экран решил реализовать рандомом
    private fun setTimerBeforeLogin() {
        object : CountDownTimer(3000, 100) {
            override fun onTick(millisUntilFinished: Long) {
                pb_login.visibility = View.VISIBLE
            }

            override fun onFinish() {
                pb_login.visibility = View.GONE
                if (random() >= 5) doLogin()
                else doError()
            }
        }.start()
    }

    private fun random(): Int = 0 + (Math.random() * 10).toInt() // сам рандом 1..10

    // еще раз проверяем валидацию, если все ок, то идем на след. экран
    private fun doLogin() {
        if (!checkOnErrorPassword() && !checkOnErrorEmail()) {
            val intent = Intent(this, NextActivity::class.java)
            startActivity(intent)
        }
    }

    private fun doError() {
        Snackbar.make(button_login, "Same Error", Snackbar.LENGTH_LONG).show()
    }
}

package com.maks.hp.testlogin.extensions

import android.util.Patterns

fun String.isValidEmail(): Boolean
        = this.isNotEmpty() &&
        Patterns.EMAIL_ADDRESS.matcher(this).matches()
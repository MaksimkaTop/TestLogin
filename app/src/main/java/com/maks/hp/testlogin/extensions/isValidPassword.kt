package com.maks.hp.testlogin.extensions

import java.util.regex.Pattern


fun String.isValidPassword(): Boolean =
        this.length in 6..40 &&
                Pattern.compile("[a-zA-Z0-9]+").matcher(this).matches()


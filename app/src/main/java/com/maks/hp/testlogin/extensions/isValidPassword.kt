package com.maks.hp.testlogin.extensions


fun String.isValidPassword(): Boolean = this.isNotEmpty()
        &&
        this.length in 6..40
//                &&
  //      this.matches(Regex("[^A-Za-z0-9 ]")))
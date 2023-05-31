package com.example.bookarchice.util

import android.graphics.Color
import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(message: String) {
    val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    snackBar.setTextColor(Color.WHITE)
    snackBar.setBackgroundTint(Color.RED)
    snackBar.show()
}
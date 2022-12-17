package com.sarvagya.android.extension

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Context.showSnackbar(
    view: View,
    length: Int = Snackbar.LENGTH_SHORT,
    @StringRes resId: Int,
    listener: View.OnClickListener? = null,
) {
    Snackbar.make(this, view, getString(resId), length)
        .setAction(resId, listener)
        .show()
}

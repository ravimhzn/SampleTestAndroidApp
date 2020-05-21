package com.ravimhzn.mightyapp.util.extension

import android.content.Context
import android.text.Editable
import android.widget.Toast
import androidx.annotation.StringRes
import com.afollestad.materialdialogs.MaterialDialog
import com.ravimhzn.mightyapp.R

fun Context.displayToast(@StringRes message: Int) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.displayToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.displaySuccessDialog(message: String) {
    MaterialDialog(this)
        .show {
            title(R.string.textSuccess)
            message(text = message)
            positiveButton {
                R.string.text_ok
            }
        }
}

fun Context.displayErrorDialog(message: String) {
    MaterialDialog(this)
        .show {
            title(R.string.textError)
            message(text = message)
            positiveButton {
                R.string.text_ok
            }
        }
}

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)


package org.pcc.arch_mvvm

import android.content.Context
import android.widget.Toast

object Utils {
    fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
package com.example.kotlin2lvl_2less.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.onChange(cb: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            cb(s.toString())
        }
    })
}

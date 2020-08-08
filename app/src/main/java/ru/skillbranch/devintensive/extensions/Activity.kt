package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


/*
 * Created by fooxer on 08.08.2020
 */

fun Activity.hideKeyboard(v: View?) {
    val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(v?.windowToken,InputMethodManager.HIDE_NOT_ALWAYS)

}

//TODO isKeyboardOpen/Closed
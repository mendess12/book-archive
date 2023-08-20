package com.example.bookarchice.util.extensions

import android.view.View


fun View.show(){
    visibility = View.VISIBLE
}

fun View.hide(){
    visibility = View.GONE
}
fun View.showOrHide(decision: Boolean){
    if (decision){
        show()
    }else{
        hide()
    }
}
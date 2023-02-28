package com.emreozel.contactbookapp.util

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.emreozel.contactbookapp.R

fun Navigation.gecisYap(id:Int,it:View){

    findNavController(it).navigate(id)
}
fun Navigation.gecisYap(it:View,id:NavDirections){

    findNavController(it).navigate(id)
}
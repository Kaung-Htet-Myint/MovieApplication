package com.example.myapplication.utils

sealed class ViewState<out T>{
    object Loading: ViewState<Nothing>()

    data class Successs<T>(val data: T): ViewState<T>()

    data class Error(val error: Exception): ViewState<Nothing>()
}
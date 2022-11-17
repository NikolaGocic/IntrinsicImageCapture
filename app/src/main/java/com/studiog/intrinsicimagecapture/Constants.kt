package com.studiog.intrinsicimagecapture

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object Constants {

    //const val BASE_API = "http://178.149.90.45:3000"
    const val BASE_API = "http://10.0.2.2:3000"

    fun isNetworkAvailable(context: Context): Boolean {
        val conManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val network = conManager.activeNetwork ?: return false
            val active = conManager.getNetworkCapabilities(network) ?: return false

            return when{
                active.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                active.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                active.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }

        } else {
            val network = conManager.activeNetworkInfo
            return network != null && network.isConnectedOrConnecting
        }
    }
}
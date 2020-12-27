package com.example.moviesappmvpkotlin.cache

import android.util.Log

object SharedPref {
    public val moviesIDMap = mutableSetOf<Int>()

    fun setMoviesIDMap(list: List<Int>) {
        for (elem in list) {
            moviesIDMap.add(elem)
        }
    }


    fun addValue(id: Int) {
        moviesIDMap.add(id)
        Log.d("isIndatabase", "bind: ${moviesIDMap.size}")

    }

    fun removeValue(id: Int) {
        moviesIDMap.remove(id)
        Log.d("isIndatabase", "bind: ${moviesIDMap.size}")

    }

    fun contain(id: Int): Boolean {
        return moviesIDMap.contains(id)
    }
}
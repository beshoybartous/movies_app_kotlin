package com.example.moviesappmvpkotlin.cache

object SharedPref {
    @JvmStatic
    val moviesIDMap = mutableSetOf<Int>()

    @JvmStatic
    fun setMoviesIDMap(list: List<Int>) {
        for (elem in list) {
            moviesIDMap.plus(elem)
        }
    }

    @JvmStatic
    fun addValue(id: Int) {
        moviesIDMap.plus(id)
    }

    @JvmStatic
    fun removeValue(id: Int) {
        moviesIDMap.remove(id)
    }

    @JvmStatic
    fun contain(id: Int): Boolean {
        return moviesIDMap.contains(id)
    }
}
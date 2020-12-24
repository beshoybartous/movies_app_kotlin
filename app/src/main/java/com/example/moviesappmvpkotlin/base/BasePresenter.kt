package com.example.moviesappmvpkotlin.base

import android.content.Context
import android.util.Log
import com.example.moviesappmvpkotlin.database.MovieDatabase
import com.example.moviesappmvpkotlin.network.NetworkManager
import kotlinx.coroutines.*

abstract class BasePresenter(val baseView: BaseView, open val context: Context) {
     val job = SupervisorJob()
     val coroutineScope = CoroutineScope(Dispatchers.Main + job)
    var networkManager = NetworkManager()
    val database by lazy { MovieDatabase.getDatabase(context) }
    var value = 0

    init {


//        coroutineScope.launch {
//            //     networkManager.getData(MovieResponse::class.java)
//            //   val list= database.movieDao().getMoviesId()
//            val a = async {
//                Log.d("logisisisi", "I'm computing a piece of the answer")
//                7
//            }
//            val b = async {
//                Log.d("logisisisi", "I'm computing another piece of the answer")
//                5
//            }
//            Log.d("logisisisi", b.javaClass.toString())
//            Log.d("logisisisi", "The answer is ${a.await() * b.await()}")
//            coroutineScope.launch(Dispatchers.IO) {
//                dosome()
//                Log.d("docoro", value.toString())
//            }
//            coroutineScope.launch(Dispatchers.IO) {
//                dosome()
//                Log.d("docoro", value.toString())
//            }
//            Log.d("docoro", "test")
//
//            coroutineScope.launch(Dispatchers.IO) {
//
//                Log.d("docoro", "test")
//            }
//
//        }
    }

    suspend fun dosome(): Int {
        delay(2000)
        value++
        return value
    }


}
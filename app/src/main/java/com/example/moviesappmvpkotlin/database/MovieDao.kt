package com.example.moviesappmvpkotlin.database

import androidx.room.*

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieEntity: MovieEntity):Long

    @Delete
    suspend fun delete(movieEntity: MovieEntity):Int

    @Query("Select id from movies where id= :id")
    suspend fun getSpecificMovie(id: Int): Int


    @Query("Select id from movies")
    suspend fun getMoviesId(): List<Int>?

    @Query("Select * from movies")
    suspend fun getMovies(): List<MovieEntity>?
}
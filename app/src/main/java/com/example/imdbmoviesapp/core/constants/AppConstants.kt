package com.example.imdbmoviesapp.core.constants

object AppConstants{
    const val BASEURL =  "https://api.themoviedb.org/3/"
    const val IMAGE_BASE_URL ="https://image.tmdb.org/t/p/original"
    const val API_KEY ="4e44d9029b1270a757cddc766a1bcb63"
    const val LANGUAGE ="en-US"

    fun getImageUrl(path:String): String = IMAGE_BASE_URL + path
}
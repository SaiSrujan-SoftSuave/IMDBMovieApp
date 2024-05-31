package com.example.imdbmoviesapp.domain.repository

import com.example.imdbmoviesapp.data.remote.dto.PersonDto
import retrofit2.http.Path

interface PersonRepository {
    suspend fun getPersonDetails(personId: Int): PersonDto
}
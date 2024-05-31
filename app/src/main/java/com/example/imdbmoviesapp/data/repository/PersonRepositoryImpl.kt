package com.example.imdbmoviesapp.data.repository

import com.example.imdbmoviesapp.data.remote.api.PersonsApi
import com.example.imdbmoviesapp.data.remote.dto.PersonDto
import com.example.imdbmoviesapp.domain.repository.PersonRepository
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(private val personsApi: PersonsApi) : PersonRepository {
    override suspend fun getPersonDetails(personId: Int): PersonDto {
        return personsApi.getPersonDetails(personId)
    }
}
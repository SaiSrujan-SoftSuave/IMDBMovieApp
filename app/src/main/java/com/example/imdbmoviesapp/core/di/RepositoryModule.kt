package com.example.imdbmoviesapp.core.di

import com.example.imdbmoviesapp.data.remote.api.MoviesApi
import com.example.imdbmoviesapp.data.remote.api.PersonsApi
import com.example.imdbmoviesapp.data.repository.MovieRepositoryImpl
import com.example.imdbmoviesapp.data.repository.PersonRepositoryImpl
import com.example.imdbmoviesapp.domain.repository.MovieRepository
import com.example.imdbmoviesapp.domain.repository.PersonRepository
import com.example.imdbmoviesapp.domain.usecases.getnowpalyingmovies.GetNowPlayingMovies
import com.example.imdbmoviesapp.domain.usecases.getpopularmovies.GetPopularMovies
import com.example.imdbmoviesapp.domain.usecases.gettoprated.GetTopRatedMovies
import com.example.imdbmoviesapp.domain.usecases.getupcomingmovies.GetUpcomingMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideMoviesRepository(moviesApi: MoviesApi): MovieRepository {
        return MovieRepositoryImpl(moviesApi)
    }

    @Provides
    @Singleton
    fun providePersonRepository(personsApi: PersonsApi): PersonRepository{
        return PersonRepositoryImpl(personsApi)
    }

    @Provides
    @Singleton
    fun proviedGetNowPlayingMoviesUseCase(movieRepository: MovieRepository): GetNowPlayingMovies{
        return GetNowPlayingMovies(movieRepository)
    }
    @Provides
    @Singleton
    fun provideGetPopularMoviesUseCase(movieRepository: MovieRepository): GetPopularMovies {
        return GetPopularMovies(movieRepository)
    }
    @Provides
    @Singleton
    fun getUpcomingMoviesUseCase(movieRepository: MovieRepository): GetUpcomingMovies = GetUpcomingMovies(movieRepository)
    @Provides
    @Singleton
    fun getTopRatedMoviesUseCase(movieRepository: MovieRepository): GetTopRatedMovies = GetTopRatedMovies(movieRepository)
}
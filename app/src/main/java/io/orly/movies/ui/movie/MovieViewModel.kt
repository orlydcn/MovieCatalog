package io.orly.movies.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import io.orly.movies.data.repository.MovieRepository
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    movieRepository: MovieRepository
) : ViewModel() {
    val movieId = MutableLiveData(0)
    val movie = movieId.switchMap { id ->
        movieRepository.loadMovieById(
            id
        )
    }

}
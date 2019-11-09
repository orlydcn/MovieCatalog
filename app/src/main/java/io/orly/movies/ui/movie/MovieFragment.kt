package io.orly.movies.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import io.orly.movies.data.Resource
import io.orly.movies.databinding.FragmentMovieDetailBinding
import io.orly.movies.di.Injectable
import io.orly.movies.di.factory.ViewModelFactory
import io.orly.movies.di.injectViewModel
import io.orly.movies.util.hide
import io.orly.movies.util.setTitle
import io.orly.movies.util.show
import javax.inject.Inject

class MovieFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MovieViewModel
    private lateinit var binding: FragmentMovieDetailBinding
    private val args: MovieFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        args.movieName?.let { setTitle(it) }
        viewModel.movieId.postValue(args.movieId)
        setHasOptionsMenu(true)
        setUI()
        return binding.root
    }

    private fun setUI() {
        viewModel.movie.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.hide()
                    binding.movie = result.data
                }
                Resource.Status.LOADING -> binding.progressBar.show()
                Resource.Status.ERROR -> {
                    binding.progressBar.hide()
                    Snackbar.make(binding.root, result.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }
}
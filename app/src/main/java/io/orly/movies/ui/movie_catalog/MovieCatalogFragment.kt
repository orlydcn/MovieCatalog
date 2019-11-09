package io.orly.movies.ui.movie_catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import io.orly.movies.data.Resource
import io.orly.movies.databinding.FragmentMovieCatalogBinding
import io.orly.movies.di.Injectable
import io.orly.movies.di.factory.ViewModelFactory
import io.orly.movies.di.injectViewModel
import io.orly.movies.ui.custom.VerticalSpaceItemDecoration
import io.orly.movies.util.hide
import io.orly.movies.util.setTitle
import io.orly.movies.util.show
import javax.inject.Inject

class MovieCatalogFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MovieCatalogViewModel
    private lateinit var binding: FragmentMovieCatalogBinding
    private val args: MovieCatalogFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        binding = FragmentMovieCatalogBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        val adapter = MovieCatalogAdapter()
        binding.rvMovie.addItemDecoration(VerticalSpaceItemDecoration(16))
        binding.rvMovie.adapter = adapter
        viewModel.categoryId.postValue(args.categoryId)
        args.categoryName?.let { setTitle(it) }
        setUI(adapter)
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun setUI(adapter: MovieCatalogAdapter) {
        viewModel.movies.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.hide()
                    result.data?.let { adapter.submitList(it) }
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
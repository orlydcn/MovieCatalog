package io.orly.movies.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import io.orly.movies.data.Resource
import io.orly.movies.databinding.FragmentCategoryBinding
import io.orly.movies.di.Injectable
import io.orly.movies.di.factory.ViewModelFactory
import io.orly.movies.di.injectViewModel
import io.orly.movies.ui.custom.VerticalSpaceItemDecoration
import io.orly.movies.util.hide
import io.orly.movies.util.show
import javax.inject.Inject

class CategoryFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: CategoryViewModel
    private lateinit var binding: FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        val adapter = CategoryAdapter()
        binding.rvCategory.addItemDecoration(VerticalSpaceItemDecoration(16))
        binding.rvCategory.adapter = adapter
        setUI(adapter)
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun setUI(adapter: CategoryAdapter) {
        viewModel.categories.observe(viewLifecycleOwner, Observer { result ->
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
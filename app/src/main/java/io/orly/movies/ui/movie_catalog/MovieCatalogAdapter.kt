package io.orly.movies.ui.movie_catalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.orly.movies.data.local.entities.MovieEntity
import io.orly.movies.databinding.ItemMovieBinding
import io.orly.movies.ui.category.CategoryFragmentDirections

class MovieCatalogAdapter :
    ListAdapter<MovieEntity, MovieCatalogAdapter.ViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(createOnClickListener(movie.id, movie.title), movie)
    }

    private fun createOnClickListener(id: Int, name: String): View.OnClickListener =
        View.OnClickListener {
            it.findNavController().navigate(
                MovieCatalogFragmentDirections.actionToMovieDetail(
                    id,
                    name
                )
            )
        }


    class ViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: MovieEntity) {
            binding.apply {
                onCardClick = listener
                movie = item
                executePendingBindings()
            }
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<MovieEntity>() {

    override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
        return oldItem == newItem
    }
}
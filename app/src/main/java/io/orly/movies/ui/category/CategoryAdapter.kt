package io.orly.movies.ui.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.orly.movies.data.local.entities.CategoryEntity
import io.orly.movies.databinding.ItemCategoryBinding

class CategoryAdapter : ListAdapter<CategoryEntity, CategoryAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(createOnClickListener(category.id, category.category), category)
    }

    private fun createOnClickListener(id: Int, name: String): View.OnClickListener =
        View.OnClickListener {
            it.findNavController().navigate(
                CategoryFragmentDirections.actionCategoryFragmentToMovieCatalogFragment(
                    id,
                    name
                )
            )
        }

    class ViewHolder(
        private val binding: ItemCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: CategoryEntity) {
            binding.apply {
                onCardClick = listener
                category = item
                executePendingBindings()
            }
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<CategoryEntity>() {

    override fun areItemsTheSame(oldItem: CategoryEntity, newItem: CategoryEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CategoryEntity, newItem: CategoryEntity): Boolean {
        return oldItem == newItem
    }
}
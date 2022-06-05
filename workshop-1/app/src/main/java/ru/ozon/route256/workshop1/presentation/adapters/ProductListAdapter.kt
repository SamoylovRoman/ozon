package ru.ozon.route256.workshop1.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.ozon.route256.workshop1.R
import ru.ozon.route256.workshop1.databinding.ProductListItemBinding
import ru.ozon.route256.workshop1.presentation.viewObject.ProductInListVO

class ProductListAdapter(
    private val onItemClicked: (String) -> Unit
) :
    ListAdapter<ProductInListVO, ProductListAdapter.MovieHolder>(DiffUtilCallback()) {

    inner class MovieHolder(private val binding: ProductListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(product: ProductInListVO) {
            with(binding) {
                Glide.with(root)
                    .load(product.image)
                    .placeholder(R.drawable.ic_download)
                    .error(R.drawable.ic_error)
                    .into(productIV)
                nameTV.text = product.name
                priceTV.text = product.price
                ratingView.rating = product.rating.toFloat()
                root.setOnClickListener {
                    onItemClicked(product.guid)
                }
            }
        }
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<ProductInListVO>() {
        override fun areItemsTheSame(oldItem: ProductInListVO, newItem: ProductInListVO): Boolean {
            return oldItem.guid == newItem.guid
        }

        override fun areContentsTheSame(
            oldItem: ProductInListVO,
            newItem: ProductInListVO
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder =
        MovieHolder(
            ProductListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(currentList[position])
    }
}
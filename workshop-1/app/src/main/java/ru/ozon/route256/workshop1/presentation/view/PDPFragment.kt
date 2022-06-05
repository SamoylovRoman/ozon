package ru.ozon.route256.workshop1.presentation.view

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import ru.ozon.route256.workshop1.R
import ru.ozon.route256.workshop1.databinding.PdpFragmentBinding
import ru.ozon.route256.workshop1.databinding.ProductListFragmentBinding
import ru.ozon.route256.workshop1.di.ServiceLocator
import ru.ozon.route256.workshop1.presentation.adapters.ProductListAdapter
import ru.ozon.route256.workshop1.presentation.viewModel.DetailProductViewModel
import ru.ozon.route256.workshop1.presentation.viewModel.ProductsViewModel
import ru.ozon.route256.workshop1.presentation.viewModel.viewModelCreator
import ru.ozon.route256.workshop1.presentation.viewObject.ProductInListVO

class PDPFragment() : Fragment() {
    private var _binding: PdpFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var productId: String

    private val vm: DetailProductViewModel by viewModelCreator {
        DetailProductViewModel(ServiceLocator().productsInteractor)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            productId = bundle.getString(ARG_PRODUCT_ID)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PdpFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservesState()
        getProductInfo()
    }

    private fun getProductInfo() {
        vm.getProductInfo(productId)
    }

    private fun initObservesState() {
        vm.product.observe(viewLifecycleOwner) { product ->
            setProductInfo(product)
        }
    }

    private fun setProductInfo(product: ProductInListVO) {
        with(binding) {
            Glide.with(root)
                .load(product.image)
                .placeholder(R.drawable.ic_download)
                .error(R.drawable.ic_error)
                .into(productIV)
            nameTV.text = product.name
            priceTV.text = product.name
            ratingView.rating = product.rating.toFloat()
        }
    }

    companion object {
        const val ARG_PRODUCT_ID = "Product id"

        fun newInstance(productId: String) =
            PDPFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PRODUCT_ID, productId)
                }
            }
    }
}
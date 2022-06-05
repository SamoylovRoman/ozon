package ru.ozon.route256.workshop1.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ru.ozon.route256.workshop1.R
import ru.ozon.route256.workshop1.databinding.ProductListFragmentBinding
import ru.ozon.route256.workshop1.di.ServiceLocator
import ru.ozon.route256.workshop1.presentation.adapters.ProductListAdapter
import ru.ozon.route256.workshop1.presentation.viewModel.ProductsViewModel
import ru.ozon.route256.workshop1.presentation.viewModel.viewModelCreator

class ProductsFragment : Fragment() {
    private var _binding: ProductListFragmentBinding? = null
    private val binding get() = _binding!!

    private var productAdapter: ProductListAdapter? = null

    private val vm: ProductsViewModel by viewModelCreator {
        ProductsViewModel(ServiceLocator().productsInteractor)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ProductListFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initProductList()
        vm.productLD.observe(viewLifecycleOwner) { newList ->
            productAdapter?.submitList(newList)
        }
    }

    private fun initProductList() {
        productAdapter = ProductListAdapter { idProduct ->
            parentFragmentManager.commit {
                addToBackStack(PDPFragment::class.java.simpleName)
                replace(R.id.fragmentContainer, PDPFragment.newInstance(idProduct))
            }
        }
        with(binding.productList) {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    companion object {
        fun newInstance() = ProductsFragment()
    }
}
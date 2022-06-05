package ru.ozon.route256.workshop1.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.ozon.route256.workshop1.domain.interactors.ProductsInteractor
import ru.ozon.route256.workshop1.presentation.viewObject.ProductInListVO

class DetailProductViewModel(private val interactor: ProductsInteractor) : ViewModel() {
    private val _product = MutableLiveData<ProductInListVO>()
    val product: LiveData<ProductInListVO>
        get() = _product

    fun getProductInfo(productId: String) {
        _product.postValue(interactor.getProductById(productId))
    }
}
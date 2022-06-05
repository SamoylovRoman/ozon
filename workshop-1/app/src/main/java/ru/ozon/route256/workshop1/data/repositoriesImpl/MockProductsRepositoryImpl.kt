package ru.ozon.route256.workshop1.data.repositoriesImpl

import ru.ozon.route256.workshop1.domain.repositories.ProductsRepository
import ru.ozon.route256.workshop1.presentation.viewObject.ProductInListVO
import ru.ozon.route256.workshop1.sources.productInListDTOs
import ru.ozon.route256.workshop1.sources.toVO

class MockProductsRepositoryImpl : ProductsRepository {
    override fun getProducts(): List<ProductInListVO> {
        return productInListDTOs.map {
            it.toVO()
        }
    }

    override fun getProductById(guid: String): ProductInListVO {
        return productInListDTOs.find {
            it.guid == guid
        }!!.toVO()
    }
}
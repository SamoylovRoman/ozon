package ru.ozon.route256.workshop1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import ru.ozon.route256.workshop1.presentation.view.ProductsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        showProductsFragment()
    }

    private fun showProductsFragment() {
        supportFragmentManager.commit {
            replace(R.id.fragmentContainer, ProductsFragment.newInstance())
        }
    }
}
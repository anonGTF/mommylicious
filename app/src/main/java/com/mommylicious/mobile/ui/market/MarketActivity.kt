package com.mommylicious.mobile.ui.market

import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import com.mommylicious.mobile.base.BaseActivity
import com.mommylicious.mobile.data.model.MarketItem
import com.mommylicious.mobile.databinding.ActivityMarketBinding

class MarketActivity : BaseActivity<ActivityMarketBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMarketBinding =
        ActivityMarketBinding::inflate

    private lateinit var marketAdapter: MarketAdapter

    override fun setup() {
        marketAdapter = MarketAdapter()
        with(binding.rvShop) {
            adapter = marketAdapter
            layoutManager =  GridLayoutManager(context, 2)
        }

        marketAdapter.differ.submitList(listOf(
            MarketItem("Popok bayi XXL", "1200xp", "https://kalcare.s3-ap-southeast-1.amazonaws.com/moch4/uploads/product/13326/13326_1624264389.3287.jpg"),
            MarketItem("Bubur bayi 1 pax", "1000xp", "https://assets.babyzania.com/image/cache/catalog/1/milna-bubur-bayi-sup-beras-merah-120g-kiri-800x800.jpg"),
            MarketItem("Pakaian bayi lucu", "800xp", "https://cf.shopee.co.id/file/6af6a8fc7df041324a3713f7b6d89833"),
            MarketItem("Jaring anti nyamuk", "600xp", "https://images.tokopedia.net/img/cache/500-square/product-1/2020/8/7/109964811/109964811_c5f8de05-48e3-4e0e-af77-6cb2ffbfa0d5_676_676.jpg"),
            MarketItem("Popok bayi XXL", "1200xp", "https://kalcare.s3-ap-southeast-1.amazonaws.com/moch4/uploads/product/13326/13326_1624264389.3287.jpg"),
            MarketItem("Bubur bayi 1 pax", "1000xp", "https://assets.babyzania.com/image/cache/catalog/1/milna-bubur-bayi-sup-beras-merah-120g-kiri-800x800.jpg"),
            MarketItem("Pakaian bayi lucu", "800xp", "https://cf.shopee.co.id/file/6af6a8fc7df041324a3713f7b6d89833"),
            MarketItem("Jaring anti nyamuk", "600xp", "https://images.tokopedia.net/img/cache/500-square/product-1/2020/8/7/109964811/109964811_c5f8de05-48e3-4e0e-af77-6cb2ffbfa0d5_676_676.jpg"),
            MarketItem("Popok bayi XXL", "1200xp", "https://kalcare.s3-ap-southeast-1.amazonaws.com/moch4/uploads/product/13326/13326_1624264389.3287.jpg"),
            MarketItem("Bubur bayi 1 pax", "1000xp", "https://assets.babyzania.com/image/cache/catalog/1/milna-bubur-bayi-sup-beras-merah-120g-kiri-800x800.jpg"),
            MarketItem("Pakaian bayi lucu", "800xp", "https://cf.shopee.co.id/file/6af6a8fc7df041324a3713f7b6d89833"),
            MarketItem("Jaring anti nyamuk", "600xp", "https://images.tokopedia.net/img/cache/500-square/product-1/2020/8/7/109964811/109964811_c5f8de05-48e3-4e0e-af77-6cb2ffbfa0d5_676_676.jpg"),
        ))
    }
}
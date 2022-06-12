package com.mommylicious.mobile.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mommylicious.mobile.base.BaseAdapter
import com.mommylicious.mobile.data.model.Article
import com.mommylicious.mobile.databinding.ItemArticleBinding
import com.squareup.picasso.Picasso

class ArticleAdapter : BaseAdapter<ItemArticleBinding, Article>() {
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> ItemArticleBinding =
        ItemArticleBinding::inflate

    override fun bind(binding: ItemArticleBinding, item: Article) {
        with(binding) {
            tvTitle.text = item.title
            Picasso.get().load(item.image).fit().into(imgThumbnail)
        }
    }
}
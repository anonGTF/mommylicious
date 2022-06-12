package com.mommylicious.mobile.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mommylicious.mobile.base.BaseFragment
import com.mommylicious.mobile.data.model.*
import com.mommylicious.mobile.data.model.Child.Companion.getAge
import com.mommylicious.mobile.databinding.FragmentHomeBinding
import com.mommylicious.mobile.ui.article.DetailArticleActivity
import com.mommylicious.mobile.ui.record.AddRecordActivity
import com.mommylicious.mobile.utils.getTimeLapse
import com.mommylicious.mobile.utils.orNow
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate

    private lateinit var articleAdapter: ArticleAdapter
    private lateinit var needAdapter: NeedAdapter
    private lateinit var menuAdapter: MenuAdapter
    private val viewModel: HomeViewModel by activityViewModels()

    override fun setup() {
        setupListener()
        setupRecyclerView()
        viewModel.getChild().observe(viewLifecycleOwner, setChildObserver())
        viewModel.getLatestRecord().observe(viewLifecycleOwner, setRecordObserver())
        viewModel.getArticles().observe(viewLifecycleOwner, setArticleObserver())
        viewModel.getNeed().observe(viewLifecycleOwner, setNeedObserver())
        viewModel.getMenu().observe(viewLifecycleOwner, setMenuObserver())
    }

    private fun setupRecyclerView() {
        articleAdapter = ArticleAdapter()
        with(binding.rvArticle) {
            adapter = articleAdapter
            layoutManager = LinearLayoutManager(binding.root.context)
        }

        articleAdapter.setOnItemClickListener {
            DetailArticleActivity.startActivity(requireActivity(), it.link)
        }

        needAdapter = NeedAdapter()
        with(binding.rvNeed) {
            adapter = needAdapter
            layoutManager = LinearLayoutManager(binding.root.context)
        }

        menuAdapter = MenuAdapter()
        with(binding.rvMenu) {
            adapter = menuAdapter
            layoutManager = LinearLayoutManager(binding.root.context)
        }
    }

    private fun setupListener() {
        binding.fabAddRecord.setOnClickListener {
            startActivity(Intent(binding.root.context, AddRecordActivity::class.java))
        }
    }

    private fun setArticleObserver() = setObserver<List<Article>?>(
        onSuccess = {
            articleAdapter.differ.submitList(it.data)
        }
    )

    private fun setNeedObserver() = setObserver<List<Need>?>(
        onSuccess = {
            needAdapter.differ.submitList(it.data)
        }
    )

    private fun setMenuObserver() = setObserver<List<Menu>?>(
        onSuccess = {
            menuAdapter.differ.submitList(it.data)
        }
    )

    private fun setChildObserver() = setObserver<Child?>(
        onSuccess = {
            populateChildInfo(it.data)
        }
    )

    private fun setRecordObserver() = setObserver<List<Record>?>(
        onSuccess = {
            if ((it.data?.size ?: 0) > 0) {
                populateRecordInfo(it.data?.get(0))
            }
        }
    )

    private fun populateRecordInfo(data: Record?) {
        with(binding) {
            tvLastHeight.text = data?.height.toString()
            tvLastWeight.text = data?.weight.toString()
            tvLastHead.text = data?.head.toString()
            tvLastRecord.text = getTimeLapse(data?.date.orNow(), dayMode = true)
        }
    }

    private fun populateChildInfo(data: Child?) {
        with(binding) {
            tvName.text = data?.name.orEmpty()
            tvAge.text = data?.getAge()

            Picasso.get().load(data?.imageUrl).into(imgBabyProfile)
        }
    }
}
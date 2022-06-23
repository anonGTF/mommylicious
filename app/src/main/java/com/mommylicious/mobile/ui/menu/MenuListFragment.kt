package com.mommylicious.mobile.ui.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mommylicious.mobile.base.BaseFragment
import com.mommylicious.mobile.data.model.Menu
import com.mommylicious.mobile.databinding.FragmentMenuListBinding
import com.mommylicious.mobile.ui.home.MenuAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuListFragment(private val type: String) : BaseFragment<FragmentMenuListBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMenuListBinding =
        FragmentMenuListBinding::inflate

    private lateinit var menuAdapter: MenuAdapter
    private val viewModel: MenuViewModel by activityViewModels()

    override fun setup() {
        setupRecyclerView()
        viewModel.getMenuByType(type).observe(viewLifecycleOwner, setMenuObserver())
    }

    private fun setMenuObserver() = setObserver<List<Menu>?>(
        onSuccess = { menuAdapter.differ.submitList(it.data) }
    )

    private fun setupRecyclerView() {
        menuAdapter = MenuAdapter()
        with(binding.rvMenu) {
            layoutManager = LinearLayoutManager(context)
            adapter = menuAdapter
        }

        menuAdapter.setOnItemClickListener {
            DetailMenuActivity.startActivity(requireContext(), it)
        }
    }
}
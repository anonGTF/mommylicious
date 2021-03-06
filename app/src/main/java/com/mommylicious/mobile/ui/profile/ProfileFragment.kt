package com.mommylicious.mobile.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.mommylicious.mobile.R
import com.mommylicious.mobile.base.BaseFragment
import com.mommylicious.mobile.data.model.Child
import com.mommylicious.mobile.data.model.User
import com.mommylicious.mobile.databinding.FragmentProfileBinding
import com.mommylicious.mobile.ui.auth.LoginActivity
import com.mommylicious.mobile.ui.child.AddChildActivity
import com.mommylicious.mobile.ui.child.DetailChildActivity
import com.mommylicious.mobile.ui.market.MarketActivity
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProfileBinding
        = FragmentProfileBinding::inflate

    private lateinit var childAdapter: ChildAdapter
    private val viewModel: ProfileViewModel by activityViewModels()
    private var data: User? = null

    override fun setup() {
        setupRecyclerView()
        viewModel.getProfile().observe(viewLifecycleOwner, setProfileObserver())
        viewModel.getChildren().observe(viewLifecycleOwner, setChildrenObserver())

//        binding.btnEditProfil.setOnClickListener {
//            val intent = Intent(binding.root.context, EditProfileActivity::class.java)
//            intent.putExtra(EditProfileActivity.EXTRA_DATA, data)
//            startActivity(intent)
//        }

        binding.btnAddChild.setOnClickListener {
            startActivity(Intent(binding.root.context, AddChildActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            viewModel.logout().observe(viewLifecycleOwner, setLogoutObserver())
        }

        binding.btnTukar.setOnClickListener {
            startActivity(Intent(binding.root.context, MarketActivity::class.java))
        }
    }

    private fun setChildrenObserver() = setObserver<List<Child>?>(
        onSuccess = {
            childAdapter.differ.submitList(it.data)
        }
    )

    private fun setupRecyclerView() {
        childAdapter = ChildAdapter()
        with(binding.rvChildren) {
            adapter = childAdapter
            layoutManager = LinearLayoutManager(binding.root.context)
        }

        childAdapter.setOnItemClickListener {
            DetailChildActivity.startActivity(requireActivity(), it.childId)
        }
    }

    private fun setProfileObserver() = setObserver<User?>(
        onSuccess = { response ->
            binding.progressBar.gone()
            data = response.data
            populateData()
        },
        onError = { response ->
            binding.progressBar.gone()
            showToast(response.message.toString())
        },
        onLoading = { binding.progressBar.visible() }
    )

    private fun populateData() {
        binding.apply {
            tvName.text = data?.name
            tvEmail.text = viewModel.getUserEmail()
//            tvBirthdate.text = data?.birthDate
//            tvGender.text = data?.gender?.toGender()
//            tvLocation.text = data?.address

//            Picasso.get().load(data?.imageUrl).into(ivPhotoProfile)
        }
    }

    private fun setLogoutObserver() = setObserver<Unit?>(
        onSuccess = {
            startActivity(Intent(binding.root.context, LoginActivity::class.java))
        },
        onError = { response ->
            showToast(response.message.toString())
        }
    )
}
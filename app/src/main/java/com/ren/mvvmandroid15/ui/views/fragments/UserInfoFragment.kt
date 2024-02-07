package com.ren.mvvmandroid15.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ren.mvvmandroid15.R
import com.ren.mvvmandroid15.databinding.FragmentUserInfoBinding
import com.ren.mvvmandroid15.ui.viewmodels.UserViewModel

class UserInfoFragment : Fragment() {

    private var _binding: FragmentUserInfoBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onShowButtonPressed()
        subscribeToUserInfo()
        onGoButtonPressed()
    }

    private fun onShowButtonPressed() {
        binding.btnShow.setOnClickListener {
            viewModel.getUserInfo()
        }
    }

    private fun subscribeToUserInfo() = with(binding) {
        viewModel.userInfoLiveData.observe(viewLifecycleOwner) { uiState ->
            uiState?.let {
                it.success?.let { user ->
                    tvUsername.text = user.name
                    tvAge.text = user.age.toString()
                    tvEmail.text = user.email
                    tvPassword.text = user.password
                }
            }
        }
    }

    private fun onGoButtonPressed() {
        binding.btnGo.setOnClickListener {
            findNavController().navigate(R.id.editUserInfoFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
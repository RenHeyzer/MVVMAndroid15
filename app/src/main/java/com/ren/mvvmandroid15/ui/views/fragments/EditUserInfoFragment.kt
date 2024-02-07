package com.ren.mvvmandroid15.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ren.mvvmandroid15.data.models.User
import com.ren.mvvmandroid15.databinding.FragmentEditUserInfoBinding
import com.ren.mvvmandroid15.ui.viewmodels.UserViewModel

class EditUserInfoFragment : Fragment() {

    private var _binding: FragmentEditUserInfoBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditUserInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToUserInfo()
        onEditButtonPressed()
    }

    private fun subscribeToUserInfo() = with(binding) {
        viewModel.getUserInfo()
        viewModel.userInfoLiveData.observe(viewLifecycleOwner) { uiState ->
            uiState?.let {
                it.success?.let { user ->
                    username.setText(user.name)
                    age.setText(user.age.toString())
                    email.setText(user.email)
                    password.setText(user.password)
                }
            }
        }
    }


    private fun onEditButtonPressed() = with(binding) {
        btnEdit.setOnClickListener {
            val username = username.text.toString().trim()
            val age = age.text.toString().trim()
            val email = email.text.toString().trim()
            val password = password.text.toString().trim()

            val user = User(
                name = username,
                age = age.toInt(),
                email = email,
                password = password
            )

            viewModel.saveUserInfo(user)
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
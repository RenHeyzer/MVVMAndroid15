package com.ren.mvvmandroid15.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ren.mvvmandroid15.R
import com.ren.mvvmandroid15.data.models.User
import com.ren.mvvmandroid15.databinding.FragmentRegistrationBinding
import com.ren.mvvmandroid15.ui.viewmodels.UserViewModel

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveUser()
    }

    private fun saveUser() = with(binding) {
        btnSave.setOnClickListener {
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
            findNavController().navigate(R.id.userInfoFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
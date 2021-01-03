package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate fragment
        val binding: FragmentWelcomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)

        // Get params from LoginFragment
        var args = WelcomeFragmentArgs.fromBundle(arguments!!)
        val username = args.username
        val welcomeMessage = args.welcome

        // Set text views
        binding.welcomeTextView.text = welcomeMessage
        binding.nameTextView.text = resources.getString(R.string.name_text, username) // insert username into predefined string with exclamation points

        // Instructions button onclick
        binding.instructionsButton.setOnClickListener { v: View ->
            v.findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToInstructionsFragment())
        }

        return binding.root
    }

}
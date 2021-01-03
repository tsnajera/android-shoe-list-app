package com.udacity.shoestore

import android.os.Bundle
import android.os.Vibrator
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.ActivityMainBinding
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate fragment layout and get binding object
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        // Set up onclick listener for login button. When it is clicked use navigation action
        binding.loginButton.setOnClickListener { v: View ->
            if(isValidInput()) {
                v.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(binding.usernameEditText.text.toString(), resources.getString(R.string.welcome_back_text)))
                hideKeyboard()
            }
        }

        // Set up onclick listener for login button. When it is clicked use navigation action
        binding.createButton.setOnClickListener { v: View ->
            if(isValidInput()) {
                v.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(binding.usernameEditText.text.toString(), resources.getString(R.string.welcome_text)))
                hideKeyboard()
            }
        }

        return binding.root
    }

    private fun hideKeyboard(){
        val keyboard = activity?.getSystemService<InputMethodManager>()
        keyboard?.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    // Validate whether username AND password entered
    private fun isValidInput(): Boolean {
        var isValid = (binding.usernameEditText.text.isNotEmpty() && binding.passwordEditText.text.isNotEmpty())
        if(!isValid)
            Toast.makeText(activity, resources.getString(R.string.validate_text), Toast.LENGTH_SHORT).show()
        return isValid
    }

}
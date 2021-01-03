package com.udacity.shoestore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.navigateUp
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe

class ShoeDetailFragment : Fragment() {

    private lateinit var viewModel: ShoeListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate
        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        // Get existing ShoeListViewModel object so that we may access the shoe list
        viewModel = ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)

        // Bind the viewModel data var in the layout to the ShoeListViewModel
        binding.shoeListViewModel = viewModel

        // Bind shoe object in layout to the shoe object in the ViewModel. When layout shoe object changes, it will automatically update ViewModel shoe object as they are 2way bound
        binding.shoe = viewModel.shoe.value

        // Observe shoeDetailComplete boolean so that we know when to navigate and hide keyboard. onlick save in layout xml will now automatically add shoe to shoeList
        viewModel.shoeDetailComplete.observe(viewLifecycleOwner, Observer { shoeDetailComplete ->
            if (shoeDetailComplete) {
                NavHostFragment.findNavController(this).navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
                viewModel.shoeDetailIncomplete()
                viewModel.clearShoe()
                hideKeyboard()
            }
        })

        // Cancel button listener
        binding.cancelButton.setOnClickListener { v: View ->
            v.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
            hideKeyboard()
        }

        return binding.root
    }

    private fun hideKeyboard(){
        val keyboard = activity?.getSystemService<InputMethodManager>()
        keyboard?.hideSoftInputFromWindow(view?.windowToken, 0)
    }

}
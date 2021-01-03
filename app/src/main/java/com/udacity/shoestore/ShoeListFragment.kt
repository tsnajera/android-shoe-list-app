package com.udacity.shoestore

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeListItemBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.fragment_instructions.*

class ShoeListFragment : Fragment() {

    private lateinit var viewModel: ShoeListViewModel
    lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate fragment
        val binding: FragmentShoeListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        // Set nav controller for use in Menu overridden methods
        navController = findNavController();

        // Get ShoeListViewModel object from ShoeListViewModel class
        viewModel = ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)

        // Set up observer for shoeList
        viewModel.shoeList.observe(viewLifecycleOwner, Observer { shoeList ->
            for (shoe in shoeList) {

                // Inflate a shoe_list_item.xml layout
                val listItemBinding = ShoeListItemBinding.inflate(layoutInflater, null, false)

                // Set shoe data variable from shoe_list_item.xml to shoe object from array. This auto sets fields in shoe_list_item.xml since views are data bound with data variable object
                listItemBinding.shoe = shoe

                // Add shoe_list_item.xml with shoe values into layout
                binding.shoeListLayout.addView(listItemBinding.root)

            }
        })

        // Floating action button listener
        binding.fab.setOnClickListener { v: View ->
            v.findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }

        // Enable options menu on this fragment for logout
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                viewModel.clearShoeList()
                navController.navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
package com.example.nikeapp.screens.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.nikeapp.databinding.FragmentHomeBinding
import com.example.nikeapp.utils.convertDpToPixel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject



@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    @Inject lateinit var sliderAdapter: SliderAdapter
    @Inject lateinit var latestProductAdapter: LatestProductsAdapter
    @Inject lateinit var popularProductAdapter: PopularProductsAdapter
    private val pagerHelper: PagerSnapHelper by lazy { PagerSnapHelper() }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            homeViewModel.sliderLiveData.observe(viewLifecycleOwner) {
                sliderAdapter.differ.submitList(it)
                sliderRv.adapter = sliderAdapter
                pagerHelper.attachToRecyclerView(sliderRv)
                sliderIndicator.attachToRecyclerView(sliderRv, pagerHelper)
            }

            homeViewModel.latestProductLiveData.observe(viewLifecycleOwner) {
                latestProductAdapter.differ.submitList(it)
                latestProductsRv.adapter = latestProductAdapter
            }

            homeViewModel.popularProductLiveData.observe(viewLifecycleOwner) {
                popularProductAdapter.differ.submitList(it)
                popularProductsRv.adapter = popularProductAdapter
            }




            //Click
            latestProductAdapter.setOnItemClickListener {
                val direction = HomeFragmentDirections.actionToDetail(it)
                findNavController().navigate(direction)
            }

        }
    }
}
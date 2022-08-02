package com.example.nikeapp.screens.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.nikeapp.databinding.FragmentDetailBinding
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private val detailViewModel :DetailViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity?)!!.apply {
            setSupportActionBar(binding.toolbarDetail)
            binding.collapsingDetail.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent))
            binding.collapsingDetail.setCollapsedTitleTextColor(ContextCompat.getColor(this, android.R.color.black))
            supportActionBar!!.setHomeButtonEnabled(true)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailViewModel.productLiveData.observe(viewLifecycleOwner) { product ->
            binding.apply {
                productImg.load(product.image) {
                    crossfade(true)
                    crossfade(1000)
                }
                productTitleTv.text = product.title
                previousPriceTv.text = product.previous_price.toString()
                currentPriceTv.text = product.price.toString()

                collapsingDetail.isTitleEnabled = true
                collapsingDetail.setExpandedTitleColor(ContextCompat.getColor(requireContext(), android.R.color.transparent))
                toolbarDetail.title = product.title
                toolbarDetail.setTitleTextColor(ContextCompat.getColor(requireContext(), android.R.color.black))
            }
        }

    }



    private fun translateAnimation(attr: View) {
        val anim = TranslateAnimation(
            0f, 0f,
            0f, -100f
        )
        anim.duration = 1000
        anim.fillAfter = true
        attr.startAnimation(anim)

    }
}
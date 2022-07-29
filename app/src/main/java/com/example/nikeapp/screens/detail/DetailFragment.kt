package com.example.nikeapp.screens.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.nikeapp.databinding.FragmentDetailBinding
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity?)!!.apply {
            setSupportActionBar(binding.toolbarDetail)
            supportActionBar!!.setHomeButtonEnabled(true)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        binding.apply {
            var isShow = true
            var scrollRange = -1
            appbarDetail.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
                if (scrollRange == -1) scrollRange = barLayout?.totalScrollRange!!
                if (scrollRange + verticalOffset == 0){
                    collapsingDetail.title = "Title Collapse"
                    isShow = true
                } else if (isShow){
                    collapsingDetail.title = " " //careful there should a space between double quote otherwise it wont work
                    isShow = false
                }
            })
        }

        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {


        }
    }
}
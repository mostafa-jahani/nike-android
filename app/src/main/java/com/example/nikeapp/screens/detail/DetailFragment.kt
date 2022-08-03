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
import com.example.nikeapp.utils.ObservableScroll.ObservableScrollViewCallbacks
import com.example.nikeapp.utils.ObservableScroll.ScrollState
import com.example.nikeapp.utils.formatPrice
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

        }

        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            productImg.post {
                observableScrollView.addScrollViewCallbacks(object : ObservableScrollViewCallbacks {
                    override fun onScrollChanged(scrollY: Int, firstScroll: Boolean, dragging: Boolean) {
                        toolbarView.alpha = scrollY.toFloat() / productImg.height.toFloat()
                        productImg.translationY = scrollY.toFloat() / 2
                    }
                    override fun onDownMotionEvent() {
                    }
                    override fun onUpOrCancelMotionEvent(scrollState: ScrollState?) {
                    }
                })
            }

            detailViewModel.productLiveData.observe(viewLifecycleOwner) { product ->
                productImg.load(product.image) {
                    crossfade(true)
                    crossfade(1000)
                }
                productTitleTv.text = product.title
                previousPriceTv.text = formatPrice(product.previous_price)
                currentPriceTv.text = formatPrice(product.price)
                toolbarTitleTv.text = product.title
            }

        }

    }

}
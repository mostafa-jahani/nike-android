package com.example.nikeapp.utils

import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import java.text.DecimalFormat


fun formatPrice(
    price: Number,
    unitRelativeSizeFactor: Float = 0.7f
): SpannableString {
    val df = DecimalFormat("0,000")
    val total = df.format(price)
    val currencyLabel = "تومان"
    val spannableString = SpannableString("$total $currencyLabel")
    spannableString.setSpan(
        RelativeSizeSpan(unitRelativeSizeFactor),
        spannableString.indexOf(currencyLabel),
        spannableString.length,
        SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return spannableString
}
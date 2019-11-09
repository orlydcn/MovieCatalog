package io.orly.movies.util

import android.graphics.Color
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView

@BindingAdapter("cardBackgroundFromString")
fun bindCardBackgroundFromString(card: MaterialCardView, color: String?) {
    if (color == null) {
        card.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"))
    } else {
        card.setCardBackgroundColor(
            Color.parseColor(
                ColorTransparentUtil.convertIntoColor(
                    color,
                    50
                )
            )
        )
    }
}

fun ProgressBar.hide() {
    visibility = View.GONE
}

fun ProgressBar.show() {
    visibility = View.VISIBLE
}
fun Fragment.setTitle(title: String) {
    (activity as AppCompatActivity).supportActionBar!!.title = title
}
package com.example.greenpanion

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.PagerAdapter

class ViewPager(private val context: Context) : PagerAdapter() {

    private val images = intArrayOf(
        R.drawable.undraw_takeout_boxes_ap54,
        R.drawable.undraw_gift,
        R.drawable.undraw_scan,
        R.drawable.undraw_world
    )

    private val titles = intArrayOf(
        R.string.title_step1,
        R.string.title_step2,
        R.string.title_step3,
        R.string.title_step4
    )

    private val descriptions = intArrayOf(
        R.string.description_step1,
        R.string.description_step2,
        R.string.description_step3,
        R.string.description_step4
    )

    override fun getCount(): Int {
        return titles.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as LinearLayout
    }

    @SuppressLint("ServiceCast")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.pager_layout, container, false)

        val slideTitleImage = view.findViewById<ImageView>(R.id.view_step1)
        val slideHeading = view.findViewById<TextView>(R.id.title_step1)
        val slideDescription = view.findViewById<TextView>(R.id.description_step1)

        slideTitleImage.setImageResource(images[position])
        slideHeading.setText(titles[position])
        slideDescription.setText(descriptions[position])

        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

}
package com.example.greenpanion

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import androidx.viewpager.widget.ViewPager

class HowRecycleFragment : Fragment() {

    private lateinit var slidePager: ViewPager
    private lateinit var layoutPager: LinearLayout

    private lateinit var steps: Array<TextView>
    private lateinit var viewPagerAdapter: PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_how_recycle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        slidePager = view.findViewById(R.id.viewpager_steps)
        layoutPager = view.findViewById(R.id.indicator_layout)

        viewPagerAdapter = com.example.greenpanion.ViewPager(requireContext())
        slidePager.adapter = viewPagerAdapter

        setUpIndicator(0)
        slidePager.addOnPageChangeListener(viewListener)

    }


    private fun setUpIndicator(position: Int) {
        steps = Array(4) { TextView(requireContext()) }
        layoutPager.removeAllViews()

        for (i in steps.indices) {
            steps[i] = TextView(requireContext())
            steps[i].text = Html.fromHtml("&#8226;").toString()
            steps[i].textSize = 35f
            steps[i].setTextColor(resources.getColor(R.color.inactive, requireContext().theme))
            layoutPager.addView(steps[i])
        }

        steps[position].setTextColor(resources.getColor(R.color.active, requireContext().theme))
    }

    private val viewListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            setUpIndicator(position)
        }

        override fun onPageScrollStateChanged(state: Int) {
        }
    }

}
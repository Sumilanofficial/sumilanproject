package com.matrix.myjournal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.matrix.myjournal.databinding.FragmentInsightBinding
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

class InsightFragment : Fragment() {

    private var binding: FragmentInsightBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInsightBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup ViewPager2
        val pagerAdapter = ChartPagerAdapter(this)
        binding?.viewPager?.adapter = pagerAdapter

        // Setup page indicator
        val pageIndicator: WormDotsIndicator? = binding?.pageIndicator
        binding?.viewPager?.let { pageIndicator?.setViewPager2(it) }
    }


}

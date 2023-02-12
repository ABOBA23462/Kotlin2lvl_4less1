package com.example.kotlin2lvl_2less.ui.fragments.onboard
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.kotlin2lvl_2less.databinding.FragmentOnBoardBinding
import com.example.kotlin2lvl_2less.ui.adapter.OnBoardViewPagerAdapter
import com.example.kotlin2lvl_2less.utils.PreferenceHelper

class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
    }

    private fun initialize() {
        binding.viewPager2.adapter = OnBoardViewPagerAdapter(this@OnBoardFragment)
        binding.dotsIndicator.attachTo(binding.viewPager2)
    }

    private fun setupListener() = with(binding) {
        nextButton.setOnClickListener {
            with(viewPager2) {
                if (currentItem < 2) {
                    setCurrentItem(viewPager2.currentItem + 1, true)
                }
            }
        }
        binding.start.setOnClickListener {
            val preferenceHelper = PreferenceHelper()
            preferenceHelper.unit(requireContext())
            preferenceHelper.onSaveOnBoardState = false
            findNavController().navigate(OnBoardFragmentDirections.actionOnBoardFragmentToNoteAppFragment())
        }
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        nextButton.isVisible = true
                        start.isVisible = false
                    }
                    1 -> {
                        nextButton.isVisible = true
                        start.isVisible = false
                    }
                    2 -> {
                        nextButton.isVisible = false
                        start.isVisible = true
                    }
                }
                super.onPageSelected(position)
            }
        })
    }
}

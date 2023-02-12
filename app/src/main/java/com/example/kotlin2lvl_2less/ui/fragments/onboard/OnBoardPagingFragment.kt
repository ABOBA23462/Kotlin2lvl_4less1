package com.example.kotlin2lvl_2less.ui.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlin2lvl_2less.R
import com.example.kotlin2lvl_2less.databinding.FragmentOnBoardPagingBinding

class OnBoardPagingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardPagingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardPagingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() = with(binding) {
        when (requireArguments().getInt(ARG_ONBOARD_PAGE_POSITION)) {
            0 -> {
                tvAppDescription.text = "Очень удобный функционал"
                lottieAppDescription.setAnimation(R.raw.functional)
            }
            1 -> {
                tvAppDescription.text = "Быстрый, и качественный продукт"
                lottieAppDescription.setAnimation(R.raw.quality)
            }
            2 -> {
                tvAppDescription.text = "Куча функций и интересных фишек"
                lottieAppDescription.setAnimation(R.raw.morefunctional)
            }
        }
    }

    companion object {
        const val ARG_ONBOARD_PAGE_POSITION = "onBoardPage"
    }
}

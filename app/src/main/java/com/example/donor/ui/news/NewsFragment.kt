package com.example.donor.ui.news

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.lifecycle.ViewModelProvider
import com.example.donor.R
import com.example.donor.databinding.FragmentNewsBinding
import com.example.donor.databinding.FragmentSlideshowBinding
import com.example.donor.ui.slideshow.SlideshowViewModel

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: NewsViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(NewsViewModel::class.java)

        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val hom: WebView = binding.webnews
        hom.loadUrl("https://ookspk.orb.ru/presscenter/news/")
        return root

    }
}
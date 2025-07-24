package com.example.donor.ui.mif

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.lifecycle.ViewModelProvider
import com.example.donor.R
import com.example.donor.databinding.FragmentMifBinding
import com.example.donor.databinding.FragmentNewsBinding
import com.example.donor.ui.news.NewsViewModel

class MifFragment : Fragment() {
    private var _binding: FragmentMifBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: MifViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(NewsViewModel::class.java)

        _binding = FragmentMifBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val hom: WebView = binding.webmif
        hom.loadUrl("file:///android_asset/mif.html")
        return root

    }
}
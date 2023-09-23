package com.example.discover.fragments

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.discover.R
import com.example.discover.activities.DetailedActivity
import com.example.discover.activities.NewsApplication
import com.example.discover.adapters.AllNewsAdapter
import com.example.discover.adapters.ViewPagerAdapter
import com.example.discover.databinding.FragmentHomeBinding
import com.example.discover.models.Article
import com.example.discover.utils.Constants.Companion.AUTHOR
import com.example.discover.utils.Constants.Companion.DATE
import com.example.discover.utils.Constants.Companion.DESCRIPTION
import com.example.discover.utils.Constants.Companion.HEADING
import com.example.discover.utils.Constants.Companion.IMAGE_URL
import com.example.discover.utils.Constants.Companion.SOURCE
import com.example.discover.utils.OnNewsClickListener
import com.example.discover.utils.ViewPagerTransition
import com.example.discover.viewModels.NewsViewModel
import com.example.discover.viewModels.NewsViewModelFactory
import com.facebook.shimmer.ShimmerFrameLayout

class HomeFragment : Fragment(), OnNewsClickListener {

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var binding: FragmentHomeBinding

    private val allNewsAdapter by lazy { AllNewsAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val newsRepository = (activity?.applicationContext as NewsApplication).newsRepository
        newsViewModel = ViewModelProvider(this, NewsViewModelFactory(newsRepository, "india", "publishedAt"))[NewsViewModel::class.java]

        binding.tvViewAll.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

        //top-headlines
        ViewPagerTransition.transitions(binding.viewPager)

        newsViewModel.topHeadlinesData.observe(viewLifecycleOwner, Observer {
            binding.shimmerViewContainerViewPager.stopShimmer()
            binding.shimmerViewContainerViewPager.visibility = View.GONE
            binding.viewPager.adapter = ViewPagerAdapter(it.articles, this)
        })

        //All News
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = allNewsAdapter

        newsViewModel.allNewsData.observe(viewLifecycleOwner, Observer {
            binding.shimmerViewContainer.stopShimmer()
            binding.shimmerViewContainer.visibility = View.GONE
            allNewsAdapter.setData(it.articles)
        })
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onNewsItemClickListener(position: Int, article: Article) {
        val intent = Intent(activity?.applicationContext, DetailedActivity::class.java)
        intent.putExtra(AUTHOR, article.author)
        intent.putExtra(HEADING, article.title)
        intent.putExtra(DATE, article.publishedAt.substring(0, 10))
        intent.putExtra(IMAGE_URL, article.urlToImage)
        intent.putExtra(SOURCE, article.source.name)
        intent.putExtra(DESCRIPTION, article.description)
        startActivity(intent)
    }

}
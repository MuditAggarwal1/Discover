package com.example.discover.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.discover.activities.DetailedActivity
import com.example.discover.activities.NewsApplication
import com.example.discover.adapters.AllNewsAdapter
import com.example.discover.databinding.FragmentCategoryBinding
import com.example.discover.models.Article
import com.example.discover.utils.Constants
import com.example.discover.utils.OnNewsClickListener
import com.example.discover.viewModels.NewsViewModel
import com.example.discover.viewModels.NewsViewModelFactory

class CategoryFragment(private val q: String) : Fragment(), OnNewsClickListener {

    private lateinit var binding: FragmentCategoryBinding
    private lateinit var newsViewModel: NewsViewModel
    private val allNewsAdapter by lazy { AllNewsAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)

        val newsRepository = (activity?.applicationContext as NewsApplication).newsRepository
        newsViewModel = ViewModelProvider(this, NewsViewModelFactory(newsRepository, q, "publishedAt"))[NewsViewModel::class.java]
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = allNewsAdapter

        newsViewModel.allNewsData.observe(viewLifecycleOwner, Observer { allNewsAdapter.setData(it.articles) })

        newsViewModel.getIsLoading().observe(viewLifecycleOwner, Observer {loading ->

            if(loading){
                binding.recyclerView.visibility = View.GONE
                binding.shimmerViewContainer.startShimmer()
                binding.shimmerViewContainer.visibility = View.VISIBLE
            }else{
                binding.shimmerViewContainer.stopShimmer()
                binding.apply{
                    binding.shimmerViewContainer.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                }
            }

        })

        return binding.root
    }

    override fun onNewsItemClickListener(position: Int, article: Article) {
        val intent = Intent(activity?.applicationContext, DetailedActivity::class.java)
        intent.putExtra(Constants.AUTHOR, article.author)
        intent.putExtra(Constants.HEADING, article.title)
        intent.putExtra(Constants.DATE, article.publishedAt.substring(0, 10))
        intent.putExtra(Constants.IMAGE_URL, article.urlToImage)
        intent.putExtra(Constants.SOURCE, article.source.name)
        intent.putExtra(Constants.DESCRIPTION, article.description)
        startActivity(intent)
    }
}
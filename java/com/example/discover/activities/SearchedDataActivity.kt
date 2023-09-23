package com.example.discover.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.discover.R
import com.example.discover.adapters.AllNewsAdapter
import com.example.discover.databinding.ActivitySearchedDataBinding
import com.example.discover.models.Article
import com.example.discover.utils.Constants
import com.example.discover.utils.Constants.Companion.QUERY
import com.example.discover.utils.OnNewsClickListener
import com.example.discover.viewModels.NewsViewModel
import com.example.discover.viewModels.NewsViewModelFactory

class SearchedDataActivity : AppCompatActivity(), OnNewsClickListener {
    private lateinit var binding: ActivitySearchedDataBinding
    private lateinit var newsViewModel: NewsViewModel
    private val allNewsAdapter by lazy { AllNewsAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_searched_data)
        val newsRepository = (application as NewsApplication).newsRepository

        val q: String = intent.getStringExtra(QUERY).toString()
        newsViewModel = ViewModelProvider(this, NewsViewModelFactory(newsRepository, q, "publishedAt"))[NewsViewModel::class.java]

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = allNewsAdapter

        newsViewModel.allNewsData.observe(this, Observer {
            binding.shimmerViewContainer.startShimmer()
            binding.shimmerViewContainer.visibility = View.GONE
            allNewsAdapter.setData(it.articles)
        })
    }

    override fun onNewsItemClickListener(position: Int, article: Article) {
        val intent = Intent(this, DetailedActivity::class.java)
        intent.putExtra(Constants.AUTHOR, article.author)
        intent.putExtra(Constants.HEADING, article.title)
        intent.putExtra(Constants.DATE, article.publishedAt.substring(0, 10))
        intent.putExtra(Constants.IMAGE_URL, article.urlToImage)
        intent.putExtra(Constants.SOURCE, article.source.name)
        intent.putExtra(Constants.DESCRIPTION, article.description)
        startActivity(intent)
    }
}
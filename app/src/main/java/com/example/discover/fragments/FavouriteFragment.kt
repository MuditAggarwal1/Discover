package com.example.discover.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.discover.R
import com.example.discover.activities.DetailedActivity
import com.example.discover.adapters.AllNewsAdapter
import com.example.discover.databinding.FragmentFavouriteBinding
import com.example.discover.db.ArticleDatabase
import com.example.discover.models.Article
import com.example.discover.utils.Constants
import com.example.discover.utils.OnNewsClickListener

class FavouriteFragment : Fragment(), OnNewsClickListener {

    private lateinit var binding: FragmentFavouriteBinding
    private lateinit var database: ArticleDatabase
    private val allNewsAdapter by lazy { AllNewsAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        database = ArticleDatabase.getDatabase(requireContext())
        val articles = database.articleDao().getArticle()

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = allNewsAdapter

        articles.observe(viewLifecycleOwner, Observer {
            allNewsAdapter.setData(it)
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
        intent.putExtra(Constants.FLAG, "true")
        startActivity(intent)
    }
}
package com.example.discover.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.discover.R
import com.example.discover.models.Article
import com.example.discover.utils.NewsDiffUtil
import com.example.discover.utils.OnNewsClickListener

class AllNewsAdapter(private val onNewsClickListener: OnNewsClickListener) : RecyclerView.Adapter<AllNewsAdapter.MyViewHolder>() {
    private lateinit var view: View
    private var oldNewsList = emptyList<Article>()
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivBackground = view.findViewById<ImageView>(R.id.iv_background)!!
        val tvSource = view.findViewById<TextView>(R.id.tv_source)!!
        val tvHeading = view.findViewById<TextView>(R.id.tv_heading)!!
        val tvAuthor = view.findViewById<TextView>(R.id.tv_author)!!
        val tvDate = view.findViewById<TextView>(R.id.tv_date)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return oldNewsList.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvSource.text = oldNewsList[position].source.name
        holder.tvHeading.text = oldNewsList[position].title
        holder.tvAuthor.text = oldNewsList[position].author
        holder.tvDate.text = oldNewsList[position].publishedAt.substring(0, 10)
        Glide.with(view).load(oldNewsList[position].urlToImage).centerCrop().placeholder(R.drawable.no_image_avaliable).into(holder.ivBackground)

        holder.itemView.setOnClickListener {
            onNewsClickListener.onNewsItemClickListener(position, oldNewsList[position])
        }
    }

    fun setData(newNewsList: List<Article>) {
        val diffUtil = NewsDiffUtil(oldNewsList, newNewsList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        oldNewsList = newNewsList
        diffResults.dispatchUpdatesTo(this)
    }
}
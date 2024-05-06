package com.example.discover.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.discover.R
import com.example.discover.models.Article
import com.example.discover.utils.OnNewsClickListener

class ViewPagerAdapter(private val newsList: List<Article>, private val onNewsClickListener: OnNewsClickListener) : RecyclerView.Adapter<ViewPagerAdapter.MyViewHolder>() {
    private lateinit var view: View

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivBackground = view.findViewById<ImageView>(R.id.ivBackground)!!
        val tvSource = view.findViewById<TextView>(R.id.tvSource)!!
        val tvHeading = view.findViewById<TextView>(R.id.tvHeading)!!
        val tvAuthor = view.findViewById<TextView>(R.id.tvAuthor)!!
        val tvDate = view.findViewById<TextView>(R.id.tvDate)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_top_headlines, parent, false)
        view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvSource.text = newsList[position].source.name
        holder.tvHeading.text = newsList[position].title
        holder.tvAuthor.text = newsList[position].author
        holder.tvDate.text = newsList[position].publishedAt.substring(0, 10)
        Glide.with(view).load(newsList[position].urlToImage).centerCrop().placeholder(R.drawable.no_image_avaliable).into(holder.ivBackground)

        holder.itemView.setOnClickListener {
            onNewsClickListener.onNewsItemClickListener(position, newsList[position])
        }
    }
}
package com.example.samachar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.samachar.R
import com.example.samachar.models.Article
import kotlinx.android.synthetic.main.item_article_preview.view.*

//if we pass a list of article and everytime you want to add an article in list and call notify data set change i.e inefficient
//using notify data set change it would update all the item even if the item didi not changed
// so to solve this we use diffutlis :- calculates the differenec between two list and would enable us to update only the different item
// and this will happen in the background so it will not block the main thread
class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder> (){

    inner class ArticleViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)


    //callback for asynch list differ :- it will compare our two list and updtes only the changed item
    private val differCallback = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
           return oldItem.url == newItem.url     //not using id in place of article as api have no id associated it is only in our local database

        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    //async diff list will take two list compares and calculates the differece

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article_preview,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply{
            //loading image of article in imageview using glide
            Glide.with(this).load(article.urlToImage).into(ivArticleImage)
            tvSource.text = article.source?.name
            tvTitle.text = article.title
            tvDescription.text = article.description
            tvPublishedAt.text = article.publishedAt
            setOnClickListener{
                onItemClickListener?.let{ it(article)}
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Article)->Unit)? = null

    fun setonItemCLickListener(listener: (Article)->Unit){
        onItemClickListener = listener
    }
}
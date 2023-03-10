package com.example.easyfood.view.adapter

import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.easyfood.R
import com.example.easyfood.databinding.PopularItemsBinding
import com.example.easyfood.model.data.PopularMeal
import kotlinx.coroutines.withContext

private const val TAG = "MostPopularViewHolder"
class MostPopularViewHolder(private val binding: PopularItemsBinding) :RecyclerView.ViewHolder(binding.root) {

//    var onLongItemClick:((PopularMeal)->Unit) ?= null

    private val popularMealImage = binding.imgPopularMealItem

    fun bind(popularMeal: PopularMeal, popularMealClickListener: PopularMealClickListener){
        popularMeal.strMealThumb?.let { Log.d(TAG,it) }
//        popularMealImage.setImageURI(Uri.parse(popularMeal.strMealThumb))
        Glide.with(binding.imgPopularMealItem.context)
            .load(popularMeal.strMealThumb)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_background)
            )
            .into(popularMealImage)
        popularMealImage.setOnClickListener { popularMealClickListener.onPopularMealClick(
            popularMeal.idMeal!!,popularMeal.strMeal!!,popularMeal.strMealThumb!!,it) }

        popularMealImage.setOnLongClickListener {
            popularMealClickListener.onLongPopularMealClick(
                popularMeal.idMeal!!, popularMeal.strMeal!!, popularMeal.strMealThumb!!, it)
            true
        }

//        itemView.setOnLongClickListener {
//            onLongItemClick?.invoke(popularMeal)
//            true
//        }
    }
}
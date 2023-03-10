package com.example.easyfood.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easyfood.databinding.PopularItemsBinding
import com.example.easyfood.model.data.MealByCategory
import com.example.easyfood.model.data.MostPopularMeals
import com.example.easyfood.model.data.PopularMeal

class MostPopularAdapter(var popularMealClickListener: PopularMealClickListener)
    :RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//    var onLongItemClick:((PopularMeal)->Unit) ?= null

    var updatedPopularMeals = mutableListOf<PopularMeal>()

    fun setMostPopularMealsDetails(itemsPopularMeals:List<PopularMeal>){
        updatedPopularMeals = itemsPopularMeals.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MostPopularViewHolder(
            PopularItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is MostPopularViewHolder -> {
                holder.bind(
                    updatedPopularMeals[position],
                    popularMealClickListener
                )

//                holder.itemView.setOnLongClickListener {
//                    onLongItemClick?.invoke(updatedPopularMeals[position])
//                    true
//                }
            }
        }
    }

    override fun getItemCount(): Int {
       return updatedPopularMeals.size
    }


}
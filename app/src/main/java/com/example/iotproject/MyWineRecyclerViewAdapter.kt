package com.example.iotproject

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import com.example.iotproject.model.placeholder.PlaceholderContent.PlaceholderItem
import com.example.iotproject.databinding.FragmentWineBinding
import com.example.iotproject.databinding.ItemWineListBinding
import com.example.iotproject.model.Wine
import java.text.DateFormat
import java.util.ArrayList

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyWineRecyclerViewAdapter : RecyclerView.Adapter<MyWineRecyclerViewAdapter.ViewHolder>() {

    private var values = arrayListOf<Wine>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemWineListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.nameView.text = item.name
        holder.typeView.text = item.type
        if(item.year != null)
            holder.dateView.text = DateFormat.getDateInstance().format(item.year)
        holder.creatorView.text = item.creator
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: ItemWineListBinding) : RecyclerView.ViewHolder(binding.root) {
        val nameView: TextView = binding.itemWineNameTextview
        val typeView: TextView = binding.itemWineTypeTextview
        val dateView: TextView = binding.itemWineDateTextview
        val creatorView: TextView = binding.itemWineCreatorTextview
    }

    public fun setData(list: ArrayList<Wine>) {
        values = list
        notifyDataSetChanged()
    }

}
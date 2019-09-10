package ilgulee.com.foodrecipejetpackkotlin.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ilgulee.com.foodrecipejetpackkotlin.R
import ilgulee.com.foodrecipejetpackkotlin.TextItemViewHolder
import ilgulee.com.foodrecipejetpackkotlin.network.Recipe

class RecipeListAdapter : RecyclerView.Adapter<TextItemViewHolder>() {
    var data = listOf<Recipe>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.text_item_view, parent, false) as TextView
        return TextItemViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = "ID: ${item.rId}, ImageUrl: ${item.imageUrl}"
    }
}
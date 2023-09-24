package fr.damienc.smash_or_pass.adapters

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.damienc.smash_or_pass.R
import fr.damienc.smash_or_pass.models.Category
import fr.damienc.smash_or_pass.models.SmashListData
import fr.damienc.smash_or_pass.utils.SmashListManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryAdapter (private val categoryList: List<Category>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val categoryName: TextView = view.findViewById(R.id.item_category_vertical_rv_tv)
        val categoryRecylerView: RecyclerView = view.findViewById(R.id.item_category_vertical_rv_horizontal_rv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category_vertical_rv, parent, false)
        return ViewHolder(view)
    }

    //mettre a jour
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = categoryList[position]
        holder.categoryName.text = currentItem.name


        val smashListList = ArrayList<SmashListData>()



        holder.categoryRecylerView.adapter = SmashListAdapter(smashListList)
    }

    override fun getItemCount(): Int = categoryList.size
}
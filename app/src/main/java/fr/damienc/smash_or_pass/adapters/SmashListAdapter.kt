package fr.damienc.smash_or_pass.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.damienc.smash_or_pass.R
import fr.damienc.smash_or_pass.models.SmashListData

class SmashListAdapter (private val smashListList: List<SmashListData>): RecyclerView.Adapter<SmashListAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_smashlist_horizontal_rv, parent, false)
        return ViewHolder(view)
    }

    //mettre a jour
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = smashListList[position]
    }

    override fun getItemCount(): Int = smashListList.size
}
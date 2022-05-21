package ru.mamzin.taskforguru.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.mamzin.taskforguru.R
import ru.mamzin.taskforguru.model.HomeStore

class HomeStoreAdapter(private val cellClickListener: CellClickListener) :
    RecyclerView.Adapter<HomeStoreAdapter.ViewHolder>() {

    var homestorelist = mutableListOf<HomeStore>()

    fun setHomeStoreList(list: List<HomeStore>) {
        this.homestorelist = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = homestorelist[position]
        holder.bind(data)
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(data)
        }
    }

    override fun getItemCount(): Int {
        return homestorelist.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.homestore_list_item, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        //var firstname: TextView = itemLayoutView.findViewById(R.id.tvfirst_name)
        //var lastname: TextView = itemLayoutView.findViewById(R.id.tvlast_name)

        fun bind(data: HomeStore) {
            //firstname.text = data.first_name
            //lastname.text = data.last_name
        }

    }

    interface CellClickListener {
        fun onCellClickListener(data: HomeStore)
    }

}
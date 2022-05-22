package ru.mamzin.taskforguru.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.homestore_list_item.view.*
import ru.mamzin.taskforguru.R
import ru.mamzin.taskforguru.model.HomeStore
import ru.mamzin.taskforguru.utils.GlideApp

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
        holder.itemView.iv_buynow.setOnClickListener {
            cellClickListener.onBuyClickListener(data)
        }

        GlideApp.with(holder.itemView.context)
            .load(data.picture)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .override(270, 270)
            .fitCenter()
            .error(R.drawable.glide_err_foreground)
            .into(holder.iv_picture_hot_sales)
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
        var title: TextView = itemLayoutView.findViewById(R.id.tv_name_hot_sales)
        var subtitle: TextView = itemLayoutView.findViewById(R.id.tv_model_hot_sales)
        var iv_picture_hot_sales: ImageView = itemLayoutView.findViewById(R.id.iv_picture_hot_sales)
        var iv_new_hot_sales: ImageView = itemLayoutView.findViewById(R.id.iv_new_hot_sales)

        fun bind(data: HomeStore) {
            title.text = data.title
            subtitle.text = data.subtitle
            if (!data.is_new){
                iv_new_hot_sales.visibility = View.GONE
            }
        }

    }

    interface CellClickListener {
        fun onBuyClickListener(data: HomeStore)
    }

}
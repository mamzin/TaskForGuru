package ru.mamzin.taskforguru.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.bestseller_list_item.view.*
import ru.mamzin.taskforguru.R
import ru.mamzin.taskforguru.model.BestSeller
import ru.mamzin.taskforguru.utils.GlideApp


class BestSellerAdapter(private val cellClickListener: CellClickListener) :
    RecyclerView.Adapter<BestSellerAdapter.ViewHolder>() {

    var bestsellerlist = mutableListOf<BestSeller>()

    fun setBestSellerList(list: List<BestSeller>) {
        this.bestsellerlist = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = bestsellerlist[position]
        holder.bind(data)
        holder.itemView.iv_favorites.setOnClickListener {
            cellClickListener.onFavoritClickListener(data)
        }
        GlideApp.with(holder.itemView.context)
            .load(data.picture)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(R.drawable.glide_err_foreground)
            .into(holder.iv_bestseller)
    }

    override fun getItemCount(): Int {
        return bestsellerlist.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.bestseller_list_item, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        var iv_bestseller: ImageView = itemLayoutView.findViewById(R.id.iv_bestseller)
        var iv_favorites: ImageView = itemLayoutView.findViewById(R.id.iv_favorites)
        var tv_bestsellername: TextView = itemLayoutView.findViewById(R.id.tv_bestsellername)
        var tv_price: TextView = itemLayoutView.findViewById(R.id.tv_price)
        var tv_price_before: TextView = itemLayoutView.findViewById(R.id.tv_price_before)

        fun bind(data: BestSeller) {
            tv_bestsellername.text = data.title
            tv_price.text = data.discount_price.toString()
            tv_price_before.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            tv_price_before.text = data.price_without_discount.toString()

            if (!data.is_favorites) {
                iv_favorites.setImageResource(R.drawable.ic_heart)
            } else {
                iv_favorites.setImageResource(R.drawable.ic_fill_heart)
            }
        }
    }

    interface CellClickListener {
        fun onFavoritClickListener(data: BestSeller)
    }

}
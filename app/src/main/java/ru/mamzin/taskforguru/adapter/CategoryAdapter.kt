package ru.mamzin.taskforguru.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import ru.mamzin.taskforguru.R
import ru.mamzin.taskforguru.model.ModelCategory

class CategoryAdapter(private val categorylist: ArrayList<ModelCategory>,
                      private val context: Context,
                      private val cellClickListener: CellClickListener) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = categorylist[position]
        holder.tv_NameOfCategory.text = data.name
        holder.iv_category_icon.setImageDrawable(context.getDrawable(data.pic))

        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(data)
            holder.iv_circle_category.isActivated
            //holder.iv_circle_category.setImageDrawable(getDrawable(context, R.drawable.ic_ellipse_color))
        }
    }

    override fun getItemCount(): Int {
        return categorylist.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.category_list_item, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        var iv_circle_category: ImageView = itemLayoutView.findViewById(R.id.iv_circle_category)
        var tv_NameOfCategory: TextView = itemLayoutView.findViewById(R.id.tv_NameOfCategory)
        var iv_category_icon: ImageView = itemLayoutView.findViewById(R.id.iv_category_icon)

    }

    interface CellClickListener {
        fun onCellClickListener(category: ModelCategory)
    }

}
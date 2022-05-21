package ru.mamzin.taskforguru.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.mamzin.taskforguru.R
import ru.mamzin.taskforguru.adapter.CategoryAdapter
import ru.mamzin.taskforguru.model.ModelCategory
import ru.mamzin.taskforguru.net.RetrofitService
import ru.mamzin.taskforguru.repository.NetRepository
import ru.mamzin.taskforguru.viewmodel.NetViewModel
import ru.mamzin.taskforguru.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity(), CategoryAdapter.CellClickListener {

    lateinit var rv_category: RecyclerView
    lateinit var category_adapter: CategoryAdapter
    var category_list = ArrayList<ModelCategory>()
    lateinit var netViewModel: NetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainRepository = NetRepository(RetrofitService.getInstance())

        netViewModel =
            ViewModelProvider(this, ViewModelFactory(mainRepository))[NetViewModel::class.java]

        rv_category = findViewById(R.id.rv_select_category)


        category_list.add(ModelCategory(1, "Phones", R.drawable.ic_phone_icon))
        category_list.add(ModelCategory(2, "Computers", R.drawable.comp_icon))
        category_list.add(ModelCategory(3, "Health", R.drawable.health_icon))
        category_list.add(ModelCategory(4, "Books", R.drawable.book_icon))
        category_list.add(ModelCategory(5, "Other", R.drawable.other_icon))

        rv_category.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        category_adapter = CategoryAdapter(category_list, this@MainActivity, this@MainActivity)
        rv_category.adapter = category_adapter
    }

    override fun onCellClickListener(category: ModelCategory) {
        Toast.makeText(this@MainActivity, "Можно повесить ещё какой-то экшен", Toast.LENGTH_SHORT).show()
    }
}
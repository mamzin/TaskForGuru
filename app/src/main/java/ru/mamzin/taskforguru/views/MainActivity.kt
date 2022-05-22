package ru.mamzin.taskforguru.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.mamzin.taskforguru.R
import ru.mamzin.taskforguru.adapter.CategoryAdapter
import ru.mamzin.taskforguru.adapter.HomeStoreAdapter
import ru.mamzin.taskforguru.model.HomeStore
import ru.mamzin.taskforguru.model.ModelCategory
import ru.mamzin.taskforguru.net.RetrofitService
import ru.mamzin.taskforguru.repository.NetRepository
import ru.mamzin.taskforguru.viewmodel.NetViewModel
import ru.mamzin.taskforguru.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity(),
    CategoryAdapter.CellClickListener,
    HomeStoreAdapter.CellClickListener {

    lateinit var rv_category: RecyclerView
    lateinit var rv_home_store: RecyclerView
    lateinit var category_adapter: CategoryAdapter
    lateinit var homestore_adapter: HomeStoreAdapter
    var category_list = ArrayList<ModelCategory>()
    lateinit var netViewModel: NetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainRepository = NetRepository(RetrofitService.getInstance())
        netViewModel =
            ViewModelProvider(this, ViewModelFactory(mainRepository))[NetViewModel::class.java]


        setSelectCategoryList()
        setHomeStoreList()

    }

    private fun setHomeStoreList() {
        rv_home_store = findViewById(R.id.rv_home_store)
        rv_home_store.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        homestore_adapter = HomeStoreAdapter(this@MainActivity)
        rv_home_store.adapter = homestore_adapter

        netViewModel.dataList.observe(this, Observer {
            if (it != null) {
                homestore_adapter.setHomeStoreList(it)
            }
        })

        netViewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        netViewModel.getAllDevices()
    }

    private fun setSelectCategoryList(){
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
        Toast.makeText(this@MainActivity, "Можно повесить ещё какой-то экшен, помимо смены цвета иконок", Toast.LENGTH_SHORT).show()
    }

    override fun onBuyClickListener(data: HomeStore) {
        Toast.makeText(this@MainActivity, "Можно положить в корзину", Toast.LENGTH_SHORT).show()
    }
}
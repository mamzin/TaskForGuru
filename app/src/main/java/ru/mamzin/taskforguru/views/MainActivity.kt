package ru.mamzin.taskforguru.views

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.mamzin.taskforguru.R
import ru.mamzin.taskforguru.adapter.BestSellerAdapter
import ru.mamzin.taskforguru.adapter.CategoryAdapter
import ru.mamzin.taskforguru.adapter.HomeStoreAdapter
import ru.mamzin.taskforguru.model.BestSeller
import ru.mamzin.taskforguru.model.HomeStore
import ru.mamzin.taskforguru.model.ModelCategory
import ru.mamzin.taskforguru.net.RetrofitService
import ru.mamzin.taskforguru.repository.NetRepository
import ru.mamzin.taskforguru.viewmodel.BestSellerViewModel
import ru.mamzin.taskforguru.viewmodel.HotSalesViewModel
import ru.mamzin.taskforguru.viewmodel.ViewModelBestsellerFactory
import ru.mamzin.taskforguru.viewmodel.ViewModelHomeStoreFactory

class MainActivity : AppCompatActivity(),
    CategoryAdapter.CellClickListener,
    HomeStoreAdapter.CellClickListener,
    BestSellerAdapter.CellClickListener {

    lateinit var rv_category: RecyclerView
    lateinit var rv_home_store: RecyclerView
    lateinit var rv_best_seller: RecyclerView

    lateinit var category_adapter: CategoryAdapter
    lateinit var homestore_adapter: HomeStoreAdapter
    lateinit var bestseller_adapter: BestSellerAdapter

    var category_list = ArrayList<ModelCategory>()
    private lateinit var hotSalesViewModel: HotSalesViewModel
    private lateinit var bestSellerViewModel: BestSellerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainRepository = NetRepository(RetrofitService.getInstance())
        hotSalesViewModel =
            ViewModelProvider(
                this,
                ViewModelHomeStoreFactory(mainRepository)
            )[HotSalesViewModel::class.java]
        bestSellerViewModel =
            ViewModelProvider(
                this,
                ViewModelBestsellerFactory(mainRepository)
            )[BestSellerViewModel::class.java]

        setSelectCategoryList()
        setHomeStoreList()
        setBestSellerList()

    }

    private fun setBestSellerList() {
        rv_best_seller = findViewById(R.id.rv_best_seller)
        rv_best_seller.layoutManager = GridLayoutManager(this, 2)
        bestseller_adapter = BestSellerAdapter(this@MainActivity)
        rv_best_seller.adapter = bestseller_adapter

        bestSellerViewModel.listbestseller.observe(this, Observer {
            if (it != null) {
                bestseller_adapter.setBestSellerList(it)
            }
        })

        bestSellerViewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        bestSellerViewModel.getAllBestSeller()
    }

    private fun setHomeStoreList() {
        rv_home_store = findViewById(R.id.rv_home_store)
        rv_home_store.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        homestore_adapter = HomeStoreAdapter(this@MainActivity)
        rv_home_store.adapter = homestore_adapter

        hotSalesViewModel.listhomestore.observe(this, Observer {
            if (it != null) {
                homestore_adapter.setHomeStoreList(it)
            }
        })

        hotSalesViewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        hotSalesViewModel.getAllHomeStore()
    }

    private fun setSelectCategoryList() {
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
        Toast.makeText(
            this@MainActivity,
            "Можно повесить ещё какой-то экшен, помимо смены цвета иконок",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onBuyClickListener(data: HomeStore) {
        Toast.makeText(this@MainActivity, "Можно положить в корзину", Toast.LENGTH_SHORT).show()
    }

    override fun onFavoritClickListener(data: BestSeller) {
        Toast.makeText(
            this@MainActivity,
            "Можно повесить ещё какой-то экшен, помимо добавления в фавориты",
            Toast.LENGTH_SHORT
        ).show()
    }
}
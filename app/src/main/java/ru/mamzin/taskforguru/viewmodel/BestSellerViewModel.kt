package ru.mamzin.taskforguru.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.mamzin.taskforguru.model.BestSeller
import ru.mamzin.taskforguru.model.ModelResponseStore
import ru.mamzin.taskforguru.repository.NetRepository


class BestSellerViewModel constructor(private val repository: NetRepository) : ViewModel() {

    val listbestseller = MutableLiveData<List<BestSeller>?>()
    val errorMessage = MutableLiveData<String>()

    fun getAllBestSeller() {
        val response = repository.getBestSeller()
        response.enqueue(object : Callback<ModelResponseStore<List<BestSeller>>> {
            override fun onResponse(
                call: Call<ModelResponseStore<List<BestSeller>>>,
                response: Response<ModelResponseStore<List<BestSeller>>>
            ) {
                listbestseller.postValue(response.body()?.best_seller)
            }

            override fun onFailure(call: Call<ModelResponseStore<List<BestSeller>>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}
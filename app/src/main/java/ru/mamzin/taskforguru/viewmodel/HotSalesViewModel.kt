package ru.mamzin.taskforguru.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.mamzin.taskforguru.model.HomeStore
import ru.mamzin.taskforguru.model.ModelResponseStore
import ru.mamzin.taskforguru.repository.NetRepository

class HotSalesViewModel constructor(private val repository: NetRepository) : ViewModel() {

    val listhomestore = MutableLiveData<List<HomeStore>?>()
    val errorMessage = MutableLiveData<String>()

    fun getAllHomeStore() {
        val response = repository.getHomeStore()
        response.enqueue(object : Callback<ModelResponseStore<List<HomeStore>>> {
            override fun onResponse(
                call: Call<ModelResponseStore<List<HomeStore>>>,
                response: Response<ModelResponseStore<List<HomeStore>>>
            ) {
                listhomestore.postValue(response.body()?.home_store)
            }

            override fun onFailure(call: Call<ModelResponseStore<List<HomeStore>>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}
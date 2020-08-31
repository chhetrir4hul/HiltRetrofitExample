package com.rahul.hiltretrofitexample

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rahul.hiltretrofitexample.network.ApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel @ViewModelInject
constructor(
    private val apiService: ApiService,
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val mResponse = MutableLiveData<String>("Loading...")

    fun getUsers() {
        val disposable = apiService.getUsers().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { userList -> mResponse.postValue(userList.toString()) },
                { error -> mResponse.postValue(error.toString()) })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
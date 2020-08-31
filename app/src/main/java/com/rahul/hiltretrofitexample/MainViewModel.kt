package com.rahul.hiltretrofitexample

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.rahul.hiltretrofitexample.network.ApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel @ViewModelInject
constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun getUsers() {
        val disposable = apiService.getUsers().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { users -> Log.d("MainViewModel", "getUsers: " + Gson().toJson(users)) },
                { error -> Log.d("MainViewModel", "getUsers: " + Gson().toJson(error)) })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
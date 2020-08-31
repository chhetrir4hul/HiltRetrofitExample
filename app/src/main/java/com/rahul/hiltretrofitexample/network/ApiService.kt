package com.rahul.hiltretrofitexample.network

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    fun getUsers(): Observable<String>
}
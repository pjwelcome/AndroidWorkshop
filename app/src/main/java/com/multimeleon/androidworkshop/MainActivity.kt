package com.multimeleon.androidworkshop

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        val animals = listOf("Lion", "rabbit")
        dashboard_recyclerview.adapter = AnimalAdapter(animals)
        bindDataFromService()

        bindServiceCallToForRepo()
    }

    private fun bindDataFromService() {
        Network("https://api.github.com/", true)
                .getRetrofitClient()
                .create(Endpoint::class.java)
                .searchUser("hello", "followers", "desc").enqueue(object : Callback<SearchRepoResponse> {

                    override fun onResponse(call: Call<SearchRepoResponse>?, response: Response<SearchRepoResponse>?) {
                        if (response!!.isSuccessful) {
                            dashboard_recyclerview.adapter = AnimalAdapter(response?.body()?.items!!.map { it.login })
                        }
                    }

                    override fun onFailure(call: Call<SearchRepoResponse>?, t: Throwable?) {
                        print(t!!.message)
                    }

                })
    }

    private fun bindServiceCallToForRepo() {
        Network("https://api.github.com/", true)
                .getRetrofitClient()
                .create(Endpoint::class.java)
                .searchRepo("Android", "stars", "desc").enqueue(object : Callback<SearchResponse> {

                    override fun onResponse(call: Call<SearchResponse>?, response: Response<SearchResponse>?) {
                        if (response!!.isSuccessful) {
                            print(response.body()!!.searchResults!!.map { it.name })
                        }
                    }

                    override fun onFailure(call: Call<SearchResponse>?, t: Throwable?) {
                        print(t!!.message)
                    }

                })
    }

    private fun showImage(url: String) {
//        Glide.with(this)
//                .load(url)
//                .into(image)
    }

    private fun setupRecyclerView() {
        dashboard_recyclerview.layoutManager = GridLayoutManager(this, 2)

    }
}

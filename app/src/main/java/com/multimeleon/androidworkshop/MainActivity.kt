package com.multimeleon.androidworkshop

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dashboard_recyclerView.adapter = DashboardAdapter(plants = it, clickListener = {
            plantItemClicked(it)
        })
    }

    private fun setupRecyclerView() {
        dashboard_recyclerView.layoutManager = GridLayoutManager(this, 2)
        this.bindData()
    }

    private fun plantItemClicked(plant: Plant) {
        Intent(this, PlantDetailActivity::class.java).addData(mapOf(PlantId to plant.id)) {
            navigateWith(this@DashboardActivity)
        }
    }
}

package com.example.orpha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orpha.adapter.DonorsAdapter

class DonorsListRv : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donors_list_rv)


//        binding.listOfDonorsRv.layoutManager = LinearLayoutManager(this)
//        val donorsAdapter: DonorsAdapter = DonorsAdapter()
//        binding.listOfDonorsRv.adapter=DonorsAdapter
    }
}
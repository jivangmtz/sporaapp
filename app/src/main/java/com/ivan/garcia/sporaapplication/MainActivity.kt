package com.ivan.garcia.sporaapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.ivan.garcia.sporaapplication.databinding.ActivityMainBinding
import com.ivan.garcia.sporaapplication.viewmodel.RewardViewModel

class MainActivity : AppCompatActivity() {

    private val rewardViewModel: RewardViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityMainBinding.inflate(layoutInflater).apply {
            binding = this
            setContentView(root)
            btnGetReward.setOnClickListener { rewardViewModel.getReward() }
        }

        rewardViewModel.rewardModel.observe(this, Observer { reward ->
            binding.tvRewardCode.text = reward.rewardCode
            binding.tvRewardDesc.text = reward.rewardDescription
        })
    }
}
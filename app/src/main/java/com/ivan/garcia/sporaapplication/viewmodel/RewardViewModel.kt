package com.ivan.garcia.sporaapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ivan.garcia.sporaapplication.model.Reward
import com.ivan.garcia.sporaapplication.model.RewardProvider

class RewardViewModel : ViewModel() {
    val rewardModel = MutableLiveData<Reward>()

    fun getReward() {
        val reward = RewardProvider.randomReward()
        rewardModel.postValue(reward)
    }
}
package com.example.myquizapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//stores UI-related data that isn't destroyed on app rotations.
class ScoreViewModel : ViewModel() {

    private  var score = 0
    private var scoreLiveData= MutableLiveData<Int>()//▪ LiveData is an observable data holder class.
    fun initialScore(): MutableLiveData<Int> {
        scoreLiveData.value = score
        return  scoreLiveData
    }
    fun currentScore(){
        score+=1
        scoreLiveData.value= score
    }
    fun finalScore(): MutableLiveData<Int> {
        return scoreLiveData
    }
}
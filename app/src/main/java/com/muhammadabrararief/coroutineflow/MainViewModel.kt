package com.muhammadabrararief.coroutineflow

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    //1
    val countDownFlow = flow<Int> {
        val startingVal = 10
        var currentVal = startingVal
        emit(startingVal)
        while (currentVal > 0) {
            delay(1000L)
            currentVal--
            emit(currentVal)
        }
    }

    // 2
    init {
        collectFlow()
    }


//    private fun collectFlow() {
//        viewModelScope.launch {
//            countDownFlow.collect /*Latest*/ {
//                delay(1500L)
//                Log.e(TAG, "collectFlow: $it")
//            }
//        }
//    }


    // 3
//    private fun collectFlow() {
//        viewModelScope.launch {
//            // 11
//            countDownFlow
//                .filter { sec ->
//                    sec % 2 == 0
//                }
//                .map {
//                    it * 2
//                }
//                .onEach {
//                    Log.e(TAG, "collectFlow: $it")
//                }
//                .count /*reduce, fold*/ {
//                    it % 2 == 0
//                }
//        }
//    }

    // 4
//    private fun collectFlow() {
//        val flow1 = flow {
//            emit(1)
//            delay(1000L)
//            emit(2)
//        }
//
//        viewModelScope.launch {
//            flow1.flatMapConcat { value ->
//                flow {
//                    emit(value + 10)
//                    delay(2000L)
//                    emit(value + 20)
//                }
//            }.collect {
//                Log.e(TAG, "collectFlow: $it")
//            }
//        }
//    }

    //5
    private fun collectFlow() {
        val flow1 = flow {
            emit("Appetizer")
            delay(500L)
            emit("Main Dish")
            delay(1000L)
            emit("Dessert")
            delay(100L)
        }
        viewModelScope.launch {
            flow1.onEach {
                Log.e(TAG, "$it delivered")
            }
                .buffer() /*conflate*/
                .collect {
                    Log.e(TAG, "Eating $it")
                    delay(2000L)
                    Log.e(TAG, "Finished eating $it")

                }
        }
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}
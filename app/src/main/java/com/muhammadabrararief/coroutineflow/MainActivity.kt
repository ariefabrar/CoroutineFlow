package com.muhammadabrararief.coroutineflow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.muhammadabrararief.coroutineflow.databinding.ActivityMainBinding
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //1
        /*Log.e(TAG, "Current Run in thread ${Thread.currentThread().name}")
        GlobalScope.launch {
            delay(3000L)
            Log.e(TAG, "Coroutine run in thread ${Thread.currentThread().name}")
        }*/

        //2
        /*Log.e(TAG, "Current Run in thread ${Thread.currentThread().name}")
        GlobalScope.launch {
            val call1 = networkCall1()
            val call2 = networkCall2()
            Log.e(TAG, call1)
            Log.e(TAG, call2)
        }*/

        //3
        /*GlobalScope.launch(Dispatchers.IO) {
            val fromAPI = networkCall1()
            withContext(Dispatchers.Main) {
                binding.textView2.text = fromAPI
            }

        }*/

        //4

        /*val coroutinesJob = GlobalScope.launch(context = Dispatchers.IO) {
            repeat(5) {
                Log.e(TAG, "Coroutine running...")
                delay(1000L)
            }
        }*/

        //4
        /*runBlocking {
            coroutinesJob.join()
            Log.e(TAG, "Main Continue...")
        }*/

        //5
        /*runBlocking {
            delay(2000L)
            coroutinesJob.cancel()
            Log.e(TAG, "Job Cancelled...")
            Log.e(TAG, "Main Continue...")
        }*/


        // 6

        /*val coroutinesJob = GlobalScope.launch(context = Dispatchers.IO) {
            Log.e(TAG, "Start Loooooooong Running")
            for (i in 30..40) {
                // 7
                *//*if (isActive) {
                    Log.e(TAG, "fib $i = ${fib(i)}")
                }*//*

                Log.e(TAG, "fib $i = ${fib(i)}")
            }
            Log.e(TAG, "End Looooong Running")
        }

        runBlocking {
            delay(2000L)
            coroutinesJob.cancel()
            Log.e(TAG, "Job Cancelled...")
            Log.e(TAG, "Main Continue...")
        }*/

        // 8
        /*val coroutinesJob = GlobalScope.launch(context = Dispatchers.IO) {
            Log.e(TAG, "Start Loooooooong Running")
            withTimeout(3000L) {
                for (i in 35..45) {
                    if (isActive) Log.e(TAG, "fib $i = ${fib(i)}")
                }
            }
            Log.e(TAG, "End Looooong Running")
        }*/


        // 9
        /*val coroutinesJob = GlobalScope.launch(context = Dispatchers.IO) {

            *//*val totalTime = measureTimeMillis {
                val api1 = networkCall1()
                val api2 = networkCall2()

                Log.e(TAG, "networkCall1: $api1")
                Log.e(TAG, "networkCall2: $api2")
            }
            Log.e(TAG, "totalTime: $totalTime")*//*


            *//*val totalTime = measureTimeMillis {
                var api1 = ""
                var api2 = ""

                val job1 = launch { api1 = networkCall1() }
                val job2 = launch { api2 = networkCall2() }

                job1.join()
                job2.join()

                Log.e(TAG, "networkCall1: $api1")
                Log.e(TAG, "networkCall2: $api2")
            }
            Log.e(TAG, "totalTime: $totalTime")*//*


            *//*val totalTime = measureTimeMillis {
                val api1 = async { networkCall1() }
                val api2 = async { networkCall2() }

                Log.e(TAG, "networkCall1: ${api1.await()}")
                Log.e(TAG, "networkCall2: ${api2.await()}")
            }
            Log.e(TAG, "totalTime: $totalTime")*//*
        }*/


        // 10 11 12
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        /*// 10
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.countDownFlow
                .collect {
                    binding.textView2.text = it.toString()
                }
        }*/

        /*binding.button.setOnClickListener {
            GlobalScope.launch {
                while (true) {
                    delay(1000L)
                    Log.e(TAG, "Main Activity Running...")
                }
            }

            GlobalScope.launch {
                delay(5000L)

                Intent(this@MainActivity, SecondActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }

        }*/
    }

    private fun fib(n: Int): Long {
        return when (n) {
            0 -> 0
            1 -> 1
            else -> fib(n - 1) + fib(n - 2)
        }
    }

    private suspend fun networkCall1(): String {
        delay(3000L)
        return "done networkCall1"
    }

    private suspend fun networkCall2(): String {
        delay(2000L)
        return "done networkCall2"
    }
}
package com.aungbophyoe.space.mvisample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.aungbophyoe.space.mvisample.R
import com.aungbophyoe.space.mvisample.model.Photo
import com.aungbophyoe.space.mvisample.recyclerAdapter.PhotoRecyclerAdapter
import com.aungbophyoe.space.mvisample.util.DataState
import com.aungbophyoe.space.mvisample.util.showOrGone
import com.aungbophyoe.space.mvisample.viewmodel.MainStateEvent
import com.aungbophyoe.space.mvisample.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by viewModels()
    private val photoRecyclerAdapter by lazy {
        PhotoRecyclerAdapter(this)
    }

    private var dataList : ArrayList<Photo> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvPhotos.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        photoRecyclerAdapter.submitList(dataList)
        rvPhotos.adapter = photoRecyclerAdapter
        subscribeObservers()
        mainViewModel.setStateEvent(MainStateEvent.GetPhotosEvent)
    }

    private fun subscribeObservers(){
        mainViewModel.dataState.observe(this, Observer { dataState->
            when(dataState){
                is DataState.Loading ->{
                    showProgressBar()
                }
                is DataState.Success<List<Photo>> ->{
                    hideProgressBar()
                    dataList = dataState.data as ArrayList<Photo>
                    photoRecyclerAdapter.submitList(dataList)
                }

                is DataState.Error -> {
                    hideProgressBar()
                    showErrorMsg(dataState.exception.message.toString())
                }
            }
        })
    }

    private fun showErrorMsg(msg:String){
        if(msg!=null){
            tvError.text = msg
        }else{
            tvError.text = "Unknown Error!"
        }
    }

    private fun showProgressBar(){
        progressBar.showOrGone(true)
    }

    private fun hideProgressBar(){
        progressBar.showOrGone(false)
    }
}
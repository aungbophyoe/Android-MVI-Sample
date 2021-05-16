package com.aungbophyoe.space.mvisample.viewmodel

import androidx.lifecycle.*
import com.aungbophyoe.space.mvisample.model.Photo
import com.aungbophyoe.space.mvisample.repository.MainRepository
import com.aungbophyoe.space.mvisample.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
        private val savedStateHandle: SavedStateHandle,
        private val repository: MainRepository
):ViewModel() {
    private val _dataState : MutableLiveData<DataState<List<Photo>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Photo>>> get() = _dataState

    fun setStateEvent(mainStateEvent : MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetPhotosEvent -> {
                    repository.getPhotos()
                            .onEach { dataState ->
                                _dataState.value = dataState
                            }
                            .launchIn(viewModelScope)
                }

                is MainStateEvent.None ->{
                    // somethings
                }
            }
        }
    }

}


sealed class MainStateEvent{

    object GetPhotosEvent: MainStateEvent()

    object None: MainStateEvent()
}
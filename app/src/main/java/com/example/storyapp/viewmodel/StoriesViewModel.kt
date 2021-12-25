package com.example.storyapp.viewmodel


import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storyapp.data.database.Stories
import kotlinx.coroutines.launch

class StoriesViewModel(private val repository: StoriesRepository) : ViewModel(),Observable {

    val allmystories=repository.allStories

    @Bindable
    val tvtitle = MutableLiveData<String>()

    @Bindable
    val tvcontent = MutableLiveData<String>()

    @Bindable
    val saveOrUpdatButtonText = MutableLiveData<String>()

    init{
        saveOrUpdatButtonText.value="Save"
    }

    fun save(){

            val title = tvtitle.value!!
            val content = tvcontent.value!!
            insertstory(Stories(0,title,content))
            tvtitle.value=null
            tvcontent.value=null

    }

    fun insertstory(newstory : Stories){
        viewModelScope.launch {
            repository.insert(newstory)
        }
    }

    fun deletestory(newstory : Stories){
        viewModelScope.launch {
            repository.delete(newstory)
        }
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}
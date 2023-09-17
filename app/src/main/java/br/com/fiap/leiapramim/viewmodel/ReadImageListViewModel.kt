package br.com.fiap.leiapramim.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.leiapramim.model.ReadImage

class ReadImageListViewModel : ViewModel() {

    private val _readImageList = MutableLiveData<List<ReadImage>>()
    val readImageList: LiveData<List<ReadImage>> = _readImageList

    fun addToList(readImage: ReadImage) {
        val currentList = _readImageList.value ?: emptyList()
        val newList = currentList.toMutableList()
        newList.add(readImage)
        _readImageList.value = newList
    }

}

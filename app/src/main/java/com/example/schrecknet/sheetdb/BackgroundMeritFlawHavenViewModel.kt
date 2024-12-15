package com.example.schrecknet.sheetdb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BackgroundMeritFlawHavenViewModel(private val dao: BackgroundMeritFlawHavenDAO) : ViewModel() {

    private val _backgroundMeritFlawHavens = MutableLiveData<List<BackgroundMeritFlawHaven>>()
    val backgroundMeritFlawHavens: LiveData<List<BackgroundMeritFlawHaven>> get() = _backgroundMeritFlawHavens

    fun getBackgroundMeritFlawHavensForCharacter(characterName: String) {
        viewModelScope.launch {
            dao.getBackgroundMeritFlawHavensForCharacter(characterName).collect { list ->
                _backgroundMeritFlawHavens.postValue(list)
            }
        }
    }

    fun insertBackgroundMeritFlawHaven(backgroundMeritFlawHaven: BackgroundMeritFlawHaven) {
        viewModelScope.launch {
            dao.insertBackgroundMeritFlawHaven(backgroundMeritFlawHaven)
        }
    }

    fun deleteBackgroundMeritFlawHaven(backgroundMeritFlawHaven: BackgroundMeritFlawHaven) {
        viewModelScope.launch {
            dao.deleteBackgroundMeritFlawHaven(backgroundMeritFlawHaven)
        }
    }
}

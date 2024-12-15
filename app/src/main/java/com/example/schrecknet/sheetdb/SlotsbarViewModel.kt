package com.example.schrecknet.sheetdb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SlotsBarViewModel(private val dao: SlotsBarDAO) : ViewModel() {

    private val _slotsBars = MutableLiveData<List<SlotsBar>>()
    val slotsBars: LiveData<List<SlotsBar>> get() = _slotsBars

    fun getSlotsBarsForCharacter(characterName: String) {
        viewModelScope.launch {
            dao.getSlotsBarsForCharacter(characterName).collect { list ->
                _slotsBars.postValue(list)
            }
        }
    }

    fun insertSlotsBar(slotsBar: SlotsBar) {
        viewModelScope.launch {
            dao.insertSlotsBar(slotsBar)
        }
    }

    fun deleteSlotsBar(slotsBar: SlotsBar) {
        viewModelScope.launch {
            dao.deleteSlotsBar(slotsBar)
        }
    }
}
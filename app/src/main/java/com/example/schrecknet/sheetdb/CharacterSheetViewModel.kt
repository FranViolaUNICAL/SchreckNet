package com.example.schrecknet.sheetdb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CharacterSheetViewModel(private val dao: CharacterSheetDAO): ViewModel() {

    private val _characterSheet = MutableLiveData<CharacterSheet?>()
    val characterSheet: LiveData<CharacterSheet?> get() = _characterSheet

    fun getCharacterSheetByName(name: String) {
        viewModelScope.launch {
            val sheet = dao.getCharacterSheetByName(name)
            _characterSheet.postValue(sheet)
        }
    }

    fun insertCharacterSheet(characterSheet: CharacterSheet){
        viewModelScope.launch {
            dao.insertCharacterSheet(characterSheet)
        }
    }

    fun deleteCharacterSheet(name: String){
        viewModelScope.launch {
            dao.deleteCharacterSheet(dao.getCharacterSheetByName(name))
        }
    }
}
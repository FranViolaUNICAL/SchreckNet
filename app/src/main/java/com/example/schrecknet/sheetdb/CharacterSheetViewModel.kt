package com.example.schrecknet.sheetdb

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CharacterSheetViewModel(application: Application) : AndroidViewModel(application) {
    private val characterSheetDAO = AppDatabase.getDatabase(application).characterSheetDao()

    fun getCharacterSheetByName(name: String) {
        viewModelScope.launch {
            val sheet = dao.getCharacterSheetByName(name)
            _characterSheet.postValue(sheet)
        }
    }

    fun insertCharacterSheet(characterSheet: CharacterSheet) {
        viewModelScope.launch {
            dao.insertCharacterSheet(characterSheet)
        }
    }

    fun deleteCharacterSheet(name: String) {
        viewModelScope.launch {
            val sheet = dao.getCharacterSheetByName(name)
            if (sheet != null) {
                dao.deleteCharacterSheet(sheet)
            }
        }
    }
}
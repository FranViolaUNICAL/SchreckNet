package com.example.schrecknet.sheetdb

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterAdvantagesViewModel(application: Application) : AndroidViewModel(application) {
    private val dao: CharacterAdvantageDAO
    private val _characterAdvantages = MutableLiveData<List<CharacterAdvantage>>()
    val characterAdvantages: LiveData<List<CharacterAdvantage>> get() = _characterAdvantages

    init {
        // Initialize the DAO using the Room database
        val database = AppDatabase.getDatabase(application)
        dao = database.characterAdvantageDao()
    }

    fun getAdvantageForCharacter(characterName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.getCharacterAdvantageForCharacter(characterName).collect { list ->
                _characterAdvantages.postValue(list)
            }
        }
    }

    fun insertAdvantage(characterAdvantage: CharacterAdvantage) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insertCharacterAdvantage(characterAdvantage)
        }
    }

    fun deleteAdvantage(characterAdvantage: CharacterAdvantage) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteCharacterAdvantage(characterAdvantage)
        }
    }
}
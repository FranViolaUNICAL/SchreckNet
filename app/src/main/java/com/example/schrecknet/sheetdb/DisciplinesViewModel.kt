package com.example.schrecknet.sheetdb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DisciplinesViewModel(private val dao: DisciplinesDAO) : ViewModel() {

    private val _disciplines = MutableLiveData<List<Discipline>>()
    val disciplines: LiveData<List<Discipline>> get() = _disciplines

    fun getDisciplinesForCharacter(characterName: String) {
        viewModelScope.launch {
            dao.getDisciplinesForCharacter(characterName).collect { list ->
                _disciplines.postValue(list)
            }
        }
    }

    fun insertDiscipline(discipline: Discipline) {
        viewModelScope.launch {
            dao.insertDiscipline(discipline)
        }
    }

    fun deleteDiscipline(discipline: Discipline) {
        viewModelScope.launch {
            dao.deleteDiscipline(discipline)
        }
    }
}
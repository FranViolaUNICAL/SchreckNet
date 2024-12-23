package com.example.schrecknet.sheetdb

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DisciplinesViewModel(application: Application) : AndroidViewModel(application) {

    private val dao: DisciplinesDAO
    private val _disciplines = MutableLiveData<List<Discipline>>()
    val disciplines: LiveData<List<Discipline>> get() = _disciplines

    init {
        val database = AppDatabase.getDatabase(application)
        dao = database.disciplineDao()
    }

    fun getDisciplinesForCharacter(characterName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.getDisciplinesForCharacter(characterName).collect { list ->
                _disciplines.postValue(list)
            }
        }
    }

    fun insertDiscipline(discipline: Discipline) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insertDiscipline(discipline)
        }
    }

    fun deleteDiscipline(discipline: Discipline) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteDiscipline(discipline)
        }
    }
}
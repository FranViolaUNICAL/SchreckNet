package com.example.schrecknet.sheetdb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WeaponsViewModel(private val dao: WeaponsDAO) : ViewModel() {

    private val _weapons = MutableLiveData<List<Weapon>>()
    val weapons: LiveData<List<Weapon>> get() = _weapons

    fun getWeaponsForCharacter(characterName: String) {
        viewModelScope.launch {
            dao.getWeaponsForCharacter(characterName).collect { list ->
                _weapons.postValue(list)
            }
        }
    }

    fun insertWeapon(weapon: Weapon) {
        viewModelScope.launch {
            dao.insertWeapon(weapon)
        }
    }

    fun deleteWeapon(weapon: Weapon) {
        viewModelScope.launch {
            dao.deleteWeapon(weapon)
        }
    }
}
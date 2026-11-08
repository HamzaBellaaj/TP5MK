package com.isetr.tp5app.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.isetr.tp5app.Model.Etudiant

class EtudiantViewModel : ViewModel() {
    private val _etudiants = MutableLiveData<MutableList<Etudiant>>()
    private val _setudiant = MutableLiveData<Etudiant>()
    val etudiants: LiveData<MutableList<Etudiant>> = _etudiants
    val setudiants: LiveData<Etudiant> = _setudiant
    init {

        _etudiants.value = mutableListOf(
            Etudiant(id = 101, classe = "M1 Info", email = "ali@isetur.tn", genre = "M"),
            Etudiant(id = 102, classe = "M2 Info", email = "Nadia@isetur.tn", genre = "F"),
            Etudiant(id = 103, classe = "L1 Info", email = "Amel@isetur.tn", genre = "F")
        ) }
    fun addEtudiant(etudiant: Etudiant) {
        val currentList = _etudiants.value ?: mutableListOf()
        currentList.add(etudiant)
        _etudiants.value = currentList
    }
    fun selectEtudiant(etudiant: Etudiant) {
        _setudiant.value = etudiant
    }
}
package com.isetr.tp5app.Model

import java.io.Serializable

data class Etudiant(
    var id: Int,
    var email: String,
    var classe: String,
    var genre: String // "M" pour Garçon, "F" pour Fille
) : Serializable

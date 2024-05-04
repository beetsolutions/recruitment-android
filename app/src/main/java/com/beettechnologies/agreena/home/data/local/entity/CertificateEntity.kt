package com.beettechnologies.agreena.home.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "certificates")
data class CertificateEntity (
    @PrimaryKey val id: String,
    val originator: String,
    val owner: String,
    val status: String,
    val isFavorite: Boolean = false
)

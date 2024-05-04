package com.beettechnologies.agreena.home.data.local.dao

import android.database.sqlite.SQLiteConstraintException
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.beettechnologies.agreena.home.data.local.entity.CertificateEntity

@Dao
interface CertificateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<CertificateEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: CertificateEntity)

    @Query("SELECT * FROM certificates")
    suspend fun getAll(): List<CertificateEntity>

    @Query("SELECT * FROM certificates WHERE id = :id")
    fun getOne(id: String): CertificateEntity

    @Query("UPDATE certificates SET isFavorite = :value WHERE id = :id")
    fun update(id: String, value: Boolean): Int

    @Update
    fun update(entity: CertificateEntity)

    fun upsertCertificates(entity: List<CertificateEntity>) {
        entity.forEach {
            try {
                insert(it)
            }
            catch (e: SQLiteConstraintException) {
                val certificate = getOne(it.id)
                update(it.copy(isFavorite = certificate.isFavorite))
            }
        }
    }
}

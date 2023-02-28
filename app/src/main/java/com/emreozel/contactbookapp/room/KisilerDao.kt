package com.emreozel.contactbookapp.room

import androidx.room.*
import com.emreozel.contactbookapp.data.entity.Kisiler

@Dao
interface KisilerDao {
        @Query("SELECT * FROM kisiler")
        suspend fun tumKisiler():List<Kisiler>

        @Query("SELECT * FROM kisiler WHERE kisi_ad like '%'  || :aramaKelimesi||  '%'")
        suspend fun kisiArama(aramaKelimesi:String):List<Kisiler>

        @Insert
        suspend fun kisiEkle(kisiler: Kisiler)

        @Update
        suspend fun kisiGuncelle(kisiler: Kisiler)

        @Delete
        suspend fun kisiSil(kisiler: Kisiler)
}
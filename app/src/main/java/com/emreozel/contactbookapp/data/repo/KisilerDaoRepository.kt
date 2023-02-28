package com.emreozel.contactbookapp.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.emreozel.contactbookapp.data.entity.Kisiler
import com.emreozel.contactbookapp.room.KisilerDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KisilerDaoRepository(var kdao:KisilerDao){
    var kisilerListesi:MutableLiveData<List<Kisiler>>
    init {
        kisilerListesi= MutableLiveData()
    }
    fun kisileriGetir():MutableLiveData<List<Kisiler>>{
        return kisilerListesi
    }
    fun kisiKayit(kisi_ad:String,kisi_tel:String) {


         val job= CoroutineScope(Dispatchers.Main).launch {
             var  yeniKisi=Kisiler(0,kisi_ad,kisi_tel)
             kdao.kisiEkle(yeniKisi)

        }
    }
    fun KisiGuncelle(kisi_id:Int,kisi_ad:String,kisi_tel:String) {
        val job= CoroutineScope(Dispatchers.Main).launch {
            var  guncellenenKisi=Kisiler(kisi_id,kisi_ad,kisi_tel)
            kdao.kisiEkle(guncellenenKisi)

        }
    }
    fun kisiAra(aramaKelimesi:String){
        val job= CoroutineScope(Dispatchers.Main).launch {
            kisilerListesi.value=kdao.kisiArama(aramaKelimesi)
        }
    }
    fun kisiSil(kisi_id:Int){
        val job= CoroutineScope(Dispatchers.Main).launch {
            var  silinenKisi=Kisiler(kisi_id,"","")
            kdao.kisiEkle(silinenKisi)
            tumKisileriAl()

        }
    }
    fun tumKisileriAl(){
        val job= CoroutineScope(Dispatchers.Main).launch {
            kisilerListesi.value=kdao.tumKisiler()
        }
    }


}
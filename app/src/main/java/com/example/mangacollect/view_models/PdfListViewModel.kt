package com.example.mangacollect.view_models

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mangacollect.collections.pdfFile
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class PdfListViewModel : ViewModel() {
    @SuppressLint("MutableCollectionMutableState")
    var state = mutableStateListOf<pdfFile>()
    init {
        getDataFromDb()
    }
    private fun getDataFromDb(){
        viewModelScope.launch {
            state.addAll(getData())
        }
    }

}


suspend fun getData() : SnapshotStateList<pdfFile>{
    val db = FirebaseFirestore.getInstance()
    var pdfs = mutableStateListOf<pdfFile>()
    try{
        db.collection("pdfs").get().await().map {
            val pdf : pdfFile = it.toObject(pdfFile::class.java)
            pdfs.add(pdf)
        }
    }catch(e : FirebaseFirestoreException){

    }
    return pdfs
}
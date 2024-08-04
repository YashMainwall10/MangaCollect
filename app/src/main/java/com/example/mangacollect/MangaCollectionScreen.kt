package com.example.mangacollect

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.documentfile.provider.DocumentFile
import com.example.mangacollect.collections.pdfFile
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class MangaCollectionScreen : ComponentActivity() {
    private var pdfFile : Uri? = null
    private var fileName :String? = null

    var fileNameText = mutableStateOf("Document")
    var currentProgress = mutableStateOf(0f)

    private lateinit var storageReference : StorageReference

    private lateinit var fireStore : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        storageReference = FirebaseStorage.getInstance().reference.child("pdfs")

        fireStore = FirebaseFirestore.getInstance()
        val intent = Intent(this@MangaCollectionScreen , PdfListActivity::class.java)
        var finish2 = ::finish

        setContent {
            Column(verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize(1f)) {
                progressBar(curentProgress = currentProgress)
                textView(name = fileNameText)
                addPDF(launcher = launcher)
                uploadButton(::uploadOnCLick, LocalContext.current)
                Button(onClick = {
                    startActivity(intent)
                    finish()
                }) {
                    Text(text = "Change Activity")
                }

            }
        }
    }
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        pdfFile = uri
        fileName = uri?.let { DocumentFile.fromSingleUri(this, uri)?.name }
        fileNameText.value = fileName.toString()
    }

    fun uploadOnCLick(context: Context) : Unit{
        if(pdfFile != null){
            uploadPdfFileToFirebase(context)
        }
        else{
            Toast.makeText(context, "Please select a file to upload" , Toast.LENGTH_SHORT).show()
        }
    }

    private fun uploadPdfFileToFirebase(context: Context){
        val Name = fileNameText.value.toString()
//        fireStore.collection("pdfs").document("pBz89GrNoMaFhi2tz2Jv").get().addOnSuccessListener {
////            Toast.makeText(context , "${it.id} => ${it.data}" , Toast.LENGTH_SHORT).show()
//            if(it != null){
//                val pdfff = it.toObject<pdfFile>()
//                Toast.makeText(context , pdfff?.downloadUri , Toast.LENGTH_SHORT).show()
//            }
//
//        }
//        Toast.makeText(context , "${a}" , Toast.LENGTH_SHORT).show()
        val storagRef = storageReference.child("${System.currentTimeMillis()}/${Name}")
        pdfFile?.let {uri->
            storagRef.putFile(uri).addOnSuccessListener {
                storagRef.downloadUrl.addOnSuccessListener {downloadUri->

                    var pdfFile = pdfFile(Name , downloadUri.toString())
                    var PDF = hashMapOf("fileName" to Name , "downloadUri" to downloadUri)

                }
                currentProgress.value = 0f
                fileNameText.value = "Document"
            }.addOnProgressListener {uploadTask->
                val uploadPercent = uploadTask.bytesTransferred * 100/uploadTask.totalByteCount
                currentProgress.value = uploadPercent.toFloat()

            }.addOnFailureListener{
                Toast.makeText(context, "${it.message}" , Toast.LENGTH_SHORT).show()
            }
        }
    }
}


@Composable
fun progressBar(curentProgress : MutableState<Float>){
    CircularProgressIndicator(progress = curentProgress.value,
        modifier = Modifier.fillMaxWidth())

}

@Composable
fun addPDF(launcher : ActivityResultLauncher<String>){
    Button(onClick = {
        launcher.launch("application/pdf")
    }) {
        Text(text = "Add a Document")
    }
}

@Composable
fun uploadButton(uploadOnClick : (context : Context) -> Unit , context: Context){
    Button(onClick = { uploadOnClick(context) }) {
        Text("UPLOAD DOCUMENT")
    }
}

@Composable
fun textView(name : MutableState<String>){
    var id = remember {
        name
    }
    Text(text = id.value)
}


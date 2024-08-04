package com.example.mangacollect

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mangacollect.view_models.PdfListViewModel
import com.example.mangacollect.collections.pdfFile
import com.example.mangacollect.ui.theme.CC

class PdfListActivity : ComponentActivity() {
    private val viewModel by viewModels<PdfListViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var pdfs = viewModel.state
            screen(pdfs = pdfs , ::finisAcivity , ::changeActivity)
        }
    }
    fun finisAcivity(){
        finish()
    }
    fun changeActivity(fileUri : String){
        val intent = Intent(this@PdfListActivity, PdfViewerActivity::class.java)
        intent.putExtra("URI" , fileUri)
        startActivity(intent)
    }
}

@Composable
fun screen(pdfs: SnapshotStateList<pdfFile>, finisActivity:()->Unit, changeActivity:(String)->Unit){
    remember {
        pdfs
    }
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween){
        LazyColumn(){
            items(pdfs){
                ItemScreen(Str = it.fileName , it.downloadUri , finisActivity , changeActivity )
            }
        }
        Button(onClick = {
            pdfs.add(pdfFile("yash" , "Mainwall"))
        }) {
            Text(text = "CLick Me")
        }
    }
}

@Composable
fun ItemScreen(Str : String , Uri : String ,  finisActivity:()->Unit , changeActivity:(String)->Unit){
    Card(
        Modifier
            .fillMaxSize()
            .padding(10.dp) , elevation = CardDefaults.cardElevation(5.dp) , colors = CardDefaults.cardColors(
        containerColor = CC,
    )
    ){
        Row(horizontalArrangement = Arrangement.SpaceBetween , modifier = Modifier.fillMaxWidth().padding(25.dp)){
            Icon(painter = painterResource(id = R.drawable.pdf_icon),
                contentDescription = "PDF Icon" ,
                modifier = Modifier.size(40.dp , 40.dp) )

            Text(text = Str ,fontSize = 15.sp , textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clickable {
                        changeActivity(Uri)
                    })
        }
    }
}


package com.example.mangacollect

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mangacollect.ui.theme.MangaCollectTheme
import com.rajat.pdfviewer.compose.PdfRendererViewCompose

class PdfViewerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val URL0 = intent.getStringExtra("URI")
        val URL = "https://firebasestorage.googleapis.com/v0/b/mangacollect-35d84.appspot.com/o/pdfs%2F1721769049185%2Fbook.pdf?alt=media&token=5451fc47-ecd4-478f-9ced-79485c8c70eb"
        val URL2 = "https://firebasestorage.googleapis.com/v0/b/mangacollect-35d84.appspot.com/o/pdfs%2F1721760504649%2FEffective%20C%2B%2B%20.pdf?alt=media&token=4182cc02-f845-4107-a85a-862ed94eca8d"
        val URL3 = "https://firebasestorage.googleapis.com/v0/b/mangacollect-35d84.appspot.com/o/pdfs%2F1722204988716%2FBerserk%20Volume%2014.pdf?alt=media&token=1cb2d427-fd5b-4bb8-a4d9-bfd2b2a72c8f"
        setContent {
            if (URL0 != null) {
                PdfView(URL = URL0)
                Column(horizontalAlignment = AbsoluteAlignment.Right ,
                    verticalArrangement = Arrangement.Bottom ,
                    modifier = Modifier.fillMaxSize()){
                    Button(onClick = { /*TODO*/ }) {
                        Text("Download")
                    }
                }
            }

        }
    }
}

@Composable
fun PdfView(URL : String){
    PdfRendererViewCompose(url = URL ,
        lifecycleOwner = LocalLifecycleOwner.current ,
        modifier = Modifier.padding(10.dp))
}
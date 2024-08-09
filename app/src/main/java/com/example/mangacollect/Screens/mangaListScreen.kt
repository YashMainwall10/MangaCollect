package com.example.mangacollect.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mangacollect.R
import com.example.mangacollect.collections.mangaCollection
import com.example.mangacollect.collections.pdfFile

class mangaListScreen {
}


@Preview(showSystemUi = true)
@Composable
fun preview(){

//    var allMangas = remember{mutableStateListOf<mangaCollection>()}

    val pdfList1 = mutableListOf(pdfFile("Volume 1", "iunsangfe.com"))
    val pdfList2 = mutableListOf(pdfFile("Volume 2", "example.com/volume2"))
    val pdfList3 = mutableListOf(pdfFile("Volume 3", "example.com/volume3"))
    val pdfList4 = mutableListOf(pdfFile("Volume 4", "example.com/volume4"))
    val pdfList5 = mutableListOf(pdfFile("Volume 5", "example.com/volume5"))
    val pdfList6 = mutableListOf(pdfFile("Volume 6", "example.com/volume6"))
    val pdfList7 = mutableListOf(pdfFile("Volume 7", "example.com/volume7"))
    val pdfList8 = mutableListOf(pdfFile("Volume 8", "example.com/volume8"))
    val pdfList9 = mutableListOf(pdfFile("Volume 9", "example.com/volume9"))
    val pdfList10 = mutableListOf(pdfFile("Volume 10", "example.com/volume10"))

    val manga1 = mangaCollection("Naruto", pdfList1)
    val manga2 = mangaCollection("One Piece", pdfList2)
    val manga3 = mangaCollection("Bleach", pdfList3)
    val manga4 = mangaCollection("Dragon Ball", pdfList4)
    val manga5 = mangaCollection("Attack on Titan", pdfList5)
    val manga6 = mangaCollection("Fullmetal Alchemist", pdfList6)
    val manga7 = mangaCollection("Death Note", pdfList7)
    val manga8 = mangaCollection("My Hero Academia", pdfList8)
    val manga9 = mangaCollection("Fairy Tail", pdfList9)
    val manga10 = mangaCollection("Hunter x Hunter", pdfList10)

    val allMangas = remember{mutableStateListOf(
        manga1,
        manga2,
        manga3,
        manga4,
        manga5,
        manga6,
        manga7,
        manga8,
        manga9,
        manga10
    )}

    mangaGridView(mList = allMangas)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun mangaGridView(mList : SnapshotStateList<mangaCollection>){
    Column {
        TopAppBar(title = { Text(text = "Collections" ,
            fontSize = 34.sp,
            color = Color.White
        )} ,
            colors = TopAppBarDefaults.topAppBarColors(Color.Black)
        )
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(mList){
                container(collectionName = it.collectionName)
            }

        }
    }

}

@Composable
fun container(collectionName : String){
    Card(modifier = Modifier.padding(10.dp)){
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.padding(10.dp)){
            Image(painter = painterResource(id = R.drawable.collectionimage), contentDescription = "Image" ,
                modifier = Modifier.size(150.dp , 150.dp))
            Text(text = collectionName , fontSize = 25.sp)
        }
    }

}
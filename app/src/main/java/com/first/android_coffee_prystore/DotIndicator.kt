package com.first.android_coffee_prystore

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun DotIndicator(pageCount: Int, pagerState: PagerState) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .padding(top = 8.dp)
    ){
        repeat(pageCount){
                iteration ->
 DotIndicator(
     color = if (pagerState.currentPage == iteration){
         Color.Gray
     }
     else {
         Color.Gray.copy(alpha = 0.5f)
     }
 )
        }
    }
}

@Composable
fun DotIndicator(color: Color){
    Box(
        modifier = Modifier
            .padding(4.dp)
            .clip(CircleShape)
            .background(color = color)
            .size(12.dp)
    )
}
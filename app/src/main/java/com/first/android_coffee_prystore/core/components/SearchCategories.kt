package com.first.android_coffee_prystore.core.components


import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.first.android_coffee_prystore.MockUtils
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign



@Composable
fun SearchCategories(
    tags: List<String>,
    modifier: Modifier = Modifier,
){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(tags) {
            SearchTag(tag = it)
        }
    }
}



@Composable
fun SearchTag(tag: String){
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(all = 8.dp)
            .padding(vertical = 4.dp)

    )
    {
        Text(
            text = tag,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(48.dp)
        )
    }
}



@Preview(showSystemUi = true)
@Composable
private fun SearchCategoriesPreview(){
    SearchCategories(
        tags = MockUtils.loadMockSearchCategories()
    )
}

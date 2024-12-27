package com.first.android_coffee_prystore.prototype

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.first.android_coffee_prystore.BaseContentLayout
import com.first.android_coffee_prystore.MockUtils
import com.first.android_coffee_prystore.R
import com.first.android_coffee_prystore.core.components.PrimaryButton
import kotlinx.coroutines.CoroutineScope


@Composable
fun ProductScreen(
    viewModel: ProductViewModel,
    id: Long,
    onBackClick: () -> Unit,
    onBuyNowClick: () -> Unit
){
 LaunchedEffect(key1 = Unit){
 viewModel.handleUiEvent(ProductUiEvent.LoadData(id)
 )
     BaseContentLayout(
         viewModel = viewModel,
         onBackPressed = TODO(),
         content = TODO()
     ){
         uiState ->
         uiState?.let {
             ProductScreenContent(
                 product = id.product,
                addedToCart =  it.isAddedToCart,
               uiEvent =   viewModel::handleUiEvent,
               onBuyNowClick =  onBuyNowClick
             )
         }
     }
 }
    /*
    Scaffold(
        topBar = {
            TopAppBar(
                title = "Product",
                onBackClick = onBackClick
            )
        },
        content = {
            ProductScreenContent(
                modifier = Modifier.padding(it)
            )
        }
    )

     */
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun ProductScreenContent(
    product: ProductDetails,
    addedToCart: Boolean,
    uiEvent: (ProductUiEvent) -> Unit,
    onBuyNowClick: () -> Unit
){
    val scope = rememberCoroutineScope()
 val scaffoldState = rememberBottomSheetScaffoldState(
     BottomSheetState(
         initialValue = BottomSheetValue.Expanded
     )

 )
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetGesturesEnabled = false,
        sheetElevation = 8.dp,
        sheepShape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        sheetBackground = MaterialTheme.colorScheme.surfaceContainer,
        sheetContent = {
  BottomSheetContent(
      scaffoldState,
      scope,
      product,
      uiEvent,
      isAddedToCart,
      onBuyNowClick
  )
        }
    ){
HorizontalPageWithIndicators(product.imageUrls)

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetContent(scaffoldState: BottomSheetScaffoldState,
                       scope: CoroutineScope,
                       product: ProductDetails,
                       uiEvent: (ProductUiEvent) -> Unit,
                       addedToCart: Any, onBuyNowClick: () -> Unit) {

    Column(
        modifier = Modifier
        .fillMaxHeight(0.5f)
        .fillMaxWidth()
            .padding(all = 16.dp)

    ){
 Row(
     verticalAlignment = Alignment.CenterVertically,
     horizontalArrangement = Arrangement.SpaceBetween,
     modifier = Modifier.fillMaxWidth()
 ) {
     ProductTitle(title = product.title)
     Spacer(modifier = Modifier.width(8.dp))
 ProductPrice(price = product.price)
     FavouriteButton(product.isFavourite, uiEvent)
 }

        ProductCategory(category = product.category)
        Spacer(modifier = Modifier.height(8.dp))
        ProductDescription(description = product.description)
 Box(modifier = Modifier.fillMaxSize()){
     Row(
         horizontalArrangement = Arrangement.spacedBy(16.dp),
         verticalAlignment = Alignment.CenterVertically,
         modifier = Modifier
             .fillMaxWidth()
             .align(Alignment.BottomCenter)
     ){
         RoundedButton(
             icon = {
                 Icon(imageVector = if (isAddedToCart){
                     Icons.Outlined.CheckCircle
                 } else {
                     Icons.Filled.ShoppingCart
                 },
                 contentDescription = null
                 )
             },
             onClick = { uiEvent(ProductUiEvent.AddToCartOnClick) }
         )
         PrimaryButton(text = stringResource(R.string.buy_now),
             roundDp = 16.dp,
             onClick = {
                 onBuyNowClick.invoke()
             }

         )
     }
 }
    }

}

@Composable
fun RoundedButton(icon: @Composable () -> Unit) {




}

@Composable
fun ProductDescription(description: String) {

    Text(
        text = description,
        style = MaterialTheme.typography.bodyMedium,
        maxLines = 10,
        overflow = TextOverflow.Ellipsis
    )


}

@Composable
private fun ProductCategory(category: String) {

    Text(
        text = category,
        style = MaterialTheme.typography.bodyMedium,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteButton(favourite: Boolean, uiEvent: (ProductUiEvent) -> Unit) {

    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ){
        OutlinedIconToggleButton(
            checked = isFavourite,
            onCheckedChange = { uiEvent(ProductUiEvent.UpdateFavouriteOnClick) },
            content = {
                Icon(
                    modifier = Modifier.size(22.dp),
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null
                )
            }
        )
    }

}

@Composable
fun ProductPrice(price: Double) {

    Text(
        text = "\$$price",
        style = MaterialTheme.typography.headlineSmall,
        maxLines = 1,
        color = MaterialTheme.colorScheme.error,
        overflow = TextOverflow.Ellipsis
    )

}

@Composable
fun ProductTitle(title: String) {

    Text(
        text = title,
        style = MaterialTheme.typography.headlineMedium,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )

}




@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPageWithIndicators(imageUrls: List<String>) {
 val pagerCount = imageUrls.size
    val pagerState = rememberPagerState{pagerCount}
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
          //  .padding(horizontal = 16.dp)
        
    ){
        Column(
            modifier = Modifier
                .align(Alignment.Center)
        ){
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 20.dp),
                pageSpacing = 10.dp
            ){
                ProductPhoto(url = imageUrls[page])
            }
            DotIndicator(pageCount, pagerState)
        }
    }
}

@Composable
private fun ProductPhoto(url: String) {
 Image(
     alignment = Alignment.Center,
     painter = rememberAsyncImagePainter(model = url),
     contentScale = ContentScale.Crop,
     contentDescription = null,
     modifier = Modifier
         .fillMaxWidth()
         .height(250.dp)
         .clip(RoundedCornerShape(size = 12.dp))
 )
}

@Preview
@Composable
private fun ProductScreenContentPreview(){
    ProductScreenContent(
        product = MockUtils.loadMockProductsDetails().first(),
        isAddedToCart = false,
        uiEvent = {},
        onBuyNowClick = {},
    )
}

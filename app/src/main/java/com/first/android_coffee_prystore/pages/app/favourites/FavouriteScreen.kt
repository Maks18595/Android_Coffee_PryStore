package com.first.android_coffee_prystore.pages.app.favourites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.first.android_coffee_prystore.BaseContentLayout
import com.first.android_coffee_prystore.MockUtils
import com.first.android_coffee_prystore.R
import com.first.android_coffee_prystore.core.components.DeleteImageButton
import com.first.android_coffee_prystore.core.components.ProductDescription
import com.first.android_coffee_prystore.core.components.ProductPrice
import com.first.android_coffee_prystore.core.components.ToolbarTitle
import com.first.android_coffee_prystore.prototype.ProductDetails
import com.first.android_coffee_prystore.core.components.ProductTitle
import com.first.android_coffee_prystore.core.components.ProductImage


@Composable
fun FavouriteScreen(
 viewModel: FavouriteViewModel,
 onNavigateToProduct: (Long) -> Unit,
){

 LaunchedEffect(key1 = Unit){
     viewModel.handleUiEvent(
         FavouritesUiEvent.Initiate
     )
 }
    BaseContentLayout(
        viewModel = viewModel,
        onBackPressed = null
    ) { uiState ->
        uiState?.let {
            FavouritesScreenContent(
                products = it.items,
                event = viewModel::handleUiEvent,
                onNavigateToProduct = onNavigateToProduct
            )
        }
    }
    }

@Composable
private fun FavouritesScreenContent(
    products: Unit,
    event: (FavouritesUiEvent) -> Unit,
    onNavigateToProduct: (Long) -> Unit)
{


    Column(modifier = Modifier.fillMaxSize()) {
        ToolbarTitle(title = stringResource(id = R.string.label_favourite))


        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ){
            items(products){
                product ->
                ProductFavouriteCard(
                    product = product,
                    event = event,
                    onNavigateToProduct = onNavigateToProduct
                )
            }
        }


    }
}


@Composable
fun ProductFavouriteCard(
    product: ProductDetails,
    event: (FavouritesUiEvent) -> Unit,
    onNavigateToProduct: (Long) -> Unit
){
    Card(
        modifier = Modifier.padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            ProductImage(
                url = product.imageUrls.first(),

            ){
                onNavigateToProduct(product.id)
            }
            Column(
                modifier = Modifier.padding(start = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ProductTitle(Modifier, product.title)

                    ProductPrice(modifier = Modifier, price = product.price, style = MaterialTheme.typography.titleMedium)
                    DeleteButton(product.id, event)
                }
                ProductDescription(
                    maxLines = 3,
                 description = product.description)
            }
        }
    }
}



@Composable
fun DeleteButton(
    id: Long,
    event: (FavouritesUiEvent) -> Unit
) {
 Box(
     contentAlignment = Alignment.CenterEnd,
     modifier = Modifier.fillMaxWidth()
 ){
  DeleteImageButton {
      event(FavouritesUiEvent.OnItemDeleted(id))
  }
 }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FavouriteScreenContentPreview(
) {

    FavouritesScreenContent(
        products = MockUtils.loadMockFavourites(),
        event = {},
        onNavigateToProduct = {}
    )

}

/*
Scaffold(
topBar = { TopBar() },
content = {
    Content(
        modifier = Modifier.padding(it),
        onNavigateToProduct = onNavigateToProduct
    )
}
)

 */


/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(
                "Favourites",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        colors = TopAppBarDefaults.topAppBarColors()
    )
}

 */

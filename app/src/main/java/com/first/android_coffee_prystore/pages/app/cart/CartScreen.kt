package com.first.android_coffee_prystore.pages.app.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.first.android_coffee_prystore.BaseContentLayout
import com.first.android_coffee_prystore.R
import com.first.android_coffee_prystore.core.components.PrimaryButton
import com.first.android_coffee_prystore.core.components.ProductPrice
import com.first.android_coffee_prystore.core.components.ProductSquareImage
import com.first.android_coffee_prystore.core.components.ProductTitle
import com.first.android_coffee_prystore.core.components.ToolbarLayout
import com.first.android_coffee_prystore.core.components.ToolbarTitle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    viewModel: CartViewModel,
    onNavigateToPromoCode: () -> Unit,
    onNavigateToCheckout: () -> Unit,
    onNavigateToProduct: (Long) -> Unit
){

    LaunchedEffect(key1 = Unit){

        viewModel.handleUiEvent(
            CartUiEvent.LoadScreenData,

        )
    }

    BaseContentLayout(
        viewModel = viewModel
    ) { uiState ->
        uiState?.let{
            CartScreenContent(
                uiState = it,
                uiEvent = viewModel::handleUiEvent,
                onNavigateToPromoCode = onNavigateToPromoCode,
                onNavigateToConfirm = onNavigateToCheckout,
                onNavigateToProduct = onNavigateToProduct,
            )
        }
    }

    /*
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(text = "Cart")},
                actions = {
                    IconButton(onClick = onNavigateToNotifications){
                        Icon(imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications")
                    }
                },

            )
        },
        content = {
            CartScreenContent(
                modifier = Modifier.padding(it),
                onNavigateToConfirm = onNavigateToConfirm,
                onNavigateToProduct = onNavigateToProduct
            )
        }
    )

     */
}



@Composable
fun CartScreenContent(
    uiState: CartUiState,
    uiEvent: (CartUiEvent) -> Unit,
    onNavigateToConfirm: () -> Unit,
    onNavigateToProduct: () -> Unit,
    onNavigateToPromoCode: (Long) -> Unit,
) {
    Column {
        ToolbarLayout {
            ToolbarTitle(
                title = stringResource(id = R.string.cart)
            )
        }

        LazyColumn {
            items(uiState.products) { product ->
                CartProductItem(
                    productCart = product,
                    onNavigateToProduct = onNavigateToProduct,

                    )
            }
        }
        Column(
 modifier = Modifier.fillMaxSize()
     .weight(0.5f)
     .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LineDivider()
            PromoTitle(onNavigateToPromoCode)
            PriceDetailsRow(stringResource(R.string.subtotal), uiState.subtotal.toString())
            PriceDetailsRow(stringResource(R.string.shipping), uiState.shipping.toString())
            PriceDetailsRow(stringResource(R.string.discount), uiState.discount.toString())
            LineDivider()
            PriceDetailsRow(stringResource(R.string.total), uiState.total.toString())
            Spacer(modifier = Modifier.height(16.dp))
            PrimaryButton(
                text = stringResource(id = R.string.checkout),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
            ) {
                onNavigateToCheckout()
            }

        }
    }
}

fun onNavigateToCheckout() {
    TODO("Not yet implemented")
}

@Composable
fun PriceDetailsRow(title: String, price: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal =  16.dp, vertical = 4.dp)

    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            )
        ProductPrice(
            price = price,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier,

        )
    }
}

@Composable
fun PromoTitle(
    onNavigateToPromoCode: (Long) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
            .clickable {
                onNavigateToPromoCode()
            }
    ) {
        Text(text = stringResource(id = R.string.promo_code),
 style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,

        )
        Image(
 imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            modifier = Modifier.size(18.dp).padding(16.dp)

        )
    }
}
@Composable
fun LineDivider() {
    Box(modifier = Modifier
        .height(2.dp)
        .fillMaxWidth()
        .background(Color.Gray.copy(alpha = 0.5f))
        .padding(vertical = 4.dp)
    )
}


class ProductCart {

}



@Composable
fun CartProductItem(
    productCart: ProductCart,
    onNavigateToProduct: (Long) -> Unit,
    uiEvent: (CartUiEvent) -> Unit ){
    Card(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row {
  Box(
      modifier = Modifier
          .fillMaxWidth(0.25f)
          .fillMaxHeight()
          .padding(8.dp)
          .clip(RoundedCornerShape(12.dp))
          .clickable { onNavigateToProduct(productCart.id) }
  ){
      ProductSquareImage(imageUrl = productCart.imageUrl)
  }

            Column(
                modifier = Modifier
                    .size(80.dp, 60.dp)
                    .background(Color.Yellow),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                ProductTitle(modifier = Modifier, title = productCart.title, style = MaterialTheme.typography.bodyLarge)
                ProductCategory(category = productCart.category)
                ProductPrice(modifier = Modifier, price = productCart.price, style = MaterialTheme.typography.bodyMedium)
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .fillMaxWidth(0.4f)
                    .background(Color.Blue),
            ) {
                Row(
 modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.remove_circle),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable { uiEvent(CartUiEvent.OnDecreaseQuantity(productCart.id)) }
                    )
                    Text(
                        text = productCart.quantity.toString()
                    )
                    Image(
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable { uiEvent(CartUiEvent.OnIncreaseQuantity(productCart.id)) }
                    )
                }
            }
        }
    }

}


@Preview
@Composable
fun CartScreenPreview(

){
    CartScreenContent(
        uiState = CartUiState(),
        uiEvent = {},
        onNavigateToPromoCode = {},
        onNavigateToCheckout = {},
        onNavigateToProduct = {}
    )

}

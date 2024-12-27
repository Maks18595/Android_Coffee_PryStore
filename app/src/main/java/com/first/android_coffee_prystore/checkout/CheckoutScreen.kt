package com.first.android_coffee_prystore.checkout

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.first.android_coffee_prystore.BaseContentLayout
import com.first.android_coffee_prystore.DeliveryAddress
import com.first.android_coffee_prystore.MockUtils
import com.first.android_coffee_prystore.ProductCart
import com.first.android_coffee_prystore.R
import com.first.android_coffee_prystore.core.components.PrimaryButton
import com.first.android_coffee_prystore.core.components.ProductImage
import com.first.android_coffee_prystore.core.components.ProductSquareImage
import com.first.android_coffee_prystore.core.components.ProductTitle
import com.first.android_coffee_prystore.core.components.ToolbarLayout
import com.first.android_coffee_prystore.core.components.ToolbarTitle
import com.first.android_coffee_prystore.pages.app.cart.PromoTitle
import com.first.android_coffee_prystore.prototype.ProductDescription

@Composable
fun CheckoutScreen(
    viewModel: CheckoutViewModel,
    navigateToDeliveryAddresses: () -> Unit,
    navigateToPaymentMethods: () -> Unit,
    navigateToCart: () -> Unit,
    navigateToProduct: (Long) -> Unit,
    navigateToSuccessScreen: () -> Unit,
){
    LaunchedEffect(Unit){
        viewModel.handleUiEvent(CheckoutUiEvent.LoadScreenData)
    }

    BaseContentLayout(viewModel = viewModel){ uiState ->
        uiState?.let {
            CheckoutScreenContent(
                uiState = it,
                uiEvent = viewModel::handleUiEvent,
                navigateToDeliveryAddresses = navigateToDeliveryAddresses,
                navigateToPaymentMethods = navigateToPaymentMethods,
                navigateToCart = navigateToCart,
                navigateToProduct = navigateToProduct,
                navigateToSuccessScreen = navigateToSuccessScreen,
            )
        }
    }

}

@Composable
private fun CheckoutScreenContent(
    uiState: CheckoutUiState,
    uiEvent: (CheckoutUiEvent) -> Unit,
    navigateToDeliveryAddresses: () -> Unit,
    navigateToPaymentMethods: () -> Unit,
    navigateToCart: () -> Unit,
    navigateToProduct: (Long) -> Unit,
    navigateToSuccessScreen: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ToolbarLayout {
            ToolbarTitle(title = stringResource(R.string.lable_checkout))
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
 PromoTitle(
     title = stringResource(R.string.label_delivery_address),
     onClick = {navigateToDeliveryAddresses()}
 )
            LazyColumn(
                modifier = Modifier.fillMaxWidth().weight(0.25f),
            ){
                items(uiState.address){
                    address ->
                    CheckoutListItem(
                        icon = Icons.Default.Person,
                        iconSize = 48.dp,
                        title = paymentMethod.address,
                        description = paymentMethod.address,
                        isSelected = paymentMethod.isSelected,
                        onClick = { uiEvent(CheckoutUiEvent.SelectDeliveryAddress(address.id)) }
                    )
                }
            }
            PromoTitle(
                title = stringResource(R.string.label_delivery_address),
                onClick = {navigateToDeliveryAddresses()}
            )
            LazyColumn(
                modifier = Modifier.fillMaxWidth().weight(0.25f),
            ){
                items(uiState.paymentMethod){
                        paymentMethod ->
                    CheckoutListItem(
                        icon = Icons.Default.Person,
                        iconSize = 48.dp,
                        title = paymentMethod.name,
                        description = paymentMethod.number,
                        isSelected = paymentMethod.isSelected
                    )
                }
            }

            PromoTitle(
                title = stringResource(R.string.label_my_cart),
                onClick = {navigateToDeliveryAddresses()}
            )
            LazyRow(
                modifier = Modifier.fillMaxWidth().weight(0.25f),
                horizontalArragement = Arrangement.spaceBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ){
                items(uiState.products){
                        product ->
                    HorizontalProductItem(
                        product = product,
                        onClick = { navigateToProduct(product.id) }
                    )
                }
            }

            Box(
                modifier = Modifier
                    .weight(0.15f)
                    .padding(16.dp)

            ){
                PrimaryButton(text = stringResource(R.string.pay_now)){
                    navigateToSuccessScreen()
                }
            }

        }
    }
}

@Composable
fun HorizontalProductItem(product: ProductCart,
                          onClick: () -> Unit) {
 Row(
 verticalAlignment = Alignment.CenterVertically,
     modifier = Modifier
         .animateContentSize()
         .wrapContentHeight()
         .fillMaxWidth()
         .clickable { onClick(product.id) }
         .padding(8.dp)
         .background(color = MaterialTheme.colorScheme.secondaryContainer,
             shape = RoundedCornerShape(12.dp)
             )
 ){
 Box(
     modifier = Modifier
         .size(40.dp)
         .clip(RoundedCornerShape(12.dp))
 ){
     ProductSquareImage(imageUrl = product.imageUrl)
 }
     Column(
         verticalArrangement = Arrangement.Center,
         modifier = Modifier
             .align(Alignment.Top)
             .size(120.dp, 60.dp)
             .padding(horizontal = 8.dp)
     ) {
         ProductTitle(
             modifier = Modifier,
             title = product.title,
             style = MaterialTheme.typography.titleMedium
         )
         ProductCategory(category = product.category)
     }
 }
}

@Composable
fun CheckoutListItem(
    icon: ImageVector,
    iconSize: Dp,
    title: String,
    description: String,
    isSelected: Boolean,
    onClick: (Long) -> Unit,
    id: Long
) {
 Row(
     verticalAlignment = Alignment.CenterVertically,
     modifier = Modifier.padding(16.dp)
 ){
     ProductImage(modifier = Modifier, imageRes = icon, size = iconSize)

     Column(
         modifier = Modifier.padding(start = 8.dp)
     ){
         ProductTitle(
             modifier = Modifier,
             title = title,
             style = MaterialTheme.typography.titleMedium
         )
         ProductDescription(
             modifier = Modifier,
             description = description,
             maxLines = 1
         )
     }
     Spacer(modifier = Modifier.weight(1f))

     RadioButton(
         selected = isSelected,
         onClick = { onClick(id) },
         modifier = Modifier.clip(RoundedCornerShape(50))
     )
 }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CheckoutScreenContentPreview(

){
    CheckoutScreenContent(
        uiState = CheckoutUiState(
            address = MockUtils.loadMockAddresses(),
            paymentMethod = MockUtils.loadMockPaymentMethods(),
            products = MockUtils.loadMockCart(),
            total = 0.0
        ),
        uiEvent = {},
        navigateToDeliveryAddresses = {},
        navigateToPaymentMethods = {},
        navigateToCart = {},
        navigateToProduct = {},
        navigateToSuccessScreen = {}

    )
}
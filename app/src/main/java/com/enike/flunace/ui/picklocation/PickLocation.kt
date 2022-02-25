package com.enike.flunace.ui.picklocation

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.airbnb.lottie.compose.*
import com.enike.flunace.R
import com.enike.flunace.ui.components.DefaultButton
import com.enike.flunace.ui.theme.FlunaceTheme
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun Map() {
    val nigeria = LatLng(9.0765, 7.3986)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(nigeria, 10f)
    }
    val (text, setText) = remember { mutableStateOf("") }
    val context = LocalContext.current
    val resources = LocalContext.current.resources

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Expanded)
    )


    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.location_permiss))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        restartOnPlay = true
    )

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 400.dp,
        sheetContent = {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LottieAnimation(
                    modifier = Modifier.size(100.dp),
                    composition = composition,
                    progress = progress
                )

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Where are you?",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h5
                )

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Allow us access your location\nto find grocery stores around you",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body2
                )
                Spacer(modifier = Modifier.height(5.dp))

                DefaultButton(buttonText = "Yes, Allow", buttonClicked = { })
            }

        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            GoogleMap(
                googleMapOptionsFactory = {
                    GoogleMapOptions().camera(CameraPosition.fromLatLngZoom(nigeria, 10f))
                }, uiSettings = MapUiSettings(zoomControlsEnabled = false),
                cameraPositionState = cameraPositionState
            ) {
                Marker(
                    position = nigeria, title = "Nigeria",
                    icon = BitmapFromVector(
                        context = context,
                        vectorResId = com.enike.flunace.R.drawable.ic_location
                    )
                )
            }

            Box(modifier = Modifier.padding(all = 20.dp)) {
                Surface(
                    color = Color.White,
                    shape = MaterialTheme.shapes.small.copy(all = CornerSize(15.dp))
                ) {
                    TextField(
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 5.dp)
                            .fillMaxWidth(),
                        value = text,
                        onValueChange = setText,
                        textStyle = MaterialTheme.typography.subtitle1,
                        placeholder = {
                            Text(
                                color = Color.Gray,
                                text = "Search for a location",
                                style = MaterialTheme.typography.subtitle1,
                            )
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                        colors = TextFieldDefaults.textFieldColors(
                            disabledTextColor = Color.Transparent,
                            backgroundColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }

    }


}

@Composable
fun Loader() {

}

@Composable
fun PermissionBottomSheet() {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Expanded)
    )

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Loader()
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = "Where are you?",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h5
                )

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Allow us access your location\nto find grocery stores around you",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body2
                )
                Spacer(modifier = Modifier.height(10.dp))

                DefaultButton(buttonText = "Yes, Allow", buttonClicked = { })
            }
        }
    ) {

    }
}

private fun BitmapFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
    // below line is use to generate a drawable.
    val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)

    // below line is use to set bounds to our vector drawable.
    vectorDrawable!!.setBounds(0, 0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)

    // below line is use to create a bitmap for our
    // drawable which we have added.
    val bitmap = Bitmap.createBitmap(
        vectorDrawable.intrinsicWidth,
        vectorDrawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )

    // below line is use to add bitmap in our canvas.
    val canvas = Canvas(bitmap)

    // below line is use to draw our
    // vector drawable in canvas.
    vectorDrawable.draw(canvas)

    // after generating our bitmap we are returning our bitmap.
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}


@Preview(name = "MapPreview", showBackground = true)
@Composable
fun MapPreview() {
    FlunaceTheme {
        Surface() {
            Map()
        }
    }
}

@Preview(name = "BottomSheet", showBackground = true)
@Composable
fun BottomSheet() {
    FlunaceTheme {
        Surface() {
            PermissionBottomSheet()
        }
    }
}
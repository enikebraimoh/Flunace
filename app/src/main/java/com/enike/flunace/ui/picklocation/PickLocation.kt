package com.enike.flunace.ui.picklocation

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.widget.Toast
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.airbnb.lottie.compose.*
import com.enike.flunace.R
import com.enike.flunace.ui.components.DefaultButton
import com.enike.flunace.ui.theme.FlunaceTheme
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.launch


private const val DEFAULT_LAT = 11.714997
private const val DEFAULT_LON = 9.354813


@Composable
fun Map(navigate: () -> Unit) {
    val (Lat, setlat) = remember { mutableStateOf(DEFAULT_LAT) }
    val (Lon, setlon) = remember { mutableStateOf(DEFAULT_LON) }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(DEFAULT_LAT, DEFAULT_LON), 19.2f)
    }
    val (text, setText) = remember { mutableStateOf("") }

    val context = LocalContext.current
    val resources = LocalContext.current.resources
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Expanded)
    )
    val coroutineScope = rememberCoroutineScope()
    val locationPermissionsState = rememberPermissionState(
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    when (locationPermissionsState.status) {
        // If the camera permission is granted, then show screen with the feature enabled
        PermissionStatus.Granted -> {
            getUserLocation(context = context) {
                setlat(it.latitude)
                setlon(it.longitude)
                coroutineScope.launch {
                    bottomSheetScaffoldState.bottomSheetState.collapse()
                    cameraPositionState.animate(update = CameraUpdateFactory.newLatLngZoom(it, 17f))
                }

            }

        }
        is PermissionStatus.Denied -> {

        }
    }

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 0.dp,
        sheetGesturesEnabled = false,
        sheetContent = {
            BottomSheetContent(allowButton = {
                locationPermissionsState.launchPermissionRequest()
            })
        },

        sheetShape = MaterialTheme.shapes.large,
    ) { padding ->
        MapContent(
            context = context,
            padding = padding,
            userLocation = LatLng(Lat, Lon),
            cameraPositionState = cameraPositionState,
            text = text,
            navigate = { navigate() },
            setText = setText,
        ) {
            if (Lat != DEFAULT_LAT) {
                Marker(
                    position = LatLng(Lat, Lon),
                    title = "My location",
                    icon = BitmapFromVector(context, R.drawable.ic_location)
                )
            }
        }
    }
}


@Composable
fun MapContent(
    context: Context,
    padding: PaddingValues,
    userLocation: LatLng,
    cameraPositionState: CameraPositionState,
    text: String,
    setText: (String) -> Unit,
    navigate: () -> Unit,
    marker: @Composable () -> Unit
) {
    val animPinOffSef: Offset by animateOffsetAsState(
        targetValue = if (cameraPositionState.isMoving) Offset(0f, -20f)
        else Offset(0f, 0f),
        animationSpec = tween(
            durationMillis = 100,
            easing = FastOutSlowInEasing
        )
    )

    val animOffSef: Offset by animateOffsetAsState(
        targetValue = if (cameraPositionState.isMoving) Offset(0f, 100f)
        else Offset(0f, 0f),
        animationSpec = tween(
            durationMillis = 500,
            easing = FastOutSlowInEasing
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        GoogleMap(
            googleMapOptionsFactory = {
                GoogleMapOptions().camera(CameraPosition.fromLatLngZoom(userLocation, 10f))
            },
            uiSettings = MapUiSettings(
                zoomControlsEnabled = false,
                tiltGesturesEnabled = false,
                rotationGesturesEnabled = false
            ),
            cameraPositionState = cameraPositionState
        ) {
            marker()
        }

        Surface(
            modifier = Modifier.padding(all = 20.dp),
            color = Color.White,
            shape = MaterialTheme.shapes.small.copy(all = CornerSize(15.dp))
        ) {
            TextField(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 5.dp)
                    .fillMaxWidth(),
                value = text,
                onValueChange = setText,
                textStyle = MaterialTheme.typography.subtitle1.copy(color = Color.Black),
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

        if (userLocation.latitude != DEFAULT_LAT) {

            Image(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(bottom = 45.dp)
                    .offset(animPinOffSef.x.dp, animPinOffSef.y.dp),
                painter = painterResource(id = R.drawable.ic_droppin),
                contentDescription = ""
            )
            DefaultButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(20.dp)
                    .fillMaxWidth()
                    .offset(animOffSef.x.dp, animOffSef.y.dp),
                buttonText = "Pick Location",
                buttonClicked = {
                    navigate()
                    Toast.makeText(
                        context,
                        cameraPositionState.position.target.toString(),
                        Toast.LENGTH_SHORT
                    ).show()

                })

        }


    }


}

@Composable
fun Loader() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.location_permiss))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        restartOnPlay = true
    )

    LottieAnimation(
        modifier = Modifier.size(100.dp),
        composition = composition,
        progress = progress
    )
}

@Composable
fun BottomSheetContent(
    allowButton: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        Loader()

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

        DefaultButton(buttonText = "Yes, Allow", buttonClicked = { allowButton() })
    }
}


@Composable
private fun FeatureThatRequiresCameraPermission() {

    // Camera permission state
    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )

    when (cameraPermissionState.status) {
        // If the camera permission is granted, then show screen with the feature enabled
        PermissionStatus.Granted -> {
            Text("Camera permission Granted")
        }
        is PermissionStatus.Denied -> {
            Column {
                val textToShow =
                    if ((cameraPermissionState.status as PermissionStatus.Denied).shouldShowRationale) {
                        // If the user has denied the permission but the rationale can be shown,
                        // then gently explain why the app requires this permission
                        "The camera is important for this app. Please grant the permission."
                    } else {
                        // If it's the first time the user lands on this feature, or the user
                        // doesn't want to be asked again for this permission, explain that the
                        // permission is required
                        "Camera permission required for this feature to be available. " +
                                "Please grant the permission"
                    }

                Text(textToShow)
                Button(onClick = { cameraPermissionState.launchPermissionRequest() }) {
                    Text("Request permission")
                }
            }
        }
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


fun getUserLocation(context: Context, callback: (latlon: LatLng) -> Unit) {

    val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    try {
        val locationResult = fusedLocationProviderClient.lastLocation

        locationResult.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val lastKnownLocation = task.result

                if (lastKnownLocation != null) {
                    //Toast.makeText(context,"${lastKnownLocation.altitude},  ${lastKnownLocation.longitude}", Toast.LENGTH_SHORT).show()
                    callback(LatLng(lastKnownLocation.latitude, lastKnownLocation.longitude))
                }
            } else {
                Toast.makeText(
                    context,
                    "Exception, Current User location is null",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    } catch (e: SecurityException) {
        Toast.makeText(context, "Exception:  $e.message.toString()", Toast.LENGTH_SHORT).show()
    }

}


@Preview(name = "MapPreview", showBackground = true)
@Composable
fun MapPreview() {
    FlunaceTheme {
        Surface() {
            Map({})
        }
    }
}

@Preview(name = "BottomSheet", showBackground = true)
@Composable
fun BottomSheet() {
    FlunaceTheme {
        Surface() {
            BottomSheetContent({})
        }
    }
}
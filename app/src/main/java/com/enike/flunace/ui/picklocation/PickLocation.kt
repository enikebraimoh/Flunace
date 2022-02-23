package com.enike.flunace.ui.picklocation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.enike.flunace.ui.theme.FlunaceTheme
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker


@Composable
fun Map() {

    val singapore = LatLng(1.35, 103.87)

    GoogleMap(modifier = Modifier.fillMaxSize(),
        googleMapOptionsFactory = {
            GoogleMapOptions().camera(CameraPosition.fromLatLngZoom(singapore, 11f))
        }) {
        Marker(
            position = singapore,
            title = "singapore",
            snippet = "marker in singapore"
        )
    }
}


@Preview(name = "MapPreview")
@Composable
fun MapPreview() {
    FlunaceTheme {
        Surface() {
            Map()
        }
    }
}
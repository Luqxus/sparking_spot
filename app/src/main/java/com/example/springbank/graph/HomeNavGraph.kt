package com.example.springbank.graph

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.springbank.screens.HomeScreen
import com.example.springbank.screens.Screens
import com.example.springbank.R
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerDragState


@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = Screens.Home.route
    ) {
        composable(
            route = Screens.Home.route
        ) {
            HomeView()
        }
    }
}


data class Parking(
    val name: String,
    val imageUrl: String,
    val distance: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView() {

    val parkingSpots: List<Parking> = listOf(
        Parking(name = "Mall Parking", imageUrl = "", distance = "10 Mtr"),
        Parking(name = "Mall Parking", imageUrl = "", distance = "10 Mtr"),
        Parking(name = "Mall Parking", imageUrl = "", distance = "10 Mtr"),
        Parking(name = "Mall Parking", imageUrl = "", distance = "10 Mtr"),
        Parking(name = "Mall Parking", imageUrl = "", distance = "10 Mtr")
    )


    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Parking")
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.menu),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {paddingValues ->
        Surface(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        ) {
            MapView()
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {

                    LazyRow (
                        contentPadding = PaddingValues(vertical = 100.dp, horizontal = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ){
                        items(
                            items = parkingSpots,
                            itemContent = { item: Parking ->
                                ParkingSpotCard(spot = item)
                            }
                        )
                }
            }
        }
    }
}

@Composable
fun ParkingSpotCard(spot: Parking) {
    Card (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
        ),
        modifier = Modifier
            .size(width = 240.dp, height = 100.dp)
    ) {
        Row {
            Box(
                modifier = Modifier.size(width = 100.dp, height = 100.dp),
            ) {
                AsyncImage(
                    model = "https://example.com/image.jpg",
                    contentDescription = "Translated description of what the image contains"
                )
            }
        }
    }
}

@Composable
fun MapView() {
    val singapore = LatLng(1.35, 103.87)
    val singaporeMarkerState = rememberMarkerDragState()
    val cameraPositionState = rememberCameraPositionState() {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize().background(color = Color.Black),
        uiSettings = MapUiSettings(zoomControlsEnabled = false),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            position = singapore,
            markerDragState = singaporeMarkerState,
            title = "Singapore",
            snippet = "Marker in Singapore"
        )
    }
}
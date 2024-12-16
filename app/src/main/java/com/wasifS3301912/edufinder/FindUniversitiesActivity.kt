package com.wasifS3301912.edufinder

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

class FindUniversitiesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TopSchoolsList(::gotoUniversityDetails)
        }
    }

    private fun gotoUniversityDetails(universityDetails: UniversityDetails) {
        SelectedUniv.universityDetails = universityDetails
        startActivity(Intent(this, UniversityDetailsActivity::class.java))
    }
}


@Composable
fun TopSchoolsList(
    onPlaceDetails: (universityDetails: UniversityDetails) -> Unit
) {
    val allPlaces = getUniversityDetails() // Retrieve the list of universities
    var selectedType by remember { mutableStateOf("") } // Default is "All" (empty string)
    val filteredPlaces = remember(selectedType) {
        if (selectedType.isEmpty()) allPlaces else allPlaces.filter { it.type == selectedType }
    }

    val context = LocalContext.current as Activity

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Header Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(id = R.color.evergreen)
                )
                .padding(vertical = 6.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile Icon",
                tint = Color.White,
                modifier = Modifier.size(24.dp).clickable {
                    context.startActivity(Intent(context, ProfileActivity::class.java))
                }
            )
            Text(
                text = "Home",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White
            )
            Image(
                painter = painterResource(id = R.drawable.baseline_live_help_24),
                contentDescription = "Help Icon",
                modifier = Modifier.size(24.dp)
                    .clickable {
                        context.startActivity(Intent(context, ContactUsActivity::class.java))
                    }
            )
        }
        // Chip Filter Row
        ChipFilterRow(
            types = listOf("All", "School", "College", "University"),
            selectedType = selectedType,
            onTypeSelected = { type ->
                selectedType = if (type == "All") "" else type
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        /*
        Card(
            elevation = cardElevation(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.cambridge_university),
                            contentDescription = "Image 1",
                            modifier = Modifier.size(60.dp)
                        )
                        Text(text = "Text 1", style = MaterialTheme.typography.bodySmall)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.uc_berkeley),
                            contentDescription = "Image 2",
                            modifier = Modifier.size(60.dp)
                        )
                        Text(text = "Text 2", style = MaterialTheme.typography.bodySmall)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.mit_university),
                            contentDescription = "Image 3",
                            modifier = Modifier.size(60.dp)
                        )
                        Text(text = "Text 3", style = MaterialTheme.typography.bodySmall)
                    }
                }

            }
        }
        */

        // Top Universities Text
        Text(
            text = "Top Universities",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        // LazyColumn to display filtered places
        LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)

        ) {
            items(filteredPlaces.size) { index ->
                TopSchools(filteredPlaces[index], onPlaceDetails)
            }
        }
    }
}

@Composable
fun ChipFilterRow(
    types: List<String>,
    selectedType: String,
    onTypeSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        types.forEach { type ->
            FilterChip(
                selected = selectedType == type || (type == "All" && selectedType.isEmpty()),
                onClick = { onTypeSelected(type) },
                label = {
                    Text(text = type)
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = colorResource(id = R.color.evergreen),
                    selectedLabelColor = colorResource(id = R.color.white),
                    containerColor = Color.LightGray,
                    labelColor = colorResource(id = R.color.black)
                )
            )
        }
    }
}

@Composable
fun TopSchools(
    university: UniversityDetails,
    onPlaceDetails: (universityDetails: UniversityDetails) -> Unit
) {
    Card(
        elevation = cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onPlaceDetails(university) }
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(university.universityImage),
                contentDescription = "University Image",
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = university.universityName,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location Icon",
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = university.universityLocation,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Text(
                    text = "Type: University",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}


fun getUniversityDetails(): List<UniversityDetails> {
    return listOf(
        // Universities
        UniversityDetails(
            universityName = "University of York",
            universityLocation = "York, North Yorkshire",

            universityDescription = "The University of York has over 20,000+ students out of which 5,500 international students from more than 150 countries. The University of York is among the most prestigious universities in the United Kingdom. York University UK is also part of the prestigious Russel Group of universities in the UK. The university has maintained its position among the top 150 universities globally.",
            universityImage = R.drawable.harvard_university, // Replace with actual drawable resource
            universityAdmissionDate = "31-12-2025",
            type = "University",
            programsOffered = "Law, Business, Medicine, Engineering, Arts, Sciences",
            faculty = "James Roy - Computer Science",
            facilities = "Library, Laboratories, Sports Complex, Hostels, Dining Services",
            featureList = listOf(
                FeaturesData("379 Courses","₹9.32 L - 46.67 L","IELTS,PTE,+1","7","About University of York"),

        )
        ),
        UniversityDetails(
            universityName = "Teesside University",
            universityLocation = "Middlesbrough, North Yorkshire",
            universityDescription = "Founded in 1930, Teesside University is a public university with its main campus in Middlesbrough, North Yorkshire in North East England. According to the Shiksha grading, the overall ranking of Tesside U is C++. Regarding the course grading,\n" +
                    "the majority of the courses rank in the B++ and B categories, which indicates the quality of education in this university is impressive. Academic divisions at the university include\n" +
                    "Arts & Creative Industries; Computing, Engineering & Digital Technologies; Health & Life Sciences; Social Sciences, Humanities & Law; and Business.",
            universityImage = R.drawable.stanford_university, // Replace with actual drawable resource
            universityAdmissionDate = "03-03-2025",
            type = "University",
            programsOffered = "Computer Science, Business, Medicine, Engineering, Humanities",
            faculty = "Michael Smith - Artificial Intelligence",
            facilities = "Innovation Hub, Gym, Hostels, Cafeterias, Research Labs",
            featureList = listOf(
                FeaturesData("357 Courses","₹6.48 L - 18.05 L","IELTS,PTE","8","About Teesside University"),
            )

                ),
        UniversityDetails(
            universityName = "York St John University",
            universityLocation = "York, North Yorkshire",
            universityDescription = "Founded in 1841, York St John University (YSJ) is an open public research university located in York, England. When it comes to YSJ's rankings, US News & World Report has placed it at #166 in National Universities. It is home to more than 400 international students, representing more than 100 countries around the globe, which contributes about 6% in their total student population. YSJ's acceptance rate stands at 75%, which makes it a lesser competitive university to get into. YSJ is famous for its academic excellence and providing a learning environment that is safe, exciting, inclusive and inspirational.",
            universityImage = R.drawable.oxford_university, // Replace with actual drawable resource
            universityAdmissionDate = "10-02-2025",
            type = "University",
            programsOffered = "Medicine, Philosophy, Computer Science, Arts, Law",
            faculty = "Laura Watson - Medicine",
            facilities = "Historic Libraries, Sports Centers, Museums, Residential Colleges",
            featureList = listOf(
                FeaturesData("16 Courses","₹10.62 L - 13.8 L","IELTS,PTE,+1","-/-","About York St John University"),
            )
        )

    )



}





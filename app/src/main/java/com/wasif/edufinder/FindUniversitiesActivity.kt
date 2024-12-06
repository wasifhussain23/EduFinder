package com.wasif.edufinder

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FindUniversitiesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TopSchoolsList(::gotoUniversityDetails)
        }
    }

    private fun gotoUniversityDetails(universityDetails: UniversityDetails) {
        SelectedUniv.universityDetails=universityDetails
        startActivity(Intent(this,UniversityDetailsActivity::class.java))
    }
}

@Preview(showBackground = true)
@Composable
fun TopSchoolsListPreview() {
    TopSchoolsList(onPlaceDetails = {})
}

@Composable
fun TopSchoolsList(onPlaceDetails: (universityDetails: UniversityDetails) -> Unit) {
    // Fetch doctors based on the selected speciality
    val places = getUniversityDetails()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(id = R.color.evergreen)
                )
                .padding(vertical = 6.dp, horizontal = 16.dp)
        ) {
            Image(
                modifier = Modifier.size(32.dp),
                painter = painterResource(id = R.drawable.ic_edu_finder),
                contentDescription = "App Icon"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Top Universities",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = Color.White
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 12.dp)
        ) {

            items(places.size) { index ->
                TopSchools(places[index], onPlaceDetails)
            }
        }
    }

}


@Composable
fun TopSchools(
    universityDetails: UniversityDetails,
    onUniversityDetails: (universityDetails: UniversityDetails) -> Unit
) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onUniversityDetails.invoke(universityDetails) },
        elevation = CardDefaults.cardElevation(4.dp),
    )
    {
        Column(
            modifier = Modifier.fillMaxSize()
        )
        {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                painter = painterResource(id = universityDetails.universityImage),
                contentDescription = "Image",
                contentScale = ContentScale.FillBounds
            )


            Row(
                modifier = Modifier.background(
                    color = colorResource(id = R.color.evergreen)
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = universityDetails.universityName,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(start = 12.dp, top = 0.dp)

                )

                Spacer(modifier = Modifier.weight(1f))


            }
        }

    }
}


fun getUniversityDetails(): List<UniversityDetails> {
    return listOf(
        UniversityDetails(
            universityName = "Harvard University",
            universityLocation = "Cambridge, Massachusetts, USA",
            universityDescription = "Harvard University is one of the most prestigious universities in the world, known for its academic excellence and vast research opportunities.",
            universityImage = R.drawable.harvard_university, // Replace with actual drawable resource
            universityAdmissionDate = "31-12-2025"
        ),
        UniversityDetails(
            universityName = "Stanford University",
            universityLocation = "Stanford, California, USA",
            universityDescription = "Stanford is known for its entrepreneurial spirit, world-class faculty, and vibrant campus life.",
            universityImage = R.drawable.stanford_university, // Replace with actual drawable resource
            universityAdmissionDate = "03-03-2025"
        ),
        UniversityDetails(
            universityName = "University of Oxford",
            universityLocation = "Oxford, England",
            universityDescription = "Oxford is one of the oldest universities in the world and has a global reputation for high academic standards.",
            universityImage = R.drawable.oxford_university, // Replace with actual drawable resource
            universityAdmissionDate = "10-02-2025"
        ),
        UniversityDetails(
            universityName = "University of Cambridge",
            universityLocation = "Cambridge, England",
            universityDescription = "The University of Cambridge is famous for its historic campuses, outstanding research, and academic excellence.",
            universityImage = R.drawable.cambridge_university, // Replace with actual drawable resource
            universityAdmissionDate = "26-01-2025"
        ),
        UniversityDetails(
            universityName = "MIT (Massachusetts Institute of Technology)",
            universityLocation = "Cambridge, Massachusetts, USA",
            universityDescription = "MIT is renowned for its cutting-edge research and a leader in technological advancements and innovation.",
            universityImage = R.drawable.mit_university, // Replace with actual drawable resource
            universityAdmissionDate = "20-01-2025"
        ),
        UniversityDetails(
            universityName = "University of California, Berkeley",
            universityLocation = "Berkeley, California, USA",
            universityDescription = "UC Berkeley is a public research university known for its distinguished faculty, rigorous programs, and diverse student body.",
            universityImage = R.drawable.uc_berkeley, // Replace with actual drawable resource
            universityAdmissionDate = "15-02-2025"
        ),
        UniversityDetails(
            universityName = "Yale University",
            universityLocation = "New Haven, Connecticut, USA",
            universityDescription = "Yale is a prestigious Ivy League university offering a broad range of academic disciplines and a commitment to research.",
            universityImage = R.drawable.yale_university, // Replace with actual drawable resource
            universityAdmissionDate = "10-01-2025"
        ),

        )
}




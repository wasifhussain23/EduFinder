package com.wasifS3301912.edufinder

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.database.FirebaseDatabase

class SessionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SessionActivityScreen()
        }
    }
}

@Composable
fun SessionActivityScreen() {
    var studentEmail by remember { mutableStateOf("") }
    var studentPassword by remember { mutableStateOf("") }


    val context = LocalContext.current as Activity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.evergreen))
    ) {
        Spacer(modifier = Modifier.height(94.dp))

        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Welcome,",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = Color.White
        )

        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Glad to see you!",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(64.dp))

        Text(
            modifier = Modifier.padding(start = 12.dp),
            text = "Enter Email Address",
            color = Color.White
        )

        Spacer(
            modifier = Modifier
                .height(6.dp)
                .align(Alignment.Start)
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            value = studentEmail,
            onValueChange = { studentEmail = it },
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                unfocusedContainerColor = colorResource(id = R.color.evergreen),
                unfocusedIndicatorColor = Color.White,
                focusedContainerColor = colorResource(id = R.color.evergreen),
                focusedIndicatorColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            modifier = Modifier.padding(start = 12.dp),
            text = "Enter Password",
            color = Color.White
        )

        Spacer(
            modifier = Modifier
                .height(6.dp)
                .align(Alignment.Start)
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            value = studentPassword,
            onValueChange = { studentPassword = it },
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                unfocusedContainerColor = colorResource(id = R.color.evergreen),
                unfocusedIndicatorColor = Color.White,
                focusedContainerColor = colorResource(id = R.color.evergreen),
                focusedIndicatorColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(64.dp))

        Button(
            onClick = {

                when{
                    studentEmail.isEmpty() -> {
                        Toast.makeText(context, "Enter Email", Toast.LENGTH_SHORT).show()
                    }
                    studentPassword.isEmpty() -> {
                        Toast.makeText(context,"Enter Password",Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        val firebaseDatabase = FirebaseDatabase.getInstance()
                        val databaseReference = firebaseDatabase.getReference("Students").child(studentEmail.replace(".", ","))

                        databaseReference.get().addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val userData = task.result?.getValue(StudentData::class.java)
                                if (userData != null) {
                                    if (userData.password == studentPassword) {
                                        Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                                        StudentDetails.saveStudentLoginDetails(context,true)
                                        StudentDetails.saveStudentEmail(context,userData.email)
                                        StudentDetails.saveStudentName(context,userData.fullName)
                                        context.startActivity(Intent(context, FindUniversitiesActivity::class.java))
                                        context.finish()
                                    } else {
                                        Toast.makeText(context, "Invalid Password", Toast.LENGTH_SHORT).show()
                                    }
                                } else {
                                    Toast.makeText(context, "Account Not Found", Toast.LENGTH_SHORT).show()
                                }
                            } else {
                                Toast.makeText(
                                    context,
                                    "Exception Message",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.misty_mint),
                contentColor = colorResource(
                    id = R.color.evergreen
                )
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            )
        }

        Spacer(modifier = Modifier.height(64.dp))


        Row(
            verticalAlignment = Alignment.CenterVertically
        )
        {

            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .height(2.dp)
                    .padding(horizontal = 6.dp)
                    .background(Color.White)

            )

            Text(
                text = "or Register",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = Color.White,
                modifier = Modifier.clickable {
                    context.startActivity(Intent(context, JoinActivity::class.java))
                    context.finish()
                }
            )

            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .height(2.dp)
                    .padding(horizontal = 6.dp)
                    .background(Color.White)

            )

        }


    }

}


@Preview(showBackground = true)
@Composable
fun SessionActivityScreenPreview() {
    SessionActivityScreen()
}
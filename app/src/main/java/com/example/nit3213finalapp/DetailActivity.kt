package com.example.nit3213finalapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.nit3213finalapp.model.Entity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val entity: Entity? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("COURSE_DETAIL", Entity::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("COURSE_DETAIL") as? Entity
        }

        val titleText = findViewById<TextView>(R.id.courseNameText)
        val descriptionText = findViewById<TextView>(R.id.descriptionText)
        val fullDetailsText = findViewById<TextView>(R.id.creditsText)
        val logoutButton = findViewById<Button>(R.id.logoutButton)
        val backButton = findViewById<Button>(R.id.backButton)  // 👈 added back button

        entity?.let {
            // Dynamically select the best title field
            titleText.text = it.courseName ?: it.event ?: it.name ?: it.destination ?: it.title ?: it.species ?: "Untitled"
            descriptionText.text = it.description ?: "No description available."

            val details = StringBuilder()

            // Append all possible fields if present
            it.courseCode?.let { details.append("Code: $it\n") }
            it.instructor?.let { details.append("Instructor: $it\n") }
            it.credits?.let { details.append("Credits: $it\n") }
            it.startYear?.let { details.append("Start Year: $it\n") }
            it.endYear?.let { details.append("End Year: $it\n") }
            it.location?.let { details.append("Location: $it\n") }
            it.keyFigure?.let { details.append("Key Figure: $it\n") }
            it.team?.let { details.append("Team: $it\n") }
            it.position?.let { details.append("Position: $it\n") }
            it.stats?.let { details.append("Stats: $it\n") }
            it.duration?.let { details.append("Duration: $it\n") }
            it.tools?.let { details.append("Tools: $it\n") }
            it.species?.let { details.append("Species: $it\n") }
            it.scientificName?.let { details.append("Scientific Name: $it\n") }
            it.habitat?.let { details.append("Habitat: $it\n") }
            it.diet?.let { details.append("Diet: $it\n") }
            it.conservationStatus?.let { details.append("Conservation Status: $it\n") }
            it.averageLifespan?.let { details.append("Average Lifespan: $it years\n") }
            it.destination?.let { details.append("Destination: $it\n") }
            it.country?.let { details.append("Country: $it\n") }
            it.bestSeason?.let { details.append("Best Season: $it\n") }
            it.popularAttraction?.let { details.append("Attraction: $it\n") }

            fullDetailsText.text = details.toString()
        }

        logoutButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        backButton.setOnClickListener {
            finish() // Takes user back to Dashboard
        }
    }
}

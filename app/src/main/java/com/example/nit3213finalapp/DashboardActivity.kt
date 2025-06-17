package com.example.nit3213finalapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nit3213finalapp.model.Entity

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val recyclerViewCourses = findViewById<RecyclerView>(R.id.recyclerViewCourses)
        recyclerViewCourses.layoutManager = LinearLayoutManager(this)

        val entityList = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("COURSE_LIST", ArrayList::class.java) as? ArrayList<Entity>
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("COURSE_LIST") as? ArrayList<Entity>
        } ?: arrayListOf()

        if (entityList.isEmpty()) {
            Toast.makeText(this, "No data received!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Items received: ${entityList.size}", Toast.LENGTH_SHORT).show()
        }

        val adapter = EntityAdapter(entityList) { selectedEntity ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("COURSE_DETAIL", selectedEntity)
            startActivity(intent)


        }

        recyclerViewCourses.adapter = adapter

        val logoutButton = findViewById<Button?>(R.id.logoutButton)
        logoutButton?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()


        }
    }
}

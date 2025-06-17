package com.example.nit3213finalapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nit3213finalapp.model.Entity

class EntityAdapter(
    private val entityList: List<Entity>,
    private val onItemClick: (Entity) -> Unit
) : RecyclerView.Adapter<EntityAdapter.EntityViewHolder>() {

    inner class EntityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleText: TextView = itemView.findViewById(R.id.courseTitleTextView)
        private val subtitleText: TextView = itemView.findViewById(R.id.courseSubtitleTextView)

        fun bind(entity: Entity) {
            // Try known fields first
            val knownTitle = listOfNotNull(
                entity.courseName,
                entity.event,
                entity.species,
                entity.destination,
                entity.name,
                entity.title,
                entity.team
            ).firstOrNull()

            // Reflection fallback (safe with try-catch)
            val reflectedTitle = try {
                Entity::class.java.declaredFields
                    .firstOrNull { it.type == String::class.java && it.get(entity) != null }
                    ?.get(entity) as? String
            } catch (e: Exception) {
                null
            }

            titleText.text = knownTitle ?: reflectedTitle ?: "Untitled"

            // Subtitle fallback (known subtitle fields)
            subtitleText.text = listOfNotNull(
                entity.courseCode,
                entity.country,
                entity.scientificName,
                entity.location,
                entity.instructor,
                entity.team,
                entity.position
            ).firstOrNull() ?: ""

            itemView.setOnClickListener {
                onItemClick(entity)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_course, parent, false)
        return EntityViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        holder.bind(entityList[position])
    }

    override fun getItemCount(): Int = entityList.size
}

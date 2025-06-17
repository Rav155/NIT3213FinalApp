package com.example.nit3213finalapp.model

import java.io.Serializable

data class Entity(
    val courseName: String? = null,
    val courseCode: String? = null,
    val instructor: String? = null,
    val credits: Int? = null,

    val event: String? = null,
    val startYear: Int? = null,
    val endYear: Int? = null,
    val location: String? = null,
    val keyFigure: String? = null,

    val species: String? = null,
    val scientificName: String? = null,
    val habitat: String? = null,
    val diet: String? = null,
    val conservationStatus: String? = null,
    val averageLifespan: Int? = null,

    val destination: String? = null,
    val country: String? = null,
    val bestSeason: String? = null,
    val popularAttraction: String? = null,

    val name: String? = null,
    val team: String? = null,
    val position: String? = null,
    val stats: String? = null,
    val title: String? = null,
    val duration: String? = null,
    val tools: String? = null,

    val description: String? = null
) : Serializable

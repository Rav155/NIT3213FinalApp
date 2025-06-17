package com.example.nit3213finalapp.model

import java.io.Serializable

data class DashboardResponse(
    val entities: List<Entity>
) : Serializable

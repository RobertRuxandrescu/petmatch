package com.petmatch.app.data.model

data class PetPost(
    val id: String = "",
    val userId: String = "",
    val userDisplayName: String = "",
    val userPhotoUrl: String = "",
    val petName: String = "",
    val petBreed: String = "",
    val petAge: Int = 0,
    val petGender: String = "", // "Male" or "Female"
    val petDescription: String = "",
    val petImageUrl: String = "",
    val location: String = "",
    val contactInfo: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val isActive: Boolean = true
)




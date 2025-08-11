package com.petmatch.app.data

import com.petmatch.app.data.model.PetPost

object SampleData {
    
    fun getSamplePetPosts(): List<PetPost> {
        return listOf(
            PetPost(
                id = "1",
                userId = "user1",
                userDisplayName = "Sarah Johnson",
                userPhotoUrl = "",
                petName = "Buddy",
                petBreed = "Golden Retriever",
                petAge = 3,
                petGender = "Male",
                petDescription = "Looking for a friendly companion for my energetic Golden Retriever. He loves playing fetch and going for walks. He's very social and gets along well with other dogs.",
                petImageUrl = "",
                location = "New York, NY",
                contactInfo = "sarah.j@email.com"
            ),
            PetPost(
                id = "2",
                userId = "user2",
                userDisplayName = "Mike Chen",
                userPhotoUrl = "",
                petName = "Luna",
                petBreed = "Husky",
                petAge = 2,
                petGender = "Female",
                petDescription = "Beautiful Siberian Husky looking for a mate. She's very playful and loves the outdoors. Perfect for someone who enjoys active lifestyle.",
                petImageUrl = "",
                location = "Los Angeles, CA",
                contactInfo = "mike.chen@email.com"
            ),
            PetPost(
                id = "3",
                userId = "user3",
                userDisplayName = "Emma Davis",
                userPhotoUrl = "",
                petName = "Max",
                petBreed = "Labrador Retriever",
                petAge = 4,
                petGender = "Male",
                petDescription = "Friendly Labrador looking for a companion. He's great with kids and other pets. Very well trained and obedient.",
                petImageUrl = "",
                location = "Chicago, IL",
                contactInfo = "emma.davis@email.com"
            ),
            PetPost(
                id = "4",
                userId = "user4",
                userDisplayName = "David Wilson",
                userPhotoUrl = "",
                petName = "Bella",
                petBreed = "Poodle",
                petAge = 2,
                petGender = "Female",
                petDescription = "Elegant Poodle seeking a mate. She's very intelligent and loves to learn new tricks. Great family pet.",
                petImageUrl = "",
                location = "Miami, FL",
                contactInfo = "david.wilson@email.com"
            ),
            PetPost(
                id = "5",
                userId = "user5",
                userDisplayName = "Lisa Brown",
                userPhotoUrl = "",
                petName = "Rocky",
                petBreed = "German Shepherd",
                petAge = 3,
                petGender = "Male",
                petDescription = "Strong and loyal German Shepherd looking for a companion. He's very protective and great with families.",
                petImageUrl = "",
                location = "Seattle, WA",
                contactInfo = "lisa.brown@email.com"
            )
        )
    }
}




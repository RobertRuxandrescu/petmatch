package com.petmatch.app.data.model

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PetPostTest {
    
    @Test
    fun `PetPost should have correct default values`() {
        val post = PetPost()
        
        assertEquals("", post.id)
        assertEquals("", post.userId)
        assertEquals("", post.userDisplayName)
        assertEquals("", post.userPhotoUrl)
        assertEquals("", post.petName)
        assertEquals("", post.petBreed)
        assertEquals(0, post.petAge)
        assertEquals("", post.petGender)
        assertEquals("", post.petDescription)
        assertEquals("", post.petImageUrl)
        assertEquals("", post.location)
        assertEquals("", post.contactInfo)
        assertTrue(post.isActive)
        assert(post.createdAt > 0)
    }
    
    @Test
    fun `PetPost should be created with provided values`() {
        val post = PetPost(
            id = "post123",
            userId = "user123",
            userDisplayName = "John Doe",
            userPhotoUrl = "https://example.com/user.jpg",
            petName = "Buddy",
            petBreed = "Golden Retriever",
            petAge = 3,
            petGender = "Male",
            petDescription = "Friendly dog looking for a companion",
            petImageUrl = "https://example.com/pet.jpg",
            location = "New York, NY",
            contactInfo = "john@example.com",
            createdAt = 1234567890L,
            isActive = true
        )
        
        assertEquals("post123", post.id)
        assertEquals("user123", post.userId)
        assertEquals("John Doe", post.userDisplayName)
        assertEquals("https://example.com/user.jpg", post.userPhotoUrl)
        assertEquals("Buddy", post.petName)
        assertEquals("Golden Retriever", post.petBreed)
        assertEquals(3, post.petAge)
        assertEquals("Male", post.petGender)
        assertEquals("Friendly dog looking for a companion", post.petDescription)
        assertEquals("https://example.com/pet.jpg", post.petImageUrl)
        assertEquals("New York, NY", post.location)
        assertEquals("john@example.com", post.contactInfo)
        assertEquals(1234567890L, post.createdAt)
        assertTrue(post.isActive)
    }
    
    @Test
    fun `PetPost should be copyable`() {
        val originalPost = PetPost(
            id = "post123",
            petName = "Buddy",
            petBreed = "Golden Retriever",
            petAge = 3,
            petGender = "Male"
        )
        
        val copiedPost = originalPost.copy(
            petAge = 4,
            petDescription = "Updated description"
        )
        
        assertEquals(originalPost.id, copiedPost.id)
        assertEquals(originalPost.petName, copiedPost.petName)
        assertEquals(originalPost.petBreed, copiedPost.petBreed)
        assertEquals(originalPost.petGender, copiedPost.petGender)
        assertEquals(4, copiedPost.petAge)
        assertEquals("Updated description", copiedPost.petDescription)
    }
    
    @Test
    fun `PetPost should handle inactive posts`() {
        val post = PetPost(
            id = "post123",
            isActive = false
        )
        
        assertTrue(!post.isActive)
    }
}




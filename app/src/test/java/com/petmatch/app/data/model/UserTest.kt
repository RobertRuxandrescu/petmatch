package com.petmatch.app.data.model

import org.junit.Test
import kotlin.test.assertEquals

class UserTest {
    
    @Test
    fun `User should have correct default values`() {
        val user = User()
        
        assertEquals("", user.id)
        assertEquals("", user.email)
        assertEquals("", user.displayName)
        assertEquals("", user.photoUrl)
        assert(user.createdAt > 0)
    }
    
    @Test
    fun `User should be created with provided values`() {
        val userId = "user123"
        val email = "test@example.com"
        val displayName = "Test User"
        val photoUrl = "https://example.com/photo.jpg"
        val createdAt = 1234567890L
        
        val user = User(
            id = userId,
            email = email,
            displayName = displayName,
            photoUrl = photoUrl,
            createdAt = createdAt
        )
        
        assertEquals(userId, user.id)
        assertEquals(email, user.email)
        assertEquals(displayName, user.displayName)
        assertEquals(photoUrl, user.photoUrl)
        assertEquals(createdAt, user.createdAt)
    }
    
    @Test
    fun `User should be copyable`() {
        val originalUser = User(
            id = "user123",
            email = "test@example.com",
            displayName = "Test User",
            photoUrl = "https://example.com/photo.jpg"
        )
        
        val copiedUser = originalUser.copy(
            displayName = "Updated User",
            email = "updated@example.com"
        )
        
        assertEquals(originalUser.id, copiedUser.id)
        assertEquals(originalUser.photoUrl, copiedUser.photoUrl)
        assertEquals("Updated User", copiedUser.displayName)
        assertEquals("updated@example.com", copiedUser.email)
    }
}




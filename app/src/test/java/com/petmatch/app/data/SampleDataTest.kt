package com.petmatch.app.data

import com.petmatch.app.data.model.PetPost
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SampleDataTest {
    
    @Test
    fun `getSamplePetPosts should return 5 posts`() {
        val posts = SampleData.getSamplePetPosts()
        
        assertEquals(5, posts.size)
    }
    
    @Test
    fun `sample posts should have valid data`() {
        val posts = SampleData.getSamplePetPosts()
        
        posts.forEach { post ->
            assertFalse(post.id.isEmpty())
            assertFalse(post.petName.isEmpty())
            assertFalse(post.petBreed.isEmpty())
            assertTrue(post.petAge > 0)
            assertFalse(post.petGender.isEmpty())
            assertFalse(post.petDescription.isEmpty())
            assertFalse(post.location.isEmpty())
            assertFalse(post.userDisplayName.isEmpty())
            assertTrue(post.isActive)
        }
    }
    
    @Test
    fun `first post should be Buddy`() {
        val posts = SampleData.getSamplePetPosts()
        val firstPost = posts.first()
        
        assertEquals("1", firstPost.id)
        assertEquals("Buddy", firstPost.petName)
        assertEquals("Golden Retriever", firstPost.petBreed)
        assertEquals(3, firstPost.petAge)
        assertEquals("Male", firstPost.petGender)
        assertEquals("Sarah Johnson", firstPost.userDisplayName)
        assertEquals("New York, NY", firstPost.location)
    }
    
    @Test
    fun `second post should be Luna`() {
        val posts = SampleData.getSamplePetPosts()
        val secondPost = posts[1]
        
        assertEquals("2", secondPost.id)
        assertEquals("Luna", secondPost.petName)
        assertEquals("Husky", secondPost.petBreed)
        assertEquals(2, secondPost.petAge)
        assertEquals("Female", secondPost.petGender)
        assertEquals("Mike Chen", secondPost.userDisplayName)
        assertEquals("Los Angeles, CA", secondPost.location)
    }
    
    @Test
    fun `all posts should have unique IDs`() {
        val posts = SampleData.getSamplePetPosts()
        val ids = posts.map { it.id }
        
        assertEquals(ids.size, ids.distinct().size)
    }
    
    @Test
    fun `all posts should have different pet names`() {
        val posts = SampleData.getSamplePetPosts()
        val petNames = posts.map { it.petName }
        
        assertEquals(petNames.size, petNames.distinct().size)
    }
    
    @Test
    fun `posts should have realistic age ranges`() {
        val posts = SampleData.getSamplePetPosts()
        
        posts.forEach { post ->
            assertTrue(post.petAge in 1..10, "Pet age should be between 1 and 10, but was ${post.petAge}")
        }
    }
    
    @Test
    fun `posts should have valid gender values`() {
        val posts = SampleData.getSamplePetPosts()
        
        posts.forEach { post ->
            assertTrue(post.petGender in listOf("Male", "Female"), "Pet gender should be Male or Female, but was ${post.petGender}")
        }
    }
}




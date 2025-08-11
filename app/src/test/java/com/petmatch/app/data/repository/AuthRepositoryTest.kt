package com.petmatch.app.data.repository

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.petmatch.app.data.model.User
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class AuthRepositoryTest {
    
    private lateinit var repository: AuthRepository
    private lateinit var mockAuth: FirebaseAuth
    private lateinit var mockFirestore: FirebaseFirestore
    private val testDispatcher = StandardTestDispatcher()
    
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        mockAuth = mockk()
        mockFirestore = mockk()
        repository = AuthRepository(mockAuth, mockFirestore)
    }
    
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    
    @Test
    fun `isUserLoggedIn should return false`() = runTest {
        val result = repository.isUserLoggedIn()
        
        assertFalse(result)
    }
    
    @Test
    fun `getCurrentUser should return null`() = runTest {
        val result = repository.getCurrentUser()
        
        assertNull(result)
    }
    
    @Test
    fun `signInWithGoogle should create user from account`() = runTest {
        val mockAccount = mockk<GoogleSignInAccount> {
            every { id } returns "google123"
            every { email } returns "test@example.com"
            every { displayName } returns "Test User"
            every { photoUrl } returns null
        }
        
        val result = repository.signInWithGoogle(mockAccount)
        
        assertTrue(result.isSuccess)
        val user = result.getOrNull()
        assertEquals("google123", user?.id)
        assertEquals("test@example.com", user?.email)
        assertEquals("Test User", user?.displayName)
        assertEquals("", user?.photoUrl)
    }
    
    @Test
    fun `signInWithGoogle should handle null account properties`() = runTest {
        val mockAccount = mockk<GoogleSignInAccount> {
            every { id } returns null
            every { email } returns null
            every { displayName } returns null
            every { photoUrl } returns null
        }
        
        val result = repository.signInWithGoogle(mockAccount)
        
        assertTrue(result.isSuccess)
        val user = result.getOrNull()
        assertEquals("mock_user_id", user?.id)
        assertEquals("", user?.email)
        assertEquals("User", user?.displayName)
        assertEquals("", user?.photoUrl)
    }
    
    @Test
    fun `signInWithGoogle should handle exceptions`() = runTest {
        val mockAccount = mockk<GoogleSignInAccount> {
            every { id } throws RuntimeException("Test exception")
        }
        
        val result = repository.signInWithGoogle(mockAccount)
        
        assertTrue(result.isFailure)
        assertEquals("Test exception", result.exceptionOrNull()?.message)
    }
    
    @Test
    fun `signOut should not throw exception`() = runTest {
        // This should not throw any exception
        repository.signOut()
    }
}




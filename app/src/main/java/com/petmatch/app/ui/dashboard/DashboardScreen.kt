package com.petmatch.app.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.petmatch.app.data.SampleData
import com.petmatch.app.ui.theme.PetMatchTheme
import androidx.compose.runtime.LaunchedEffect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel,
    onNavigateToPostDetail: (com.petmatch.app.data.model.PetPost) -> Unit = {},
    onNavigateToAddPost: () -> Unit = {},
    onShowToast: (String) -> Unit = {}
) {
    val state by viewModel.state.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(state.isLoading)
    
    // Handle navigation
    LaunchedEffect(state.shouldNavigateToPostDetail) {
        if (state.shouldNavigateToPostDetail && state.selectedPost != null) {
            onNavigateToPostDetail(state.selectedPost!!)
            // Reset the flag
            viewModel.onIntent(DashboardIntent.ErrorDismissed)
        }
    }
    
    LaunchedEffect(state.shouldNavigateToAddPost) {
        if (state.shouldNavigateToAddPost) {
            onNavigateToAddPost()
            // Reset the flag
            viewModel.onIntent(DashboardIntent.ErrorDismissed)
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "PetMatch",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onIntent(DashboardIntent.AddPostClicked) },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Post",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) { paddingValues ->
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { viewModel.onIntent(DashboardIntent.RefreshPosts) }
        ) {
            when {
                state.isLoading && state.posts.isEmpty() -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                
                state.isEmpty -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "No posts yet",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Be the first to create a post!",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                
                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(state.posts) { post ->
                            PetPostCard(
                                post = post,
                                onClick = { viewModel.onIntent(DashboardIntent.PostClicked(post)) }
                            )
                        }
                    }
                }
            }
        }
        
        // Error message
        state.error?.let { error ->
            // TODO: Show error message (could be a snackbar or toast)
        }
    }
}

@Preview(showBackground = true, name = "Empty State")
@Composable
fun DashboardScreenEmptyPreview() {
    PetMatchTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            // Create a mock ViewModel with empty state for preview
            val mockViewModel = DashboardViewModel(
                petPostRepository = com.petmatch.app.data.repository.PetPostRepository(
                    firestore = com.google.firebase.firestore.FirebaseFirestore.getInstance()
                )
            )
            DashboardScreen(viewModel = mockViewModel)
        }
    }
}

@Preview(showBackground = true, name = "With Posts")
@Composable
fun DashboardScreenWithPostsPreview() {
    PetMatchTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            // Create a mock ViewModel with sample data for preview
            val mockViewModel = DashboardViewModel(
                petPostRepository = com.petmatch.app.data.repository.PetPostRepository(
                    firestore = com.google.firebase.firestore.FirebaseFirestore.getInstance()
                )
            )
            DashboardScreen(viewModel = mockViewModel)
        }
    }
}

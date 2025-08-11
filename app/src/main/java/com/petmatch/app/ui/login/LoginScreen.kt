package com.petmatch.app.ui.login

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.petmatch.app.R
import com.petmatch.app.ui.theme.PetMatchTheme
import com.petmatch.app.ui.utils.ToastUtil

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onNavigateToDashboard: () -> Unit = {},
    onLaunchGoogleSignIn: () -> Unit = {},
    onShowToast: (String) -> Unit = {}
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    
    // Google Sign-In setup
    val gso = remember {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    }
    
    val googleSignInClient = remember {
        GoogleSignIn.getClient(context, gso)
    }
    
    // Google Sign-In launcher
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == android.app.Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                viewModel.handleGoogleSignInResult(account)
            } catch (e: ApiException) {
                viewModel.onIntent(LoginIntent.ErrorDismissed)
                ToastUtil.showToast(context, "Google Sign-In failed: ${e.message}")
            }
        }
    }
    
    // Handle navigation and side effects
    LaunchedEffect(state.shouldNavigateToDashboard) {
        if (state.shouldNavigateToDashboard) {
            onNavigateToDashboard()
        }
    }
    
    LaunchedEffect(state.shouldLaunchGoogleSignIn) {
        if (state.shouldLaunchGoogleSignIn) {
            launcher.launch(googleSignInClient.signInIntent)
            // Reset the flag
            viewModel.onIntent(LoginIntent.ErrorDismissed)
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.ic_pet_logo),
            contentDescription = "PetMatch Logo",
            modifier = Modifier.size(120.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Title
        Text(
            text = "Welcome to PetMatch",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Subtitle
        Text(
            text = "Find the perfect match for your pet",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(48.dp))
        
        // Google Sign-In Button
        Button(
            onClick = { viewModel.onIntent(LoginIntent.GoogleSignInClicked) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(28.dp)),
            enabled = !state.isLoading
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text(
                    text = "Sign in with Google",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        
        // Error message
        state.error?.let { error ->
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    PetMatchTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            // Create a mock ViewModel for preview
            val mockViewModel = LoginViewModel(
                authRepository = com.petmatch.app.data.repository.AuthRepository(
                    auth = com.google.firebase.auth.FirebaseAuth.getInstance(),
                    firestore = com.google.firebase.firestore.FirebaseFirestore.getInstance()
                )
            )
            LoginScreen(viewModel = mockViewModel)
        }
    }
}

@Preview(showBackground = true, name = "Loading State")
@Composable
fun LoginScreenLoadingPreview() {
    PetMatchTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            // Create a mock ViewModel with loading state for preview
            val mockViewModel = LoginViewModel(
                authRepository = com.petmatch.app.data.repository.AuthRepository(
                    auth = com.google.firebase.auth.FirebaseAuth.getInstance(),
                    firestore = com.google.firebase.firestore.FirebaseFirestore.getInstance()
                )
            )
            LoginScreen(viewModel = mockViewModel)
        }
    }
}

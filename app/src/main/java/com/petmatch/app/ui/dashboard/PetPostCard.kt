package com.petmatch.app.ui.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.petmatch.app.data.model.PetPost
import com.petmatch.app.ui.theme.PetMatchTheme

@Composable
fun PetPostCard(
    post: PetPost,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Pet Image
            AsyncImage(
                model = post.petImageUrl.ifEmpty { "https://via.placeholder.com/80x80" },
                contentDescription = "Pet Image",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Post Details
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = post.petName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = "${post.petBreed} • ${post.petAge} years old • ${post.petGender}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = post.petDescription,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Posted by ${post.userDisplayName}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                    
                    Spacer(modifier = Modifier.weight(1f))
                    
                    Text(
                        text = post.location,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PetPostCardPreview() {
    PetMatchTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            PetPostCard(
                post = PetPost(
                    id = "1",
                    userId = "user1",
                    userDisplayName = "John Doe",
                    userPhotoUrl = "",
                    petName = "Buddy",
                    petBreed = "Golden Retriever",
                    petAge = 3,
                    petGender = "Male",
                    petDescription = "Friendly and energetic Golden Retriever looking for a mate. Loves playing fetch and going for walks.",
                    petImageUrl = "",
                    location = "New York, NY",
                    contactInfo = "john@example.com",
                    createdAt = System.currentTimeMillis(),
                    isActive = true
                )
            )
        }
    }
}

@Preview(showBackground = true, name = "Long Description")
@Composable
fun PetPostCardLongDescriptionPreview() {
    PetMatchTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            PetPostCard(
                post = PetPost(
                    id = "2",
                    userId = "user2",
                    userDisplayName = "Jane Smith",
                    userPhotoUrl = "",
                    petName = "Luna",
                    petBreed = "Siberian Husky",
                    petAge = 2,
                    petGender = "Female",
                    petDescription = "Beautiful Siberian Husky with striking blue eyes. Very playful and loves snow. Looking for a compatible mate for breeding. She has excellent pedigree and health records.",
                    petImageUrl = "",
                    location = "Seattle, WA",
                    contactInfo = "jane@example.com",
                    createdAt = System.currentTimeMillis(),
                    isActive = true
                )
            )
        }
    }
}



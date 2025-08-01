package com.silva021.minhajornada.ui.screens.communities

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.silva021.minhajornada.ui.theme.Palette
import com.silva021.minhajornada.ui.theme.Palette.inputBackground
import com.silva021.minhajornada.ui.theme.Palette.primaryColor
import com.silva021.minhajornada.ui.theme.Palette.textPrimary
import com.silva021.minhajornada.ui.theme.Palette.textSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunitiesScreen() {
    val searchText = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Palette.backgroundColor)
    ) {
        Text(
            text = "Comunidades",
            color = textPrimary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        )

        // Barra de pesquisa
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(50))
                    .background(inputBackground)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Pesquisar",
                        tint = textSecondary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    TextField(
                        value = searchText.value,
                        onValueChange = { searchText.value = it },
                        placeholder = { Text("Pesquisar comunidades") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors().copy(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = primaryColor,
                            focusedTextColor = textPrimary,
                            unfocusedTextColor = textPrimary,
                        )
                    )
                }
            }
        }

        Text(
            text = "Em destaque",
            color = textPrimary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FeaturedCommunityCard(
                imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDG8U7Jgh29H6fv_GzSiE4iDYIGl5VlQae2Izv6y5Blp0DnD6BbcnsQQkBqMJWzZ92QGideUZxYCttOogvRLnGLuS1YQWLxz8VqWCDyPpKAnAeeVPjY97u8s8QyVe2X9HYfGmecxL0tEe9_3D1ULwVckACP7dzopBqwx6n8ifYfLZAL553x3aJPl1XMeeW3Zw-3UlpPxSTQCTpWq1zDhMA9vrwcgBflLecL1g6n5dXYRQg52DjG8_KQYdVTi40RI6DzLIpbY3YXYqA",
                name = "Fitness Fanáticos",
                members = "1.2k membros"
            )

            FeaturedCommunityCard(
                imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDA4p3n7YAU0tuTTdUX1LXvST81S587HIZyGmvHVPennNBDBO44rX3GtNANF2o61oLDAyXlHfi6JeyLmtbEIvWxXS6CtSaEZtwXSnhdXgcwAsOnV6YjnW4u233WljWwK9R0nEhMKHv_bUknrvg-P8_jGN_uuStHzRRCKGQWvRZjiPew6af3P2SDzvcxQ2D80WZ1es7HpJZo-0dlAEI0uvSH0HiAEtEo7tkGl3rBhrOP1VjT9XpWAYRWl0Qp48KpncrzMxSShunC0iw",
                name = "Viciados em Leitura",
                members = "876 membros"
            )

            FeaturedCommunityCard(
                imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuABe51cmxUVX373ihvdi_ODisHFTOZxFeUhz9ecuDTGl8QApQy4oR7Y6p3Gey8BT5rBbLDDZOErz5Rtkh986BKch1E8_2JTug-b6beonHXeRlQgQWYSiooiNjYTxSxwwUrDEwxIhJymskaPM5F9kQDyxtwvcG6Wsa6rittQ5ydRq7EHN13fJVS_BhQ6FqRgutL3-vMtpacq05YMUUNGd4aSpLCQUQOnfaeLOQdLMAIIYUDcfa4SzF2CfT7gpCYKvjaCJ1ri9_tn4is",
                name = "Criadores Culinários",
                members = "2.5k membros"
            )
        }

        Text(
            text = "Categorias",
            color = textPrimary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CategoryCard(
                    icon = Icons.Default.Person,
                    name = "Fitness"
                )
                CategoryCard(
                    icon = Icons.Default.Person,
                    name = "Leitura"
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CategoryCard(
                    icon = Icons.Default.Person,
                    name = "Culinária"
                )
                CategoryCard(
                    icon = Icons.Default.Person,
                    name = "Natureza"
                )
            }
        }

        Spacer(modifier = Modifier.height(80.dp)) // Espaço para a barra inferior
    }
}

@Composable
fun FeaturedCommunityCard(imageUrl: String, name: String, members: String) {
    Column(
        modifier = Modifier.width(160.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = imageUrl),
            contentDescription = "Imagem da comunidade $name",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(4f / 3f)
                .clip(RoundedCornerShape(16.dp))
        )
        Text(
            text = name,
            color = textPrimary,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = members,
            color = textSecondary,
            fontSize = 14.sp
        )
    }
}

@Composable
fun CategoryCard(icon: ImageVector, name: String) {
    Row(
        modifier = Modifier
//            .weight(1f)
            .clip(RoundedCornerShape(12.dp))
            .background(Palette.cardBackground)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = name,
            tint = primaryColor,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = name,
            color = textPrimary,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview
@Composable
fun CommunitiesScreenPreview() {
    MaterialTheme {
        CommunitiesScreen()
    }
}


package com.silva021.minhajornada.ui.screens.explorer.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.silva021.minhajornada.domain.model.CategoryType
import com.silva021.minhajornada.domain.model.PublicChallenge
import com.silva021.minhajornada.ui.DatabaseFake
import com.silva021.minhajornada.ui.components.CategoriesFilter
import com.silva021.minhajornada.ui.components.Search
import com.silva021.minhajornada.ui.theme.Palette.backgroundColor
import com.silva021.minhajornada.ui.theme.Palette.cardBackground
import com.silva021.minhajornada.ui.theme.Palette.textPrimary
import com.silva021.minhajornada.ui.theme.Palette.textSecondary

@Composable
fun ExplorerScreen(
    onChallengeClick: (PublicChallenge) -> Unit,
) {
    var searchText by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(CategoryType.FITNESS) }

    LazyColumn(
        modifier = Modifier
            .background(backgroundColor)
    ) {
        item {
            Search(
                text = searchText,
                onTextChange = { searchText = it },
                placeholder = "Pesquisar desafios"
            )

            CategoriesFilter(
                selectedCategory
            ) {
                selectedCategory = it
            }


            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Destaques",
                    color = textPrimary,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(DatabaseFake.featuredChallenges) {
                        FeaturedChallengeCard(it, onChallengeClick)
                    }
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Populares",
                    color = textPrimary,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                DatabaseFake.popularChallenges.chunked(2).forEach { rowItems ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        rowItems.forEach { challenge ->
                            Box(modifier = Modifier.weight(1f)) {
                                PopularChallengeCard(challenge, onChallengeClick)
                            }
                        }

                        if (rowItems.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}


@Composable
private fun FeaturedChallengeCard(
    challenge: PublicChallenge,
    onChallengeClick: (PublicChallenge) -> Unit
) {
    Card(
        modifier = Modifier
            .width(280.dp)
            .height(260.dp),
        colors = CardDefaults.cardColors().copy(containerColor = cardBackground),
        onClick = {
            onChallengeClick.invoke(challenge)
        }
    )
    {
        Column {
            AsyncImage(
                model = challenge.imageUrl,
                contentDescription = challenge.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = challenge.title,
                    color = textPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = challenge.description,
                    color = textSecondary,
                    fontSize = 14.sp
                )
            }
        }
    }
}


@Composable
private fun PopularChallengeCard(
    challenge: PublicChallenge,
    onChallengeClick: (PublicChallenge) -> Unit,
) {
    Column {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = cardBackground),
            onClick = {
                onChallengeClick.invoke(challenge)
            }
        ) {
            AsyncImage(
                model = challenge.imageUrl,
                contentDescription = challenge.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
        }

        Column(modifier = Modifier.padding(top = 8.dp)) {
            Text(
                text = challenge.title,
                color = textPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = challenge.description,
                color = textSecondary,
                fontSize = 14.sp
            )
        }
    }
}


@Preview
@Composable
fun ExplorerScreenPreview() {
    ExplorerScreen(
        onChallengeClick = {}
    )
}
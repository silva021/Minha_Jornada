package com.silva021.minhajornada.ui.screens.communities.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import coil3.compose.rememberAsyncImagePainter
import com.silva021.minhajornada.domain.model.CategoryType
import com.silva021.minhajornada.domain.model.Communities
import com.silva021.minhajornada.domain.model.Community
import com.silva021.minhajornada.domain.model.toDomain
import com.silva021.minhajornada.ui.DatabaseFake.communities
import com.silva021.minhajornada.ui.components.CategoriesFilter
import com.silva021.minhajornada.ui.components.Search
import com.silva021.minhajornada.ui.theme.Palette.backgroundColor
import com.silva021.minhajornada.ui.theme.Palette.textPrimary
import com.silva021.minhajornada.ui.theme.Palette.textSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunitiesContent(
    communities: Communities,
    selectedCategory: CategoryType,
    onCategoryClick: (CategoryType) -> Unit,

    onMineCommunityClick: (Community) -> Unit,
    onCommunityClick: (Community) -> Unit,
) {
    var searchText by remember { mutableStateOf("") }
    val communitiesFeatured = communities.discover.take(3)
    val communitiesPopular = communities.discover.drop(3)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        Search(
            text = searchText,
            onTextChange = { searchText = it },
            placeholder = "Pesquisar comunidades"
        )

        CategoriesFilter(
            Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            selectedCategory = selectedCategory
        ) {
            onCategoryClick(it)
        }

        if (communities.my.isEmpty() && communities.discover.isEmpty()) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Nenhuma comunidade encontrada",
                    color = textSecondary,
                    fontSize = 16.sp
                )
            }
        } else {
            LazyColumn {
                item {
                    if (communities.my.isNotEmpty()) {
                        Column(
                            Modifier.padding(horizontal = 16.dp),
                        ) {
                            Text(
                                text = "Minhas comunidades",
                                color = textPrimary,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )

                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                items(communities.my) { community ->
                                    FeaturedCommunityCard(
                                        community = community,
                                        onCommunityClick = onMineCommunityClick
                                    )
                                }
                            }
                        }
                    }

                    if (communitiesFeatured.isNotEmpty()) {
                        Column(
                            Modifier.padding(horizontal = 16.dp),
                        ) {
                            Text(
                                text = "Em destaque",
                                color = textPrimary,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )

                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                items(communitiesFeatured) { community ->
                                    FeaturedCommunityCard(
                                        community = community,
                                        onCommunityClick = onCommunityClick
                                    )
                                }
                            }
                        }
                    }

                    if (communitiesPopular.isNotEmpty()) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "Todos",
                                color = textPrimary,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )

                            communitiesPopular.chunked(2).forEach { rowItems ->
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    rowItems.forEach { community ->
                                        Box(modifier = Modifier.weight(1f)) {
                                            FeaturedCommunityCard(
                                                community = community,
                                                onCommunityClick = onCommunityClick
                                            )
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
        }
    }
}

@Composable
fun FeaturedCommunityCard(
    community: Community,
    onCommunityClick: (Community) -> Unit,
) {
    Column(
        modifier = Modifier
            .width(160.dp)
            .clickable {
                onCommunityClick.invoke(community)
            },
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = community.imageUrl),
            contentDescription = "Imagem da comunidade $community.name",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(4f / 3f)
                .clip(RoundedCornerShape(16.dp))
        )
        Text(
            text = community.name,
            color = textPrimary,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = "${community.membersCount} membros",
            color = textSecondary,
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
fun CommunitiesContentPreview() {
    MaterialTheme {
        CommunitiesContent(
            communities = communities.toDomain(),
            onCommunityClick = {},
            onCategoryClick = {},
            selectedCategory = CategoryType.FITNESS,
            onMineCommunityClick = {}
        )
    }
}

@Preview
@Composable
fun CommunitiesEmptyContentPreview() {
    MaterialTheme {
        CommunitiesContent(
            communities = Communities(
                discover = emptyList(),
                my = emptyList()
            ),
            selectedCategory = CategoryType.FITNESS,
            onCategoryClick = {},
            onCommunityClick = {},
            onMineCommunityClick = {}
        )
    }
}


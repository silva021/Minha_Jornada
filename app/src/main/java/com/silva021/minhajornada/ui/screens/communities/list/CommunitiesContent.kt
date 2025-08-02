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
import com.silva021.minhajornada.ui.components.CategoriesFilter
import com.silva021.minhajornada.ui.components.Search
import com.silva021.minhajornada.ui.theme.Palette.backgroundColor
import com.silva021.minhajornada.ui.theme.Palette.textPrimary
import com.silva021.minhajornada.ui.theme.Palette.textSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunitiesContent(
    communities: Communities,
    onMineCommunityClick: (Community) -> Unit,
    onCommunityClick: (Community) -> Unit,
) {
    var searchText by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(CategoryType.FITNESS) }

    val communitiesFeatured = communities.featured.subList(0, 3)
    val communitiesPopular = communities.featured.subList(3, communities.featured.size)

    Column(
        modifier = Modifier.background(backgroundColor)
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        Search(
            text = searchText,
            onTextChange = { searchText = it },
            placeholder = "Pesquisar comunidades"
        )

        CategoriesFilter(
            selectedCategory
        ) {
            selectedCategory = it
        }

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
            text = community.members,
            color = textSecondary,
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
fun CommunitiesContentPreview() {
    MaterialTheme {
        val communities = Communities(
            my = listOf(),
            featured = listOf(
                Community(
                    id = "1",
                    imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDG8U7Jgh29H6fv_GzSiE4iDYIGl5VlQae2Izv6y5Blp0DnD6BbcnsQQkBqMJWzZ92QGideUZxYCttOogvRLnGLuS1YQWLxz8VqWCDyPpKAnAeeVPjY97u8s8QyVe2X9HYfGmecxL0tEe9_3D1ULwVckACP7dzopBqwx6n8ifYfLZAL553x3aJPl1XMeeW3Zw-3UlpPxSTQCTpWq1zDhMA9vrwcgBflLecL1g6n5dXYRQg52DjG8_KQYdVTi40RI6DzLIpbY3YXYqA",
                    name = "Fitness Fanáticos",
                    members = "1200 membros"
                ),
                Community(
                    id = "2",
                    imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDA4p3n7YAU0tuTTdUX1LXvST81S587HIZyGmvHVPennNBDBO44rX3GtNANF2o61oLDAyXlHfi6JeyLmtbEIvWxXS6CtSaEZtwXSnhdXgcwAsOnV6YjnW4u233WljWwK9R0nEhMKHv_bUknrvg-P8_jGN_uuStHzRRCKGQWvRZjiPew6af3P2SDzvcxQ2D80WZ1es7HpJZo-0dlAEI0uvSH0HiAEtEo7tkGl3rBhrOP1VjT9XpWAYRWl0Qp48KpncrzMxSShunC0iw",
                    name = "Viciados em Leitura", members = "876 membros"
                ),
                Community(
                    id = "3",
                    imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuABe51cmxUVX373ihvdi_ODisHFTOZxFeUhz9ecuDTGl8QApQy4oR7Y6p3Gey8BT5rBbLDDZOErz5Rtkh986BKch1E8_2JTug-b6beonHXeRlQgQWYSiooiNjYTxSxwwUrDEwxIhJymskaPM5F9kQDyxtwvcG6Wsa6rittQ5ydRq7EHN13fJVS_BhQ6FqRgutL3-vMtpacq05YMUUNGd4aSpLCQUQOnfaeLOQdLMAIIYUDcfa4SzF2CfT7gpCYKvjaCJ1ri9_tn4is",
                    name = "Criadores Culinários",
                    members = "2750 membros"
                ),
                Community(
                    id = "4",
                    imageUrl = "https://assets-sitesdigitais.dasa.com.br/strapi/corpo-em-m_2ab2e62957/corpo-em-m_2ab2e62957.png",
                    name = "Corpo em Movimento",
                    members = "1890 membros"
                ),
                Community(
                    id = "5",
                    imageUrl = "https://www.decoracao.com/wp-content/uploads/2012/10/cozinha-via-lamaisondannag.jpg",
                    name = "Cozinha de Verdade",
                    members = "2375 membros"
                ),
                Community(
                    id = "6",
                    imageUrl = "https://maisexpressao.com.br/imagens/noticias/72189/640x480/whatsapp-image-2023-08-02-at-10.jpeg",
                    name = "Natureza e Bem - estar",
                    members = "1622 membros"
                ),
                Community(
                    id = "7",
                    imageUrl = "https://artritereumatoide.blog.br/wp-content/uploads/2019/11/quer-adotar-uma-culinaria-saudavel-entao-mude-ja-esses-5-habitos31199.jpg",
                    name = "Culinária Saudável",
                    members = "2844 membros"
                )
            )
        )

        CommunitiesContent(
            communities = communities,
            onCommunityClick = {},
            onMineCommunityClick = {}
        )
    }
}


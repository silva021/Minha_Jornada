package com.silva021.minhajornada.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.NaturePeople
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

enum class CategoryType(
    val label: String,
    val icon: ImageVector,
    val color: Color,
    val imageUrl: String
) {
    ALL(
        label = "Todos",
        icon = Icons.AutoMirrored.Filled.MenuBook,
        color = Color(0xFF9E9E9E),
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuD9vgHgUNi5FW7XeXvA3UfQSw6KruG9cR32qkfmF2lPPxLCAI7ORwDuz1FQz1HVRkEnKMJGWwSca6K1N-I6rM9kBXXQT52cI5ysoNQgNmXujf2-ee3ecQII-du00VnVJxouo0FtJdhvRioTBDcpzx0aQ_jhGD2gsG9NulxBsR5Q9F2tYLtqP9mhwU8hIwQ6VuwVPMwm76rg0pxyPZfun1xRnPVvQ-rayxZJOBctgI4QWk0E8WI732pUqrvpCz24myS0QDkbRgqLjhI"
    ),
    FITNESS(
        label = "Fitness",
        icon = Icons.Default.FitnessCenter,
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBCrW-P-hS7qsucbk5keYoRD86ptQ0tHKVgvq1t3nz-WpPNDCYjYOA0PbpJTrRtgYRnFTc2GsbEMu2jFxRy_6bK9nL1CVkMLILXJlbUe2EplDvc6ZkPjIXvyMw-G6XEdzGVh509Qv3_68rBKSghbRWq_HMbkfxq-h8b4eOzRhiSfudYwIhZw78zVIX-BVZ1k7eWff7M0rs8CbBFI_av2o_aet_08IQOEU0siD3uj4vySufJnKxDCe1zQrlsl0ZlSUhvH_kzCjed7WQ",
        color = Color(0xFF4CAF50)
    ),
    READING(
        label = "Leitura",
        icon = Icons.AutoMirrored.Filled.MenuBook,
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuASsQk7dbUMj7ibrRtf12nogi2hU9h7VEUk49RzlD9KFgkwGVrsdnJYZbo0B5CdkzZI8F_apzhCeW2niZGJ7y_bUfJoaiI31zYy-8UO4O0c5u-NpTr9ucBRtghAbsjNLguwWiQiVE3Qh6BsgPeuFHjx0uCjPrQrinTN-bf2vHREo4nOIzDca431FJL5dXDvMirfyba_sOxKOymckFcByu468xOxxJ-HN8AEO_mTMOKQ6WI0bzoRRihNwHlbmgT5v8PCRzBwYNQ_xBU",
        color = Color(0xFF3F51B5)
    ),
    CULINARY(
        label = "Culinária",
        icon = Icons.Default.Restaurant,
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuD9vgHgUNi5FW7XeXvA3UfQSw6KruG9cR32qkfmF2lPPxLCAI7ORwDuz1FQz1HVRkEnKMJGWwSca6K1N-I6rM9kBXXQT52cI5ysoNQgNmXujf2-ee3ecQII-du00VnVJxouo0FtJdhvRioTBDcpzx0aQ_jhGD2gsG9NulxBsR5Q9F2tYLtqP9mhwU8hIwQ6VuwVPMwm76rg0pxyPZfun1xRnPVvQ-rayxZJOBctgI4QWk0E8WI732pUqrvpCz24myS0QDkbRgqLjhI",
        color = Color(0xFFFF7043)
    ),
    NATURE(
        label = "Natureza",
        icon = Icons.Default.NaturePeople,
        color = Color(0xFF66BB6A),
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCp9degKs731yeuU7AIizup4AYO7fTtAJgiurX93Bs7bqS7Ie6UiiWBJOAKjVVJlodlQpebQRBJNn-Ylw3kgkv0YTtfVj6pZDS3LHNUM0ULict7jPVScCfHJNBwvaG3B7BY1uvshkpTfR6Zilae5gA_3ExIubr00mpj9_4gnikSTwuYpmRrcdd62Zp9ryukD34PjY_j2E7z5mAlmDjfwfYyjv9N4Kwko6rwLLSFwWcoNVlbOx-3oH0veR6XgOkvN3XdG6yjZEvCFSI",
    ),
    MINDSET(
        label = "Mindset",
        icon = Icons.Default.SelfImprovement,
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuAijpAG_9bomitVexpHbS744QD1oWcNwuFSbOtECApwlp_7HrZaXaSG4pJXK5KtwutElO666SuoSCkfrFJts7iD3nux31-283YswxtwOIevcaEi08K8tsH5Tr6uR1yIYY7q_9ySHcDIZp7TjE7IuLOp-JuakzYcrfl0smmc-fvYg4dFnXCx0jm1R9RcJTBq8M7NNCTIM544XHnWdxFbP08K6Cn4pxb_jFGBiYIq0-DmWUzzR7e-zQ0xx5VpWpjci8cDgDiUVGIJS94",
        color = Color(0xFFAB47BC)
    ),
    EDUCATION(
        label = "Educação",
        icon = Icons.Default.School,
        color = Color(0xFF29B6F6),
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuD9vgHgUNi5FW7XeXvA3UfQSw6KruG9cR32qkfmF2lPPxLCAI7ORwDuz1FQz1HVRkEnKMJGWwSca6K1N-I6rM9kBXXQT52cI5ysoNQgNmXujf2-ee3ecQII-du00VnVJxouo0FtJdhvRioTBDcpzx0aQ_jhGD2gsG9NulxBsR5Q9F2tYLtqP9mhwU8hIwQ6VuwVPMwm76rg0pxyPZfun1xRnPVvQ-rayxZJOBctgI4QWk0E8WI732pUqrvpCz24myS0QDkbRgqLjhI",

    )
}
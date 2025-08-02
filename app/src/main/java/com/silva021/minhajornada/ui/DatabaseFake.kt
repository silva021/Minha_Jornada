package com.silva021.minhajornada.ui

import com.silva021.minhajornada.domain.model.CategoryType
import com.silva021.minhajornada.domain.model.PublicChallenge

object DatabaseFake {

    val popularChallenges = listOf(
        PublicChallenge(
            id = "gratidao",
            title = "Gratidão Diária",
            description = "Cultive gratidão todos os dias",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuASsQk7dbUMj7ibrRtf12nogi2hU9h7VEUk49RzlD9KFgkwGVrsdnJYZbo0B5CdkzZI8F_apzhCeW2niZGJ7y_bUfJoaiI31zYy-8UO4O0c5u-NpTr9ucBRtghAbsjNLguwWiQiVE3Qh6BsgPeuFHjx0uCjPrQrinTN-bf2vHREo4nOIzDca431FJL5dXDvMirfyba_sOxKOymckFcByu468xOxxJ-HN8AEO_mTMOKQ6WI0bzoRRihNwHlbmgT5v8PCRzBwYNQ_xBU",
            creatorName = "Equipe Desafio+",
            participantsCount = 1340,
            category = CategoryType.MINDSET,
            isTrending = true
        ),
        PublicChallenge(
            id = "habilidade",
            title = "Aprenda uma Nova Habilidade",
            description = "Domine uma nova habilidade",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCu-akvszPRrab-2SAuWyfvyIUfgLWOH-8VC1Ndr6Diheee21Qz68BDK3mHkfLmBH7f5EDF3BxXHfra6ztGe4o3VPrfGhSDYp1aLrSbm16LQizsvqA7cz8jVrcq-9r6Jyvap8UUn56qQ_2Hke-zxMRDW0pJyzD4ggoL8wHz_rUZu0jkXC9MRhNfTsLIa9OHVNQnKKsSpj7nEwjX5NNjR1IiusU-kolZThnf8XyeA12WmNZ-wVdWkph-_wXjxvW5gqC2h8WtNycqX8c",
            creatorName = "Comunidade Skills+",
            participantsCount = 920,
            category = CategoryType.EDUCATION
        ),
        PublicChallenge(
            id = "detox",
            title = "Detox Digital",
            description = "Desconecte-se da tecnologia",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBs0XQlz9mAWVDi3n53PW4I5rxNLvOjuJJJd-K0Y8Ovj-b0x9TBOfVy7zf4V2tjTBlAqZGMtKVyM1m3HCriRpycTjcYPL3xG9QUQOaeEi0pZmou60Za5djoVgtAXaK4wCQ9vOScpK9h6VtaHVS4mNy0vTA0e2P0cLa9y_TQAvLBuK77yYy9Gq9QRhD7NQBNiAN3urtqbs5yLHnjtJ81_TC5Rkg37-Oe1fQioDWvQLtI_Iy-wfEyeLNbPddtaswwqMh-sm1fkSHXnYU",
            creatorName = "DigitalFree",
            participantsCount = 1105,
            category = CategoryType.MINDSET
        ),
        PublicChallenge(
            id = "1",
            title = "Alimentação Saudável",
            description = "Adote hábitos alimentares saudáveis",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuD9vgHgUNi5FW7XeXvA3UfQSw6KruG9cR32qkfmF2lPPxLCAI7ORwDuz1FQz1HVRkEnKMJGWwSca6K1N-I6rM9kBXXQT52cI5ysoNQgNmXujf2-ee3ecQII-du00VnVJxouo0FtJdhvRioTBDcpzx0aQ_jhGD2gsG9NulxBsR5Q9F2tYLtqP9mhwU8hIwQ6VuwVPMwm76rg0pxyPZfun1xRnPVvQ-rayxZJOBctgI4QWk0E8WI732pUqrvpCz24myS0QDkbRgqLjhI",
            creatorName = "NutriTeam",
            participantsCount = 1987,
            category = CategoryType.FITNESS
        )
    )

    val featuredChallenges = listOf(
        PublicChallenge(
            id = "fitness-7-dias",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBu1VDWNzIZGgVSP-NqdjIkQCX3gyvA-ICH9Xp7YsF8u-XWG9MWtfCjU6ddPsJzEv2iszLp_iJmVpl7vAginGvWGHfY13oYYIWn_YtzpL6QBTiaoYIwYGY12OSNSF9gokih6r0K21NviLwHaQ6kcyCzY1hiHYoUx4tSYUP3snqUpZBBsGr2WTjybGloX2axlldG70gR9Rv0iCp_ckD4GEMFaDN-2ycR29c5vOVHLIhO8-ysA3yENqGG_xjIFRUFm8SZL8Rny-AOnz0",
            title = "Desafio Fitness de 7 Dias",
            description = "Fique em forma em apenas uma semana",
            creatorName = "Lucas Silva",
            participantsCount = 278,
            category = CategoryType.FITNESS,
            isTrending = true
        ),
        PublicChallenge(
            id = "mindfulness-30-dias",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDC1CK0fJJekJFIALgZN8l9nrRFGObdyllNq8lf6DxJZ8IUsb5qYwMzKWtGtN-nXBCqfdu17MHgeoPkKp5xDDpbXjwvfCrF3ZMz_rRdJwFs9TM3rN6uZEK6KhM1tmwEurY1MDeTe6BI1Jr3DpOlyYbCjLOjYG-gJmCqMF4PV-sBfIfIGIjhjDjdWnUEMGZC8KbUyko8bUnt65-EGafdK6qHIQQDjN5_kmznxLSEaxS4EkFAEUU4ehsgB6-vQT2Jl6Zk8Er3SKk78eY",
            title = "Jornada de Mindfulness de 30 Dias",
            description = "Encontre paz interior em um mês",
            creatorName = "Ana Costa",
            participantsCount = 341,
            category = CategoryType.MINDSET,
            isTrending = true
        ),
        PublicChallenge(
            id = "produtividade-21-dias",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCp9degKs731yeuU7AIizup4AYO7fTtAJgiurX93Bs7bqS7Ie6UiiWBJOAKjVVJlodlQpebQRBJNn-Ylw3kgkv0YTtfVj6pZDS3LHNUM0ULict7jPVScCfHJNBwvaG3B7BY1uvshkpTfR6Zilae5gA_3ExIubr00mpj9_4gnikSTwuYpmRrcdd62Zp9ryukD34PjY_j2E7z5mAlmDjfwfYyjv9N4Kwko6rwLLSFwWcoNVlbOx-3oH0veR6XgOkvN3XdG6yjZEvCFSI",
            title = "Impulso de Produtividade de 21 Dias",
            description = "Maximize seu rendimento em três semanas",
            creatorName = "Carlos Souza",
            participantsCount = 512,
            category = CategoryType.EDUCATION,
            isTrending = true
        )
    )
}
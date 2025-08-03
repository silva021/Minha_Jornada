package com.silva021.minhajornada.ui

import com.silva021.minhajornada.data.dto.ChallengeDTO
import com.silva021.minhajornada.data.dto.CheckInDTO
import com.silva021.minhajornada.data.dto.CommunitiesDTO
import com.silva021.minhajornada.data.dto.CommunityDTO
import com.silva021.minhajornada.data.dto.ProfileDTO
import com.silva021.minhajornada.data.dto.ProfileStatsDTO
import com.silva021.minhajornada.data.dto.PublicChallengeDTO
import com.silva021.minhajornada.domain.model.CategoryType
import com.silva021.minhajornada.domain.model.CheckInStatus
import com.silva021.minhajornada.domain.model.Comment
import com.silva021.minhajornada.domain.model.DurationType
import com.silva021.minhajornada.domain.model.Post
import com.silva021.minhajornada.domain.model.PublicChallenge

object DatabaseFake {

    val publicChallenges = listOf(
        PublicChallengeDTO(
            id = "1",
            title = "Gratidão Diária",
            subtitle = "Cultive gratidão todos os dias",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuASsQk7dbUMj7ibrRtf12nogi2hU9h7VEUk49RzlD9KFgkwGVrsdnJYZbo0B5CdkzZI8F_apzhCeW2niZGJ7y_bUfJoaiI31zYy-8UO4O0c5u-NpTr9ucBRtghAbsjNLguwWiQiVE3Qh6BsgPeuFHjx0uCjPrQrinTN-bf2vHREo4nOIzDca431FJL5dXDvMirfyba_sOxKOymckFcByu468xOxxJ-HN8AEO_mTMOKQ6WI0bzoRRihNwHlbmgT5v8PCRzBwYNQ_xBU",
            creatorName = "Equipe Desafio+",
            participantsCount = 1340,
            category = CategoryType.MINDSET.name,
            duration = DurationType.SEVEN_DAYS.name,
            description = "A prática da gratidão diária pode transformar a forma como você enxerga sua vida...",
            rules = listOf(
                "Registre pelo menos 3 coisas pelas quais você é grato cada dia",
                "Escreva detalhes sobre cada item listado",
                "Compartilhe com um amigo uma vez por semana",
                "Mantenha o diário por 7 dias consecutivos",
            ),
            benefits = listOf(
                "Aumento do bem-estar emocional",
                "Maior consciência das coisas positivas",
                "Redução do estresse e ansiedade",
                "Melhora na qualidade do sono",
            )
        ),
        PublicChallengeDTO(
            id = "2",
            title = "Aprenda uma Nova Habilidade",
            subtitle = "Domine uma nova habilidade",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCu-akvszPRrab-2SAuWyfvyIUfgLWOH-8VC1Ndr6Diheee21Qz68BDK3mHkfLmBH7f5EDF3BxXHfra6ztGe4o3VPrfGhSDYp1aLrSbm16LQizsvqA7cz8jVrcq-9r6Jyvap8UUn56qQ_2Hke-zxMRDW0pJyzD4ggoL8wHz_rUZu0jkXC9MRhNfTsLIa9OHVNQnKKsSpj7nEwjX5NNjR1IiusU-kolZThnf8XyeA12WmNZ-wVdWkph-_wXjxvW5gqC2h8WtNycqX8c",
            creatorName = "Comunidade Skills+",
            participantsCount = 920,
            duration = DurationType.THREE_DAYS.name,
            category = CategoryType.EDUCATION.name,
            description = "Dominar algo novo pode ser mais rápido e prazeroso do que parece...",
            rules = listOf(
                "Escolha uma habilidade para aprender",
                "Dedique pelo menos 1 hora por dia",
                "Pratique ativamente, não apenas assista / leia",
                "Compartilhe seu progresso diariamente",
            ),
            benefits = listOf(
                "Expansão do conhecimento e habilidades",
                "Aumento da confiança pessoal",
                "Desenvolvimento de disciplina",
                "Estímulo à neuroplasticidade cerebral",
            )
        ),
        PublicChallengeDTO(
            id = "3",
            title = "Detox Digital",
            subtitle = "Desconecte-se da tecnologia",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBs0XQlz9mAWVDi3n53PW4I5rxNLvOjuJJJd-K0Y8Ovj-b0x9TBOfVy7zf4V2tjTBlAqZGMtKVyM1m3HCriRpycTjcYPL3xG9QUQOaeEi0pZmou60Za5djoVgtAXaK4wCQ9vOScpK9h6VtaHVS4mNy0vTA0e2P0cLa9y_TQAvLBuK77yYy9Gq9QRhD7NQBNiAN3urtqbs5yLHnjtJ81_TC5Rkg37-Oe1fQioDWvQLtI_Iy-wfEyeLNbPddtaswwqMh-sm1fkSHXnYU",
            creatorName = "DigitalFree",
            participantsCount = 1105,
            duration = DurationType.FOURTEEN_DAYS.name,
            category = CategoryType.MINDSET.name,
            description = "A tecnologia aproxima, mas também consome...",
            rules = listOf(
                "Nada de telas 1 h antes de dormir",
                "Limite de 2 h nas redes sociais por dia",
                "Ative o modo avião por 2 h durante o dia",
                "Jante sem dispositivos eletrônicos",
            ),
            benefits = listOf(
                "Melhora na qualidade do sono",
                "Redução da ansiedade digital",
                "Mais tempo para atividades presenciais",
                "Aumento da produtividade",
            )
        ),
        PublicChallengeDTO(
            id = "4",
            title = "Alimentação Saudável",
            subtitle = "Adote hábitos alimentares saudáveis",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuD9vgHgUNi5FW7XeXvA3UfQSw6KruG9cR32qkfmF2lPPxLCAI7ORwDuz1FQz1HVRkEnKMJGWwSca6K1N-I6rM9kBXXQT52cI5ysoNQgNmXujf2-ee3ecQII-du00VnVJxouo0FtJdhvRioTBDcpzx0aQ_jhGD2gsG9NulxBsR5Q9F2tYLtqP9mhwU8hIwQ6VuwVPMwm76rg0pxyPZfun1xRnPVvQ-rayxZJOBctgI4QWk0E8WI732pUqrvpCz24myS0QDkbRgqLjhI",
            creatorName = "NutriTeam",
            participantsCount = 1987,
            duration = DurationType.TWENTY_ONE_DAYS.name,
            category = CategoryType.FITNESS.name,
            description = "Transforme sua relação com a comida e aprenda a fazer escolhas mais saudáveis...",
            rules = listOf(
                "Coma pelo menos 3 porções de vegetais por dia",
                "Beba 2L de água diariamente",
                "Reduza alimentos processados",
                "Faça 5 refeições balanceadas por dia",
            ),
            benefits = listOf(
                "Mais energia e disposição",
                "Melhora na digestão",
                "Pele mais saudável",
                "Controle do peso corporal",
            )
        ),
        PublicChallengeDTO(
            id = "5",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBu1VDWNzIZGgVSP-NqdjIkQCX3gyvA-ICH9Xp7YsF8u-XWG9MWtfCjU6ddPsJzEv2iszLp_iJmVpl7vAginGvWGHfY13oYYIWn_YtzpL6QBTiaoYIwYGY12OSNSF9gokih6r0K21NviLwHaQ6kcyCzY1hiHYoUx4tSYUP3snqUpZBBsGr2WTjybGloX2axlldG70gR9Rv0iCp_ckD4GEMFaDN-2ycR29c5vOVHLIhO8-ysA3yENqGG_xjIFRUFm8SZL8Rny-AOnz0",
            title = "Desafio Fitness de 7 Dias",
            subtitle = "Fique em forma em apenas uma semana",
            creatorName = "Lucas Silva",
            participantsCount = 278,
            category = CategoryType.FITNESS.name,
            duration = DurationType.SEVEN_DAYS.name,
            isTrending = true,
            description = "Em apenas uma semana, você vai dar o primeiro passo para uma rotina mais ativa e saudável...",
            rules = listOf(
                "30 minutos de exercícios diários",
                "Alongamento matinal obrigatório",
                "10.000 passos por dia",
                "Troque 1 refeição por opção saudável",
            ),
            benefits = listOf(
                "Melhora da condição física",
                "Aumento da disposição",
                "Perda de peso inicial",
                "Estabelecimento de rotina saudável",
            )
        ),
        PublicChallengeDTO(
            id = "6",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDC1CK0fJJekJFIALgZN8l9nrRFGObdyllNq8lf6DxJZ8IUsb5qYwMzKWtGtN-nXBCqfdu17MHgeoPkKp5xDDpbXjwvfCrF3ZMz_rRdJwFs9TM3rN6uZEK6KhM1tmwEurY1MDeTe6BI1Jr3DpOlyYbCjLOjYG-gJmCqMF4PV-sBfIfIGIjhjDjdWnUEMGZC8KbUyko8bUnt65-EGafdK6qHIQQDjN5_kmznxLSEaxS4EkFAEUU4ehsgB6-vQT2Jl6Zk8Er3SKk78eY",
            title = "Jornada de Mindfulness de 30 Dias",
            subtitle = "Encontre paz interior em um mês",
            creatorName = "Ana Costa",
            participantsCount = 341,
            duration = DurationType.THIRTY_DAYS.name,
            category = CategoryType.MINDSET.name,
            isTrending = true,
            description = "Acalme a mente e aprofunde sua conexão com o presente...",
            rules = listOf(
                "Meditação de 10 minutos diários",
                "Pratique respiração consciente 3 x ao dia",
                "Anote observações sobre seu estado mental",
                "Desacelere uma atividade cotidiana",
            ),
            benefits = listOf(
                "Redução do estresse",
                "Maior foco e concentração",
                "Melhor regulação emocional",
                "Aumento da autoconsciência",
            )
        ),
        PublicChallengeDTO(
            id = "7",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCp9degKs731yeuU7AIizup4AYO7fTtAJgiurX93Bs7bqS7Ie6UiiWBJOAKjVVJlodlQpebQRBJNn-Ylw3kgkv0YTtfVj6pZDS3LHNUM0ULict7jPVScCfHJNBwvaG3B7BY1uvshkpTfR6Zilae5gA_3ExIubr00mpj9_4gnikSTwuYpmRrcdd62Zp9ryukD34PjY_j2E7z5mAlmDjfwfYyjv9N4Kwko6rwLLSFwWcoNVlbOx-3oH0veR6XgOkvN3XdG6yjZEvCFSI",
            title = "Impulso de Produtividade de 21 Dias",
            subtitle = "Maximize seu rendimento em três semanas",
            creatorName = "Carlos Souza",
            participantsCount = 512,
            duration = DurationType.THIRTY_DAYS.name,
            category = CategoryType.EDUCATION.name,
            isTrending = true,
            description = "Organize sua rotina, elimine distrações e aumente sua eficiência...",
            rules = listOf(
                "Defina 3 prioridades diárias",
                "Use a técnica Pomodoro (25 / 5)",
                "Elimine distrações digitais",
                "Revise o dia por 10 minutos",
            ),
            benefits = listOf(
                "Aumento da produtividade",
                "Melhor gestão do tempo",
                "Redução da procrastinação",
                "Maior senso de realização",
            )
        )
    )

    val comments = listOf(
        Comment(
            userImage = "https://lh3.googleusercontent.com/aida-public/AB6AXuAZlM4wvIsS3fmlcpSyTvhzzjZF7Q_Op85f3s0ny4TpDiU-MIz49SBtb9Gf8hUhkr2iIUVhMgywH7u7NsQDYdwmZE6Ss76FL1wjeaN_JFtmde7n6Tvw1KG87xs1qXLva771J63ZtojlMI36UezCJsXxCWUtPwLSS99k5PTQHq0TPMHcjrIoyG-em8wHtkwKnhrbJDoxHXm6kTMndU3tTbFbhOIu-e4JsCDfBa5hkydz3oja5kRYelhhzV28TBYRJgWvlqFXyo_pMuI",
            userName = "Ethan Carter",
            timeAgo = "1d",
            comment = "Isso é incrível, Jessica! Eu sempre quis aprender espanhol também. Me avise se precisar de um parceiro para praticar!"
        ),
        Comment(
            userImage = "https://lh3.googleusercontent.com/aida-public/AB6AXuCdBDfNXrteRJRjT-vER_7GASE5paihBZU6VF70j2REcHW5gn-lwFiYXIM82It99QIMKjnV8lgTWC5KzqQiA1o6S_Gfan0pgb5JnJ9Jnf2JPLn07FHfi13bmRApe7B6w3WEvJuQJRwzupIFK2iNI0nlmcuv6tNBoPMdO8O6i1rblXKZg4gHzSCnkHIFagBK2b0nHwqofhGfaCZGrQqOkUKFaYLGGvFDRt8htR1eaUIwA_QkEZhucRg9nFNL_kxX7xyUFU6RkyWAuU0",
            userName = "Olivia Davis",
            timeAgo = "2d",
            comment = "Boa sorte! Tenho certeza que você vai se sair bem. Lembre-se de celebrar seu progresso ao longo do caminho."
        )
    )

    val postList = listOf(
        Post(
            userImage = "https://lh3.googleusercontent.com/aida-public/AB6AXuDtOZOGqPBmoMIxOJODpUF6L55FFzYzDm-7hdl2NAIn44H2ViOQAGcW_9BLQ1JXgaCtWCA4Faq3I-uU4MF6pDd9ixBDalXweY9q1Tz1CBZe5F0dCHySwJjn21fBCZ-dHBU_lDUbFSItE21a0vIT-wO9bdYw1hPv5ej6XyBzb5sVHP3Lh0hi59j48AntkhtNH0ADIm--kflWd8AZa-ZA7GmULHesWk8EcM98tMB3ebeLOk5LiaU9-dgp-qwq5ouQj3JP4v1mc73v1L8",
            userName = "Sarah Miller",
            timeAgo = "2d",
            content = "Acabei de completar minha corrida de 5km! Me sentindo ótima e cheia de energia para o dia. Quem mais está alcançando seus objetivos fitness hoje?",
            likes = 23,
            comments = 5
        ),

        Post(
            userImage = "https://lh3.googleusercontent.com/aida-public/AB6AXuD38031a9CUlzl_YS565weCOqjozwjPs_D8QhTpnQNCNbreXCZXtJmMNmIYPu0l8vmmXMGS3EMcPwJG-tlMUr_-Y0y3qxdtsG0HyurbO0MBouSoZDdD9dX7QgSFqDi5nyeq3Hrioaln2cG345utbSVfOeEuzmpREIlRnlAJXNB_VTsajQcFh-KuG4hX_vWA1CaeRpIXU-1j-ZH20kzY2vxMbAvV1mpjvFXMgD82P80FTne_XTXi1yd1pVPBpE7GXZ-zHFKHnG1yHqo",
            userName = "Mark Thompson",
            timeAgo = "1sem",
            content = "Estou tentando incluir mais treino de força na minha rotina. Alguma dica para iniciantes?",
            likes = 15,
            comments = 3
        ),
        Post(
            userImage = "https://lh3.googleusercontent.com/aida-public/AB6AXuCbc0xLKqOdwb-Vi-QOdbZm9ShGVL6V1BcoLgrMCJ3aRNaobT2QFOCFnWF0fp1EdXFif8_exZfOcq5ANfbEtGZ0p1g9KqcNdtOvcbY_uq7jj9I4xYcnoZ4TZr5SEyfXs8ZdKwJ7bWwBwORGdm82ELnptKa1VcaLPNxIuSVLa5FQj6DGoPeTlEPTmiGbswtKYmBST5fc7gxGBIJB4rN1miXSXWb1BJfCvVrJEQtSzC6eoQAJzLD5A9yAUeUeuDEKQUo-oht2W1yKdQ8",
            userName = "Emily Carter",
            timeAgo = "3sem",
            content = "Alguém animado para uma trilha no final de semana? Vamos explorar novas trilhas e aproveitar o ar livre juntos!",
            likes = 30,
            comments = 8
        )
    )

    val challengesDto = listOf(
        ChallengeDTO(
            id = 2,
            title = "Correr 15 km",
            description = "Corra um total de 15 km em três dias.",
            categoryType = CategoryType.FITNESS.name,
            durationType = DurationType.THREE_DAYS.name,
            startDate = "2025-08-02",
            checkins = listOf(
                CheckInDTO(
                    day = 1,
                    date = "2025-07-10",
                    note = "Corri 3 km no parque hoje cedo. Ritmo bom, mas ainda estou voltando ao hábito.",
                    status = CheckInStatus.SUCCESS.name
                ),
                CheckInDTO(
                    day = 2,
                    date = "2025-07-11",
                    note = "Mais 3 km hoje, mas senti um pouco de dor na perna. Vou alongar melhor amanhã.",
                    status = CheckInStatus.SUCCESS.name
                )
            )
        ),
        ChallengeDTO(
            id = 5,
            title = "Beber 8 copos de água por dia",
            description = "Hábitos de hidratação saudáveis desenvolvidos.",
            categoryType = CategoryType.FITNESS.name,
            durationType = DurationType.SEVEN_DAYS.name,
            startDate = "2023-06-20",
            checkins = listOf(
                CheckInDTO(
                    day = 1,
                    date = "2023-06-20",
                    note = "Comecei bem, bebi os 8 copos. Usei um app pra lembrar.",
                    status = CheckInStatus.SUCCESS.name
                ),
                CheckInDTO(
                    day = 2,
                    date = "2023-06-21",
                    note = "Esqueci de manhã, mas recuperei à tarde. Completei!",
                    status = CheckInStatus.SUCCESS.name
                ),
                CheckInDTO(
                    day = 3,
                    date = "2023-06-22",
                    note = "Já estou com mais sede natural, o corpo tá pedindo água.",
                    status = CheckInStatus.SUCCESS.name
                ),
                CheckInDTO(
                    day = 4,
                    date = "2023-06-23",
                    note = "Fácil hoje. Levei uma garrafa de 2L comigo.",
                    status = CheckInStatus.FAILURE.name
                ),
                CheckInDTO(
                    day = 5,
                    date = "2023-06-24",
                    note = "Tá virando rotina. Só preciso lembrar antes de dormir.",
                    status = CheckInStatus.SUCCESS.name
                ),
                CheckInDTO(
                    day = 6,
                    date = "2023-06-25",
                    note = "Quase esqueci no meio do dia, mas deu tempo. Água salva!",
                    status = CheckInStatus.SUCCESS.name
                ),
                CheckInDTO(
                    day = 7,
                    date = "2023-06-26",
                    note = "Último dia! Me sinto mais leve e com menos dor de cabeça.",
                    status = CheckInStatus.SUCCESS.name
                )
            )
        )
    )

    val communities = CommunitiesDTO(
        my = listOf(
            CommunityDTO(
                id = "1",
                imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDG8U7Jgh29H6fv_GzSiE4iDYIGl5VlQae2Izv6y5Blp0DnD6BbcnsQQkBqMJWzZ92QGideUZxYCttOogvRLnGLuS1YQWLxz8VqWCDyPpKAnAeeVPjY97u8s8QyVe2X9HYfGmecxL0tEe9_3D1ULwVckACP7dzopBqwx6n8ifYfLZAL553x3aJPl1XMeeW3Zw-3UlpPxSTQCTpWq1zDhMA9vrwcgBflLecL1g6n5dXYRQg52DjG8_KQYdVTi40RI6DzLIpbY3YXYqA",
                name = "Fitness Fanáticos",
                members = 1200
            )
        ),
        featured = listOf(
            CommunityDTO(
                id = "2",
                imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDA4p3n7YAU0tuTTdUX1LXvST81S587HIZyGmvHVPennNBDBO44rX3GtNANF2o61oLDAyXlHfi6JeyLmtbEIvWxXS6CtSaEZtwXSnhdXgcwAsOnV6YjnW4u233WljWwK9R0nEhMKHv_bUknrvg-P8_jGN_uuStHzRRCKGQWvRZjiPew6af3P2SDzvcxQ2D80WZ1es7HpJZo-0dlAEI0uvSH0HiAEtEo7tkGl3rBhrOP1VjT9XpWAYRWl0Qp48KpncrzMxSShunC0iw",
                name = "Viciados em Leitura",
                members = 876
            )
        )
    )

    val profileDto = ProfileDTO(
        id = "u123",
        name = "Lucas Silva",
        username = "silva021",
        profilePictureUrl = "https://media.licdn.com/dms/image/v2/D4D03AQGHu5O7K6BgOA/profile-displayphoto-shrink_800_800/B4DZYRGyL2GwAk-/0/1744043714075?e=1756944000&v=beta&t=1a1Ej8JUBG-Awxu-qDbAXTUjCk3u5bPOGQ3rTsnPEtE",
        memberSince = "2025",
        stats = ProfileStatsDTO(
            challenges = 3,
            following = 0,
            followers = 0
        )
    )
}
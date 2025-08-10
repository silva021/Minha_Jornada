package com.silva021.minhajornada.ui

import com.google.firebase.Timestamp
import com.silva021.minhajornada.data.dto.CommentDTO
import com.silva021.minhajornada.data.dto.CommunitiesDTO
import com.silva021.minhajornada.data.dto.CommunityDTO
import com.silva021.minhajornada.data.dto.PostDTO
import com.silva021.minhajornada.data.dto.ProfileDTO
import com.silva021.minhajornada.data.dto.PublicChallengeDTO
import com.silva021.minhajornada.domain.model.CategoryType
import com.silva021.minhajornada.domain.model.Challenge
import com.silva021.minhajornada.domain.model.CheckIn
import com.silva021.minhajornada.domain.model.CheckInStatus
import com.silva021.minhajornada.domain.model.DurationType
import com.silva021.minhajornada.domain.model.Reminder
import com.silva021.minhajornada.domain.model.ReminderFrequency
import com.silva021.minhajornada.domain.model.Weekday

object DatabaseFake {

    val publicChallenges = listOf(
        PublicChallengeDTO(
            title = "Gratidão Diária",
            subtitle = "Cultive gratidão todos os dias",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuASsQk7dbUMj7ibrRtf12nogi2hU9h7VEUk49RzlD9KFgkwGVrsdnJYZbo0B5CdkzZI8F_apzhCeW2niZGJ7y_bUfJoaiI31zYy-8UO4O0c5u-NpTr9ucBRtghAbsjNLguwWiQiVE3Qh6BsgPeuFHjx0uCjPrQrinTN-bf2vHREo4nOIzDca431FJL5dXDvMirfyba_sOxKOymckFcByu468xOxxJ-HN8AEO_mTMOKQ6WI0bzoRRihNwHlbmgT5v8PCRzBwYNQ_xBU",
            creatorName = "Equipe Desafio+",
            participantsCount = 0,
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
            ),
            createdAt = Timestamp.now()
        ),
        PublicChallengeDTO(
            title = "Aprenda uma Nova Habilidade",
            subtitle = "Domine uma nova habilidade",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCu-akvszPRrab-2SAuWyfvyIUfgLWOH-8VC1Ndr6Diheee21Qz68BDK3mHkfLmBH7f5EDF3BxXHfra6ztGe4o3VPrfGhSDYp1aLrSbm16LQizsvqA7cz8jVrcq-9r6Jyvap8UUn56qQ_2Hke-zxMRDW0pJyzD4ggoL8wHz_rUZu0jkXC9MRhNfTsLIa9OHVNQnKKsSpj7nEwjX5NNjR1IiusU-kolZThnf8XyeA12WmNZ-wVdWkph-_wXjxvW5gqC2h8WtNycqX8c",
            creatorName = "Comunidade Skills+",
            participantsCount = 0,
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
            ),
            createdAt = Timestamp.now()
        ),
        PublicChallengeDTO(
            title = "Detox Digital",
            subtitle = "Desconecte-se da tecnologia",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBs0XQlz9mAWVDi3n53PW4I5rxNLvOjuJJJd-K0Y8Ovj-b0x9TBOfVy7zf4V2tjTBlAqZGMtKVyM1m3HCriRpycTjcYPL3xG9QUQOaeEi0pZmou60Za5djoVgtAXaK4wCQ9vOScpK9h6VtaHVS4mNy0vTA0e2P0cLa9y_TQAvLBuK77yYy9Gq9QRhD7NQBNiAN3urtqbs5yLHnjtJ81_TC5Rkg37-Oe1fQioDWvQLtI_Iy-wfEyeLNbPddtaswwqMh-sm1fkSHXnYU",
            creatorName = "DigitalFree",
            participantsCount = 0,
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
            ),
            createdAt = Timestamp.now()
        ),
        PublicChallengeDTO(
            title = "Alimentação Saudável",
            subtitle = "Adote hábitos alimentares saudáveis",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuD9vgHgUNi5FW7XeXvA3UfQSw6KruG9cR32qkfmF2lPPxLCAI7ORwDuz1FQz1HVRkEnKMJGWwSca6K1N-I6rM9kBXXQT52cI5ysoNQgNmXujf2-ee3ecQII-du00VnVJxouo0FtJdhvRioTBDcpzx0aQ_jhGD2gsG9NulxBsR5Q9F2tYLtqP9mhwU8hIwQ6VuwVPMwm76rg0pxyPZfun1xRnPVvQ-rayxZJOBctgI4QWk0E8WI732pUqrvpCz24myS0QDkbRgqLjhI",
            creatorName = "NutriTeam",
            participantsCount = 0,
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
            ),
            createdAt = Timestamp.now()
        ),
        PublicChallengeDTO(
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBu1VDWNzIZGgVSP-NqdjIkQCX3gyvA-ICH9Xp7YsF8u-XWG9MWtfCjU6ddPsJzEv2iszLp_iJmVpl7vAginGvWGHfY13oYYIWn_YtzpL6QBTiaoYIwYGY12OSNSF9gokih6r0K21NviLwHaQ6kcyCzY1hiHYoUx4tSYUP3snqUpZBBsGr2WTjybGloX2axlldG70gR9Rv0iCp_ckD4GEMFaDN-2ycR29c5vOVHLIhO8-ysA3yENqGG_xjIFRUFm8SZL8Rny-AOnz0",
            title = "Desafio Fitness de 7 Dias",
            subtitle = "Fique em forma em apenas uma semana",
            creatorName = "Lucas Silva",
            participantsCount = 0,
            category = CategoryType.FITNESS.name,
            duration = DurationType.SEVEN_DAYS.name,
            trending = true,
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
            ),
            createdAt = Timestamp.now()
        ),
        PublicChallengeDTO(
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDC1CK0fJJekJFIALgZN8l9nrRFGObdyllNq8lf6DxJZ8IUsb5qYwMzKWtGtN-nXBCqfdu17MHgeoPkKp5xDDpbXjwvfCrF3ZMz_rRdJwFs9TM3rN6uZEK6KhM1tmwEurY1MDeTe6BI1Jr3DpOlyYbCjLOjYG-gJmCqMF4PV-sBfIfIGIjhjDjdWnUEMGZC8KbUyko8bUnt65-EGafdK6qHIQQDjN5_kmznxLSEaxS4EkFAEUU4ehsgB6-vQT2Jl6Zk8Er3SKk78eY",
            title = "Jornada de Mindfulness de 30 Dias",
            subtitle = "Encontre paz interior em um mês",
            creatorName = "Ana Costa",
            participantsCount = 0,
            duration = DurationType.THIRTY_DAYS.name,
            category = CategoryType.MINDSET.name,
            trending = true,
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
            ),
            createdAt = Timestamp.now()
        ),
        PublicChallengeDTO(
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCp9degKs731yeuU7AIizup4AYO7fTtAJgiurX93Bs7bqS7Ie6UiiWBJOAKjVVJlodlQpebQRBJNn-Ylw3kgkv0YTtfVj6pZDS3LHNUM0ULict7jPVScCfHJNBwvaG3B7BY1uvshkpTfR6Zilae5gA_3ExIubr00mpj9_4gnikSTwuYpmRrcdd62Zp9ryukD34PjY_j2E7z5mAlmDjfwfYyjv9N4Kwko6rwLLSFwWcoNVlbOx-3oH0veR6XgOkvN3XdG6yjZEvCFSI",
            title = "Impulso de Produtividade de 21 Dias",
            subtitle = "Maximize seu rendimento em três semanas",
            creatorName = "Carlos Souza",
            participantsCount = 0,
            duration = DurationType.THIRTY_DAYS.name,
            category = CategoryType.EDUCATION.name,
            trending = true,
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
            ),
            createdAt = Timestamp.now()
        )
    )

    val comments = listOf(
        CommentDTO(
            postId = "1",
            profile = profilesDTO.first(),
            createdAt = "2025-08-03",
            comment = "Isso é incrível, Jessica! Eu sempre quis aprender espanhol também. Me avise se precisar de um parceiro para praticar!"
        ),
        CommentDTO(
            postId = "1",
            profile = profilesDTO[1],
            createdAt = "2025-04-02",
            comment = "Boa sorte! Tenho certeza que você vai se sair bem. Lembre-se de celebrar seu progresso ao longo do caminho."
        )
    )

    val postList = listOf(
        PostDTO(
            id = "1",
            profile = profilesDTO[2],
            createdAt = "2025-08-03",
            content = "Acabei de completar minha corrida de 5km! Me sentindo ótima e cheia de energia para o dia. Quem mais está alcançando seus objetivos fitness hoje?",
            likes = 23,
            comments = 5
        ),
        PostDTO(
            id = "2",
            profile = profilesDTO.first(),
            createdAt = "2025-07-02",
            content = "Estou tentando incluir mais treino de força na minha rotina. Alguma dica para iniciantes?",
            likes = 15,
            comments = 3
        ),
        PostDTO(
            id = "3",
            profile = profilesDTO[0],
            createdAt = "2025-06-02",
            content = "Alguém animado para uma trilha no final de semana? Vamos explorar novas trilhas e aproveitar o ar livre juntos!",
            likes = 30,
            comments = 8
        ),
        PostDTO(
            id = "4",
            profile = profilesDTO[2],
            createdAt = "2025-05-02",
            content = "Acabei de completar minha corrida de 5km! Me sentindo ótima e cheia de energia para o dia. Quem mais está alcançando seus objetivos fitness hoje?",
            likes = 23,
            comments = 5
        ),
        PostDTO(
            id = "5",
            profile = profilesDTO[1],
            createdAt = "2025-04-02",
            content = "Estou tentando incluir mais treino de força na minha rotina. Alguma dica para iniciantes?",
            likes = 15,
            comments = 3
        ),
        PostDTO(
            id = "6",
            profile = profilesDTO[0],
            createdAt = "2025-03-02",
            content = "Alguém animado para uma trilha no final de semana? Vamos explorar novas trilhas e aproveitar o ar livre juntos!",
            likes = 30,
            comments = 8
        )
    )

    val challenges = listOf(
        Challenge(
            id = "1",
            title = "Correr 15 km",
            description = "Corra um total de 15 km em três dias.",
            categoryType = CategoryType.FITNESS,
            durationType = DurationType.THREE_DAYS,
            startDate = Timestamp.now(),
            checkins = listOf(
                CheckIn(
                    id = "checkin1",
                    day = 1,
                    date = Timestamp.now(),
                    note = "Corri 3 km no parque hoje cedo. Ritmo bom, mas ainda estou voltando ao hábito.",
                    status = CheckInStatus.SUCCESS
                ),
                CheckIn(
                    id = "checkin2",
                    day = 2,
                    date = Timestamp.now(),
                    note = "Mais 3 km hoje, mas senti um pouco de dor na perna. Vou alongar melhor amanhã.",
                    status = CheckInStatus.SUCCESS
                )
            ),
            ownerName = "Lucas Silva",
            isCompleted = true,
            isPublic = true,
            reminders = mockReminders
        ),
        Challenge(
            id = "2",
            title = "Beber 8 copos de água por dia",
            description = "Hábitos de hidratação saudáveis desenvolvidos.",
            categoryType = CategoryType.FITNESS,
            durationType = DurationType.SEVEN_DAYS,
            startDate = Timestamp.now(),
            checkins = listOf(
                CheckIn(
                    id = "checkin3",
                    day = 1,
                    date = Timestamp.now(),
                    note = "Comecei bem, bebi os 8 copos. Usei um app pra lembrar.",
                    status = CheckInStatus.SUCCESS
                ),
                CheckIn(
                    id = "checkin4",
                    day = 2,
                    date = Timestamp.now(),
                    note = "Esqueci de manhã, mas recuperei à tarde. Completei!",
                    status = CheckInStatus.SUCCESS
                ),
                CheckIn(
                    id = "checkin5",
                    day = 3,
                    date = Timestamp.now(),
                    note = "Já estou com mais sede natural, o corpo tá pedindo água.",
                    status = CheckInStatus.SUCCESS
                ),
                CheckIn(
                    id = "checkin6",
                    day = 4,
                    date = Timestamp.now(),
                    note = "Fácil hoje. Levei uma garrafa de 2L comigo.",
                    status = CheckInStatus.FAILURE
                ),
                CheckIn(
                    id = "checkin7",
                    day = 5,
                    date = Timestamp.now(),
                    note = "Tá virando rotina. Só preciso lembrar antes de dormir.",
                    status = CheckInStatus.SUCCESS
                ),
                CheckIn(
                    id = "checkin8",
                    day = 6,
                    date = Timestamp.now(),
                    note = "Quase esqueci no meio do dia, mas deu tempo. Água salva!",
                    status = CheckInStatus.SUCCESS
                ),
                CheckIn(
                    id = "checkin9",
                    day = 7,
                    date = Timestamp.now(),
                    note = "Último dia! Me sinto mais leve e com menos dor de cabeça.",
                    status = CheckInStatus.SUCCESS
                )
            ),
            reminders = mockReminders,
            isCompleted = true,
            isPublic = false,
            ownerName = "Lucas Silva",
        )
    )

    val communities = CommunitiesDTO(
        my = listOf(
            CommunityDTO(
                id = "1",
                imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDG8U7Jgh29H6fv_GzSiE4iDYIGl5VlQae2Izv6y5Blp0DnD6BbcnsQQkBqMJWzZ92QGideUZxYCttOogvRLnGLuS1YQWLxz8VqWCDyPpKAnAeeVPjY97u8s8QyVe2X9HYfGmecxL0tEe9_3D1ULwVckACP7dzopBqwx6n8ifYfLZAL553x3aJPl1XMeeW3Zw-3UlpPxSTQCTpWq1zDhMA9vrwcgBflLecL1g6n5dXYRQg52DjG8_KQYdVTi40RI6DzLIpbY3YXYqA",
                name = "Fitness Fanáticos",
                description = "Uma comunidade para todos que amam fitness, saúde e bem-estar.",
                category = CategoryType.FITNESS.name,
                about = "Esta comunidade é para pessoas que amam fitness, saúde e bem-estar. Compartilhe dicas, treinos, receitas saudáveis e muito mais.",
                membersCount = 1200
            )
        ),
        discover = listOf(
            CommunityDTO(
                id = "2",
                imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDA4p3n7YAU0tuTTdUX1LXvST81S587HIZyGmvHVPennNBDBO44rX3GtNANF2o61oLDAyXlHfi6JeyLmtbEIvWxXS6CtSaEZtwXSnhdXgcwAsOnV6YjnW4u233WljWwK9R0nEhMKHv_bUknrvg-P8_jGN_uuStHzRRCKGQWvRZjiPew6af3P2SDzvcxQ2D80WZ1es7HpJZo-0dlAEI0uvSH0HiAEtEo7tkGl3rBhrOP1VjT9XpWAYRWl0Qp48KpncrzMxSShunC0iw",
                name = "Viciados em Leitura",
                about = "Para os amantes de livros e leitura. Compartilhe resenhas, dicas de leitura e participe de discussões literárias.",
                description = "Uma comunidade para os amantes de livros e leitura. Compartilhe resenhas, dicas de leitura e participe de discussões literárias.",
                category = CategoryType.EDUCATION.name,
                membersCount = 876
            )
        )
    )
}

val profilesDTO = listOf(
    ProfileDTO(
        id = "u123",
        name = "Lucas Silva",
        username = "silva021",
        profilePictureUrl = "https://media.licdn.com/dms/image/v2/D4D03AQGHu5O7K6BgOA/profile-displayphoto-shrink_800_800/B4DZYRGyL2GwAk-/0/1744043714075?e=1756944000&v=beta&t=1a1Ej8JUBG-Awxu-qDbAXTUjCk3u5bPOGQ3rTsnPEtE",
        createdAt = Timestamp.now(),
        email = "lucas@gmail.com"
    ),
    ProfileDTO(
        id = "u123",
        profilePictureUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDtOZOGqPBmoMIxOJODpUF6L55FFzYzDm-7hdl2NAIn44H2ViOQAGcW_9BLQ1JXgaCtWCA4Faq3I-uU4MF6pDd9ixBDalXweY9q1Tz1CBZe5F0dCHySwJjn21fBCZ-dHBU_lDUbFSItE21a0vIT-wO9bdYw1hPv5ej6XyBzb5sVHP3Lh0hi59j48AntkhtNH0ADIm--kflWd8AZa-ZA7GmULHesWk8EcM98tMB3ebeLOk5LiaU9-dgp-qwq5ouQj3JP4v1mc73v1L8",
        name = "Sarah Miller",
        username = "SarahMIMI",
        createdAt = Timestamp.now(),
        email = "sara@gmail.com"
    ),
    ProfileDTO(
        id = "u123",
        name = "Mark Thompson",
        username = "MarkTThompson",
        profilePictureUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuD38031a9CUlzl_YS565weCOqjozwjPs_D8QhTpnQNCNbreXCZXtJmMNmIYPu0l8vmmXMGS3EMcPwJG-tlMUr_-Y0y3qxdtsG0HyurbO0MBouSoZDdD9dX7QgSFqDi5nyeq3Hrioaln2cG345utbSVfOeEuzmpREIlRnlAJXNB_VTsajQcFh-KuG4hX_vWA1CaeRpIXU-1j-ZH20kzY2vxMbAvV1mpjvFXMgD82P80FTne_XTXi1yd1pVPBpE7GXZ-zHFKHnG1yHqo",
        createdAt = Timestamp.now(),
        email = "mark@gmail.com"
    )
)

val mockReminders = listOf(
    Reminder(
        id = "1",
        hour = 10,
        minute = 12,
        weekday = Weekday.SATURDAY,
        frequency = ReminderFrequency.DAILY,
        active = true
    ),
    Reminder(
        id = "2",
        hour = 11,
        minute = 12,
        weekday = Weekday.MONDAY,
        frequency = ReminderFrequency.WEEKLY,
        active = true
    ),
    Reminder(
        id = "3",
        hour = 12,
        minute = 12,
        weekday = Weekday.SATURDAY,
        frequency = ReminderFrequency.WEEKDAYS,
        active = false
    ),
    Reminder(
        id = "4",
        hour = 13,
        minute = 12,
        weekday = Weekday.WEDNESDAY,
        frequency = ReminderFrequency.WEEKENDS,
        active = true
    ),
    Reminder(
        id = "5",
        hour = 14,
        minute = 12,
        weekday = Weekday.MONDAY,
        frequency = ReminderFrequency.WEEKLY,
        active = true
    ),
    Reminder(
        id = "6",
        hour = 15,
        minute = 12,
        weekday = Weekday.SATURDAY,
        frequency = ReminderFrequency.WEEKDAYS,
        active = false
    ),
    Reminder(
        id = "7",
        hour = 16,
        minute = 12,
        weekday = Weekday.WEDNESDAY,
        frequency = ReminderFrequency.WEEKENDS,
        active = true
    )
).take(5)
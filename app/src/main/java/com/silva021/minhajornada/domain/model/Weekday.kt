package com.silva021.minhajornada.domain.model

enum class Weekday(
    val fullName: String,
    val shortName: String,
    val isoNumber: Int
) {
    MONDAY("Segunda-feira", "Seg", 1),
    TUESDAY("Terça-feira", "Ter", 2),
    WEDNESDAY("Quarta-feira", "Qua", 3),
    THURSDAY("Quinta-feira", "Qui", 4),
    FRIDAY("Sexta-feira", "Sex", 5),
    SATURDAY("Sábado", "Sáb", 6),
    SUNDAY("Domingo", "Dom", 7);
}

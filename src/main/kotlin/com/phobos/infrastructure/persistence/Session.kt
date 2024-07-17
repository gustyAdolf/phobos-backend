package com.phobos.infrastructure.persistence

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "session")
data class Session(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(name = "session_date", nullable = false)
    val sessionDate: LocalDate,

    @Column(name = "activation_level", nullable = false)
    val activationLevel: Int,

    @Column(name = "exposure_level", nullable = false)
    val exposureLevel: Int,

    @Column(name = "user_id", nullable = false)
    val userId: Int,

    @Column(name = "mental_disorder_id", nullable = false)
    val mentalDisorderId: Int,

    @Column(name = "progress", nullable = false)
    val progress: Int,

    @Column(name = "therapist_notes")
    val therapistNotes: String,

    @Column(name = "user_notes")
    val userNotes: String
)

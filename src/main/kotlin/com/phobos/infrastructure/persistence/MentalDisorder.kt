package com.phobos.infrastructure.persistence

import jakarta.persistence.*

@Entity
@Table(name = "mental_disorder")
data class MentalDisorder (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @Column(name = "mental_disorder", nullable = false)
    val mentalDisorder: String,
)
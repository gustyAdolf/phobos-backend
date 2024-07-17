package com.phobos.infrastructure.persistence.user

import com.phobos.infrastructure.persistence.MentalDisorder
import jakarta.persistence.*

@Entity
@Table(name = "user_disorder")
data class UserDisorder(
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Id
    @ManyToOne
    @JoinColumn(name = "mental_disorder_id", nullable = false)
    val mentalDisorder: MentalDisorder
)

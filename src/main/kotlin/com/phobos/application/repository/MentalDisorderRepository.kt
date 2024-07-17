package com.phobos.application.repository

import com.phobos.infrastructure.persistence.MentalDisorder
import org.springframework.data.jpa.repository.JpaRepository

interface MentalDisorderRepository : JpaRepository<MentalDisorder, Int> {
}
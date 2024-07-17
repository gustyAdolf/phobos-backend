package com.phobos.application.service

import com.phobos.application.dto.MentalDisorderResponse
import com.phobos.application.repository.MentalDisorderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MentalDisorderService(
    @Autowired val mentalDisorderRepository: MentalDisorderRepository
) {
    @Transactional(readOnly = true)
    fun getMentalDisorders(): List<MentalDisorderResponse> {
        return mentalDisorderRepository.findAll().map {
            MentalDisorderResponse(id = it.id, mentalDisorder = it.mentalDisorder)
        }
    }
}
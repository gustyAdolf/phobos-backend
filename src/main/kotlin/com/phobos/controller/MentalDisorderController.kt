package com.phobos.controller

import com.phobos.application.dto.MentalDisorderResponse
import com.phobos.application.service.MentalDisorderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/mental-disorder")
class MentalDisorderController(
    @Autowired private val mentalDisorderService: MentalDisorderService
) {

    @GetMapping
    fun getMentalDisorder(): ResponseEntity<List<MentalDisorderResponse>> {
        return ResponseEntity.ok(mentalDisorderService.getMentalDisorders())
    }
}
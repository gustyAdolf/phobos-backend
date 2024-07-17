package com.phobos.controller

import com.phobos.application.dto.MentalDisorderResponse
import com.phobos.application.dto.user.UserRequest
import com.phobos.application.dto.user.UserResponse
import com.phobos.application.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("/users")
class UserController(@Autowired val userService: UserService) {

    @GetMapping
    fun getAllUsers(
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) mentalDisorder: String?,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
        @RequestParam(defaultValue = "name,asc") sort: String,
    ): ResponseEntity<Page<UserResponse>> {
        val sortParams = sort.split(",")
        val sortField = sortParams[0]
        val sortDirection = if (sortParams.size > 1 && sortParams[1].equals("desc")) {
            Sort.Direction.DESC
        } else {
            Sort.Direction.ASC
        }
        val pageable: Pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortField))
        val userPage: Page<UserResponse> = userService.getUsers(name, mentalDisorder, pageable)
        return ResponseEntity.ok(userPage)
    }

    @GetMapping("/{userId}/mental-disorder")
    fun getUserMentalDisorder(
        @PathVariable(value = "userId", required = true) userId: Int
    ): ResponseEntity<List<MentalDisorderResponse>> {
        return ResponseEntity.ok(userService.getMentalDisordersByUserId(userId))
    }

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun createUser(
        @RequestPart userRequest: UserRequest,
        @RequestParam("image", required = false) image: MultipartFile?
    ): ResponseEntity<Void> {
        userService.createUser(userRequest, image)
        return ResponseEntity.ok().build()
    }
}
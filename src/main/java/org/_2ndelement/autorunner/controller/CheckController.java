package org._2ndelement.autorunner.controller;

import lombok.RequiredArgsConstructor;
import org._2ndelement.autorunner.response.Response;
import org._2ndelement.autorunner.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/check")
@RequiredArgsConstructor
public class CheckController {
    private final UserService userService;


    @GetMapping("/username")
    ResponseEntity<?> existUsername(@RequestParam String username) {
        return ResponseEntity.ok().body(Response.ok(userService.exist("username", username)));
    }

    @GetMapping("/email")
    ResponseEntity<?> existEmail(@RequestParam String email) {
        return ResponseEntity.ok().body(Response.ok(userService.exist("email", email)));
    }
}

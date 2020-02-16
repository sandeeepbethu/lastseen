package com.reliance.assessment.lastseen.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/health")
public class StatusController {
    @GetMapping(value = "")
    public String application_health_status() {
        return "ok";
    }
}

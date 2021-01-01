package com.example.FinalProject.controller;

import com.example.FinalProject.model.Person;
import com.example.FinalProject.model.Values;
import com.example.FinalProject.service.FinalProjectService;
import com.example.FinalProject.utils.featureSwitchPerformOperationConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.sql.Timestamp;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class FinalProjectController
{
    private final FinalProjectService finalProjectService;
    private final featureSwitchPerformOperationConfiguration featureSwitchPerformOperationConfiguration;

    @PostMapping("/start")
    public Object isUnderAgeThirteen(@RequestBody Person person)
    {
        try {
            return finalProjectService.isUnderAgeThirteen(person);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        }
    }

    @GetMapping("/time")
    public Timestamp getTime()
    {
        return finalProjectService.getTime();
    }

    @PostMapping("/{operation}")
    public Object performOperation(@RequestBody Values values, @PathVariable (value = "operation") String operation, @RequestParam double v1)
    {
        try {
            if (featureSwitchPerformOperationConfiguration.isPerformOperationOn()) {
                return finalProjectService.performOperation(values, operation, v1);
            } else {
                return new ResponseEntity<>("No math allowed here.", HttpStatus.SERVICE_UNAVAILABLE);
            }
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        }
    }
}
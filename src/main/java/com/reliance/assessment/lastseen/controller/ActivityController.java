package com.reliance.assessment.lastseen.controller;

import com.reliance.assessment.lastseen.ActivityService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 406, message = "Media type not acceptable: Only JSON responses are supported"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 200, message = "Success", response = String.class) })
    @GetMapping(value = "/last_seen_data/{person_name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> personLastSeenData(@PathVariable(value = "person_name") String person_name) {
        if(StringUtils.isEmpty(person_name) || StringUtils.isBlank(person_name))
            return new ResponseEntity("Input data cannot be null or empty.", HttpStatus.BAD_REQUEST);
        return activityService.personLastSeenData(person_name);
    }
}

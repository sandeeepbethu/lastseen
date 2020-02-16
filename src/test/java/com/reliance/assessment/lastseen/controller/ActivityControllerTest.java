package com.reliance.assessment.lastseen.controller;

import com.reliance.assessment.lastseen.ActivityService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ActivityControllerTest {

    private ActivityController activityController;
    private ActivityService activityService;

    @Before
    public void setUp() {
        activityService = mock(ActivityService.class);
        activityController = new ActivityController(activityService);
    }

    @Test
    public void personLastSeenData_Blank() {
        ResponseEntity<String> responseEntity = new ResponseEntity("", HttpStatus.BAD_REQUEST);
        when(activityService.personLastSeenData("")).thenReturn(responseEntity);

        ResponseEntity actual = activityController.personLastSeenData("");

        Assert.assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
    }

    @Test
    public void personLastSeenData_NotBlank() {
        ResponseEntity<String> responseEntity = new ResponseEntity("a", HttpStatus.OK);
        when(activityService.personLastSeenData("a")).thenReturn(responseEntity);

        ResponseEntity actual = activityController.personLastSeenData("a");

        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
    }
}

package com.reliance.assessment.lastseen.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StatusControllerTest {

    private StatusController statusController;

    @Before
    public void setUp() {
        statusController = new StatusController();
    }

    @Test
    public void application_health_status() {
        Assert.assertEquals("ok", statusController.application_health_status());
    }
}

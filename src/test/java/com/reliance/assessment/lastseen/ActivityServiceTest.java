package com.reliance.assessment.lastseen;

import com.reliance.assessment.lastseen.common.Labels;
import com.reliance.assessment.lastseen.config.AppConfiguration;
import com.reliance.assessment.lastseen.model.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ActivityServiceTest {

    private ActivityService activityService;
    private AppConfiguration appConfiguration;
    private Response response;
    private Labels labels;

    @Before
    public void setUp() {
        appConfiguration = mock(AppConfiguration.class);
        response = mock(Response.class);
        labels = mock(Labels.class);

        activityService = new ActivityService(appConfiguration, response, labels);
    }

    @Test
    public void personLastSeenData_Success_DaysOld() {
        String personArray[] = new String[1];
        personArray[0] = "a";
        String lastVisitArray[] = new String[1];
        lastVisitArray[0] = "2020/02/14 10:00:00";

        when(appConfiguration.getPerson_list()).thenReturn(personArray);
        when(appConfiguration.getLast_visited_list()).thenReturn(lastVisitArray);

        ResponseEntity actual = activityService.personLastSeenData("a");

        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    public void personLastSeenData_Success_YearsOld() {
        String personArray[] = new String[1];
        personArray[0] = "a";
        String lastVisitArray[] = new String[1];
        lastVisitArray[0] = "2019/02/14 10:00:00";

        when(appConfiguration.getPerson_list()).thenReturn(personArray);
        when(appConfiguration.getLast_visited_list()).thenReturn(lastVisitArray);

        ResponseEntity actual = activityService.personLastSeenData("a");

        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    public void personLastSeenData_Success_MonthsOld() {
        String personArray[] = new String[1];
        personArray[0] = "a";
        String lastVisitArray[] = new String[1];
        lastVisitArray[0] = "2020/01/14 10:00:00";

        when(appConfiguration.getPerson_list()).thenReturn(personArray);
        when(appConfiguration.getLast_visited_list()).thenReturn(lastVisitArray);

        ResponseEntity actual = activityService.personLastSeenData("a");

        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    public void personLastSeenData_NoData() {
        String personArray[] = new String[1];
        personArray[0] = "a";

        when(appConfiguration.getPerson_list()).thenReturn(personArray);

        ResponseEntity actual = activityService.personLastSeenData("aa");

        Assert.assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode());
    }

    @Test
    public void personLastSeenData_Exception() {
        when(appConfiguration.getPerson_list()).thenReturn(null);

        ResponseEntity actual = activityService.personLastSeenData("a");

        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actual.getStatusCode());
    }
}

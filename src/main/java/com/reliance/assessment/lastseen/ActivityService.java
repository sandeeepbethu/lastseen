package com.reliance.assessment.lastseen;

import com.reliance.assessment.lastseen.common.Labels;
import com.reliance.assessment.lastseen.config.AppConfiguration;
import com.reliance.assessment.lastseen.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ActivityService {
    private final Response response;
    private final AppConfiguration appConfiguration;
    private final Labels labels;

    private final String daySuffix = "D--";
    private final String hourSuffix = "H--";
    private final String minuteSuffix = "M--";
    private final String secondSuffix = "S";

    public ActivityService(AppConfiguration appConfiguration, Response response, Labels labels) {
        this.appConfiguration = appConfiguration;
        this.response = response;
        this.labels = labels;
    }

    public ResponseEntity<String> personLastSeenData(String person_name) {
        ResponseEntity responseEntity;
        response.setPerson_name(person_name);
        try {
            List<String> person_list = Arrays.stream(appConfiguration.getPerson_list()).collect(Collectors.toList());

            if(person_list.contains(person_name)) {
                String last_seen_tick = computeLastSeen(person_name);
                response.setLast_seen_tick(last_seen_tick);
                responseEntity = new ResponseEntity(response, HttpStatus.OK);
            } else {
                response.setLast_seen_tick(labels.data_unavailable);
                response.setLast_seen_crux(null);
                responseEntity = new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            response.setError_message(exception.getMessage());
            responseEntity = new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return  responseEntity;
    }

    private String computeLastSeen(String person_name) throws ParseException {
        StringBuilder tick = new StringBuilder();
        Date current_date = new SimpleDateFormat(labels.date_format).parse(mapLastVisitToPersonName(person_name));
        long date_time_difference = new Date().getTime() - current_date.getTime();
        long difference_seconds = date_time_difference / 1000 % 60;
        long difference_minutes = date_time_difference / (60 * 1000) % 60;
        long difference_hours = date_time_difference / (60 * 60 * 1000) % 24;
        long difference_days = date_time_difference / (24 * 60 * 60 * 1000);

        computeLastSeenCrux(difference_days, difference_hours, difference_minutes, difference_seconds);

        tick.append(difference_days);
        tick.append(daySuffix);
        tick.append(difference_hours);
        tick.append(hourSuffix);
        tick.append(difference_minutes);
        tick.append(minuteSuffix);
        tick.append(difference_seconds);
        tick.append(secondSuffix);

        return tick.toString();
    }

    private String mapLastVisitToPersonName(String person_name) {
        List<String> last_visit_list = Arrays.stream(appConfiguration.getLast_visited_list()).collect(Collectors.toList());
        switch (person_name) {
            case "a" : return last_visit_list.get(0);
            case "b" : return last_visit_list.get(1);
            case "c" : return last_visit_list.get(2);
            case "d" : return last_visit_list.get(3);
            default: return null;
        }
    }

    private void computeLastSeenCrux(long difference_days, long difference_hours, long difference_minutes, long difference_seconds) {
        if(difference_days>=365)
            response.setLast_seen_crux(labels.year_message);
        else if(difference_days >= 30)
            response.setLast_seen_crux(labels.month_message);
        else if(difference_days >= 7)
            response.setLast_seen_crux(labels.week_message);
        else if(difference_days >= 1)
            response.setLast_seen_crux(difference_days + labels.days_message);
        else if(difference_hours >= 1)
            response.setLast_seen_crux(difference_hours + labels.hours_message);
        else if(difference_minutes >= 1)
            response.setLast_seen_crux(difference_minutes + labels.minutes_message);
        else
            response.setLast_seen_crux(difference_seconds + labels.seconds_message);
    }
}

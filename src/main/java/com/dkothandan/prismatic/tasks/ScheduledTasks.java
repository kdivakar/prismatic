package com.dkothandan.prismatic.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private final CampGroundReservationAvailabilityTask campGroundReservationAvailabilityTask;

    public ScheduledTasks(CampGroundReservationAvailabilityTask campGroundReservationAvailabilityTask) {
        this.campGroundReservationAvailabilityTask = campGroundReservationAvailabilityTask;
    }

//    @Scheduled(fixedRate = (1000 * 60 * 30)) // 30 mins
//    public void campGroundReservationAvailability() {
//        log.info("The time is now {}", dateFormat.format(new Date()));
//        campGroundReservationAvailabilityTask.check();
//    }
}

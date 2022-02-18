package com.dkothandan.prismatic.tasks;

import com.dkothandan.prismatic.utils.PrismaticConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {

    private PrismaticConfig prismaticConfig;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private final CampGroundReservationAvailabilityTask campGroundReservationAvailabilityTask;

    public ScheduledTasks(CampGroundReservationAvailabilityTask campGroundReservationAvailabilityTask, PrismaticConfig prismaticConfig) {
        this.campGroundReservationAvailabilityTask = campGroundReservationAvailabilityTask;
        this.prismaticConfig = prismaticConfig;
        System.out.println(prismaticConfig.getSendGridAPIKey());
    }

//    @Scheduled(fixedRate = (1000 * 60 * 30)) // 30 mins
//    public void campGroundReservationAvailability() {
//        log.info("The time is now {}", dateFormat.format(new Date()));
//        campGroundReservationAvailabilityTask.check();
//    }
}

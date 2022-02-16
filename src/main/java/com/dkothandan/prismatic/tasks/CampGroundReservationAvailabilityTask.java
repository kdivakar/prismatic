package com.dkothandan.prismatic.tasks;

import com.dkothandan.prismatic.models.Campsite;
import com.dkothandan.prismatic.models.CampsiteAvailability;
import com.dkothandan.prismatic.utils.EmailHelper;
import com.dkothandan.prismatic.utils.HttpClientHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Component
public class CampGroundReservationAvailabilityTask {

    @Autowired
    EmailHelper emailHelper;

    @Value("${CAMP_RESERVATION_FROM_EMAIL}")
    private String fromEmail;

    @Value("${CAMP_RESERVATION_TO_EMAIL}")
    private String toEmail;

    class CampSiteRequest {
        String id, name, month;

        public CampSiteRequest(String id, String name, String month) {
            this.id = id;
            this.name = name;
            this.month = month;
        }
    }

    public List<CampSiteRequest> getCamps() {
        List<CampSiteRequest> requests = new ArrayList<>();
        requests.add(new CampSiteRequest("232451", "Hodgdon Meadows", "2021-07-01T00%3A00%3A00.000Z"));
        requests.add(new CampSiteRequest("232453", "Bridalveil", "2021-07-01T00%3A00%3A00.000Z"));
        requests.add(new CampSiteRequest("231959", "Plaskett Creek Campground near Big Sur", "2021-07-01T00%3A00%3A00.000Z"));
        requests.add(new CampSiteRequest("231958", "Arroyo Seco Campground near Big Sur", "2021-07-01T00%3A00%3A00.000Z"));

        requests.add(new CampSiteRequest("232451", "Hodgdon Meadows", "2021-08-01T00%3A00%3A00.000Z"));
        requests.add(new CampSiteRequest("232453", "Bridalveil", "2021-08-01T00%3A00%3A00.000Z"));
        requests.add(new CampSiteRequest("231959", "Plaskett Creek Campground near Big Sur", "2021-08-01T00%3A00%3A00.000Z"));
        requests.add(new CampSiteRequest("231958", "Arroyo Seco Campground near Big Sur", "2021-08-01T00%3A00%3A00.000Z"));

        return requests;
    }

    public void check() {
        System.out.println("Started");
        StringBuilder emailMessage = new StringBuilder();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        HttpClientHelper<CampsiteAvailability> httpClientHelper = new HttpClientHelper<>();

        //https://www.recreation.gov/api/camps/availability/campground/232452/month?start_date=2021-07-01T00%3A00%3A00.000Z
        String campAvailabilityUrl = "https://www.recreation.gov/api/camps/availability/campground/CAMPID/month?start_date=CAMPMONTH";

        List<CampSiteRequest> camps = getCamps();
        camps.stream().forEach(camp -> {
            AtomicLong availableCount = new AtomicLong();
            StringBuilder message = new StringBuilder("Campsite is available at camp " + camp.name + "\n");
            message.append("<a href=\"https://www.recreation.gov/camping/campgrounds/"+camp.id+"/availability\">Click here </a>\n");

            String date = camp.month;

            String url = campAvailabilityUrl.replace("CAMPID", camp.id).replace("CAMPMONTH", date);
            CampsiteAvailability campsiteAvailability = httpClientHelper.get(url, CampsiteAvailability.class);

            if (campsiteAvailability != null) {
                Map<String, Campsite> campsites = campsiteAvailability.getCampsites();

                if (campsites != null) {
                    campsites.values().stream().forEach(cs -> {
                        if (cs.getMax_num_people() < 10) {
                            return;
                        }

                        Map<String, String> availabilities = cs.getAvailabilities();
                        Map<String, String> availables = availabilities.entrySet()
                                .stream()
                                .filter(f -> f.getValue().equalsIgnoreCase("Available"))
                                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));

                        if (availables.values().size() > 1) {
                            boolean existsWeekEnds = availables.entrySet().stream().map(d -> {
                                try {
                                    Date parsedDate = simpleDateFormat.parse(d.getKey());
                                    if (parsedDate.getDay() == 5 || parsedDate.getDay() == 6) {
                                        return true;
                                    }
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                return false;
                            }).collect(Collectors.toSet()).contains(true);

                            if (existsWeekEnds) {
                                availableCount.getAndIncrement();

                                message.append("\t" + cs.getSite() + "\n");

                                availables.entrySet().stream().forEach(d -> {
                                    try {
                                        Date parsedDate = simpleDateFormat.parse(d.getKey());
//                                    System.out.println(simpleDateFormat.parse(d.getKey()) + "\t" + parsedDate.getDay());
                                        message.append("\t\t\t" + simpleDateFormat.parse(d.getKey()) + "\n");
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                });

                                message.append("\n");
                            }
                        }
                    });
                }
            }

            if (availableCount.get() > 0) {
                System.out.println(message.toString());
                emailMessage.append(message);
            }
        });

        if (emailMessage.length() > 0) {
//            emailHelper.sendEmail(fromEmail, toEmail, "Camp Reservation Available", emailMessage.toString());
        }

        System.out.println("Completed");
    }
}

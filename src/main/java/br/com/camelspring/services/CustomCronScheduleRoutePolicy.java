package br.com.camelspring.services;

import org.apache.camel.CamelContext;
import org.apache.camel.Route;
import org.apache.camel.routepolicy.quartz2.CronScheduledRoutePolicy;
import org.quartz.CronExpression;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

public class CustomCronScheduleRoutePolicy extends CronScheduledRoutePolicy {
    private CamelContext context;

    public CustomCronScheduleRoutePolicy(CamelContext context) {
        this.context = context;
    }

    @Override
    public void onInit(Route route) {
        try {
            LocalDateTime startDate = parseCronToLocalDateTime(super.getRouteStartTime());
            LocalDateTime stopDate = parseCronToLocalDateTime(super.getRouteStopTime());
            LocalDateTime now = LocalDateTime.now();

            if (!now.isBefore(startDate) && now.isBefore(stopDate)) {
                context.getRouteDefinition(route.getId()).setAutoStartup("true");
            } else {
                context.getRouteDefinition(route.getId()).setAutoStartup("false");
            }
            super.onInit(route);
        } catch (ParseException ex) {
            ex.printStackTrace();
            context.getRouteDefinition(route.getId()).setAutoStartup("false");
            super.onInit(route);
        }
    }

    private LocalDateTime parseCronToLocalDateTime(String cronExpressionString) throws ParseException {
        TimeZone timeZone = TimeZone.getTimeZone(super.getTimeZone());
        ZoneId zoneId = timeZone.toZoneId();
        Date date = Date.from(LocalDate.now().atStartOfDay(zoneId).toInstant());

        CronExpression cronExpression = new CronExpression(cronExpressionString);
        cronExpression.setTimeZone(timeZone);
        Date validDateAfter = cronExpression.getNextValidTimeAfter(date);
        return validDateAfter.toInstant().atZone(zoneId).toLocalDateTime();
    }
}

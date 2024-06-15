package com.finalyear.VolunteeringSystm.service.schedules;
import com.finalyear.VolunteeringSystm.model.OpenPosition;
import com.finalyear.VolunteeringSystm.repository.OpenPositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/*
THIS CLASS WILL HELP US TO UPDATE OPEN POSITION STATUS AT MID-NIGHT
 */
@Service
@RequiredArgsConstructor
public class PositionStatusUpdater {
    private final OpenPositionRepository openPositionRepository;

    @Scheduled(cron = "0 0 0 * * ?") // Runs daily at midnight
    public void updatePositionStatuses() {
        List<OpenPosition> positions = openPositionRepository.findAll();
        LocalDate today = LocalDate.now();

        for (OpenPosition position : positions) {
            if (today.isAfter(position.getFinishDate())) {
                position.setStatus(OpenPosition.PositionStatus.ENDED);
            } else if (!today.isBefore(position.getStartDate())) {
                position.setStatus(OpenPosition.PositionStatus.ONGOING);
            } else {
                position.setStatus(OpenPosition.PositionStatus.PENDING);
            }
            openPositionRepository.save(position);
        }
    }
}

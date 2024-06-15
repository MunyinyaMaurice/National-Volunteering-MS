//package com.finalyear.VolunteeringSystm.controller;
//
//public class OpenPositionController {
//}
package com.finalyear.VolunteeringSystm.controller;

        import com.finalyear.VolunteeringSystm.dto.OpenPositionDto;
        import com.finalyear.VolunteeringSystm.model.OpenPosition;
        import com.finalyear.VolunteeringSystm.service.serviceImpl.OpenPositionServiceImpl;
        import lombok.RequiredArgsConstructor;
        import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/positions")
public class OpenPositionController {

    private final OpenPositionServiceImpl openPositionServiceImpl;

    @PostMapping
    public OpenPosition createOpenPosition(@RequestParam Integer departmentId,
                                           @RequestBody OpenPositionDto openPositionDto
                                           ) {
        return openPositionServiceImpl.createPosition(departmentId,openPositionDto);
    }
}

//package com.finalyear.VolunteeringSystm.controller;
//
//public class OpenPositionController {
//}
package com.finalyear.VolunteeringSystm.controller;

        import com.finalyear.VolunteeringSystm.dto.OpenPositionDto;
        import com.finalyear.VolunteeringSystm.dto.OpenPositionInfoDto;
        import com.finalyear.VolunteeringSystm.model.OpenPosition;
        import com.finalyear.VolunteeringSystm.service.serviceImpl.OpenPositionServiceImpl;
        import lombok.RequiredArgsConstructor;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

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
    @GetMapping
    public List<OpenPositionInfoDto> getPendingOpenPositionsWithSkills() {
        return openPositionServiceImpl.getPendingOpenPositionsWithSkills();
    }
    @GetMapping("/{openPositionId}")
    public List<OpenPositionInfoDto> getPositionById( @PathVariable Integer openPositionId){
        return openPositionServiceImpl.getOPenPositionById(openPositionId);
    }
//    @GetMapping("/{openPositionId}")
//    public OpenPosition getPositionById( @PathVariable Integer openPositionId){
//        return openPositionServiceImpl.getOPenPositionById(openPositionId);
//    }
}

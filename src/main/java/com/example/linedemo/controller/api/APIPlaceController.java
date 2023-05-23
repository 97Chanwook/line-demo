package com.example.linedemo.controller.api;

import com.example.linedemo.constant.PlaceType;
import com.example.linedemo.dto.PlaceDto;
import com.example.linedemo.dto.response.APIDataResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class APIPlaceController {

    @GetMapping("/api")
    public APIDataResponse<List<PlaceDto>> getPlaces(){
        return null;
    }

    @PostMapping("/places")
    public Boolean createPlace(){
        return true;
    }

    @GetMapping("/places/{placeId}")
    public APIDataResponse<PlaceDto> getPlace(
            @PathVariable
            Integer placeId
    ){
        if(placeId.equals(2)){
            return APIDataResponse.of(null);
        }
        return APIDataResponse.of(PlaceDto.of(
                PlaceType.COMMON,
                "my home",
                "010-3308-9531",
                30,
                "asd"
        ));
    }

    @PutMapping("/places/{placeId}")
    public Boolean modifyPlace(
            @PathVariable
            Integer placeId
    ){
        return true;
    }

    @DeleteMapping("/places/{placeId}")
    public Boolean removePlace(
            @PathVariable
            Integer placeId
    ){
        return true;
    }

}

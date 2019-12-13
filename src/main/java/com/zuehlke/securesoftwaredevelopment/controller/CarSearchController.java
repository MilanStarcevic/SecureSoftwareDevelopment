package com.zuehlke.securesoftwaredevelopment.controller;

import com.zuehlke.securesoftwaredevelopment.domain.Car;
import com.zuehlke.securesoftwaredevelopment.repository.CarRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CarSearchController {
    private CarRepository carRepository;

    public CarSearchController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/car-search")
    public String showSearch() {
        return "car-search";
    }

    @GetMapping(value = "/cars/search", produces = "application/json")
    @ResponseBody
    public List<Car> search(@RequestParam("query") String query) {
        return carRepository.search(query);
    }
}

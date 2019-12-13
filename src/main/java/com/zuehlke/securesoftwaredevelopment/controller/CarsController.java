package com.zuehlke.securesoftwaredevelopment.controller;

import com.zuehlke.securesoftwaredevelopment.domain.Car;
import com.zuehlke.securesoftwaredevelopment.repository.CarRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CarsController {
    private CarRepository carRepository;

    public CarsController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/cars")
    public String showSearch() {
        return "cars";
    }

    @GetMapping(value = "/api/cars/search", produces = "application/json")
    @ResponseBody
    public List<Car> search(@RequestParam("query") String query) {
        return carRepository.search(query);
    }

    @GetMapping("/cars/{id}")
    public String showCar(@PathVariable("id") int id, Model model) {
        model.addAttribute("car", carRepository.findById(id));
        return "car";
    }

    @PostMapping("/cars/{id}")
    public String editCar(@PathVariable("id") int id, Car car) {
        carRepository.update(id, car);
        return "redirect:/cars/" + id;
    }
}

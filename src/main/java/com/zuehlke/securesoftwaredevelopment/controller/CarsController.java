package com.zuehlke.securesoftwaredevelopment.controller;

import com.zuehlke.securesoftwaredevelopment.domain.Car;
import com.zuehlke.securesoftwaredevelopment.domain.Comment;
import com.zuehlke.securesoftwaredevelopment.domain.Person;
import com.zuehlke.securesoftwaredevelopment.domain.ViewComment;
import com.zuehlke.securesoftwaredevelopment.repository.CarRepository;
import com.zuehlke.securesoftwaredevelopment.repository.CommentRepository;
import com.zuehlke.securesoftwaredevelopment.repository.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarsController {
    private CarRepository carRepository;
    private CommentRepository commentRepository;
    private PersonRepository userRepository;

    public CarsController(CarRepository carRepository, CommentRepository commentRepository, PersonRepository userRepository) {
        this.carRepository = carRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(value = {"/cars", "/"})
    public String showSearch(Model model) {
        model.addAttribute("cars", carRepository.getAll());
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
        List<Comment> comments = commentRepository.getAll(id);

        List<ViewComment> commentList = new ArrayList<>();

        for (Comment comment : comments) {
            Person person = userRepository.get(comment.getUserId());
            commentList.add(new ViewComment(person.getFirstName() + " " + person.getLastName(), comment.getComment()));
        }

        model.addAttribute("comments", commentList);

        return "car";
    }

    @PostMapping("/cars/{id}")
    public String editCar(@PathVariable("id") int id, Car car) {
        carRepository.update(id, car);
        return "redirect:/cars/" + id;
    }
}

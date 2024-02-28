package com.operazionicrud.operazionicrud.controllers;

import com.operazionicrud.operazionicrud.entities.Car;
import com.operazionicrud.operazionicrud.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    //crea nuova Car
    @PostMapping
    public Car createCar(@RequestBody Car car) {
        Car savedCar = carRepository.save(car);
        return savedCar;
    }

    //restituisce la lista di tutte le Car
    @GetMapping
    public List<Car> getAllCars() {
        List<Car> cars = (List<Car>) carRepository.findAll();
        return cars;
    }

    //restituisce una singola Car - se id non è presente in db (usa existsById()), restituisce Car vuota
    @GetMapping("/{id}")
    public Optional<Car> getCar(@PathVariable long id) {
        Optional<Car> car = carRepository.findById(id);
        return car;
    }

    //aggiorna type della Car specifica, identificata da id e passando query param - se id non è presente in db (usa existsById()), restituisce Car vuota
    @PutMapping("/{id}")
    public Car updateCarType(@PathVariable Long id, @RequestParam Car car) {
    car.setType(car.getType());
    Car newCar = carRepository.save(car);
    return newCar;
}

    //cancella la Car specifica - se non presente, la risposta deve avere Conflict HTTP status
    @DeleteMapping("/{id}")
    public void deleteSingleCar(@PathVariable long id){
        carRepository.deleteById(id);
    }

    //cancella tutte le Cars in db
    @DeleteMapping("/deleteAll")
    public void deleteAll(@RequestParam List<Long> ids){
        carRepository.deleteAllById(ids);
    }
}

/**
 * 
 */
package br.codespace.restapi.actions;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.codespace.restapi.domain.Car;
import br.codespace.restapi.repo.CarRepository;
/**
 * @author gilmar
 *
 */
@RestController
@RequestMapping("/api")
public class HomeController {
	private CarRepository carRepository;
	
	@GetMapping(path="/cars")
	public @ResponseBody Iterable<Car> getAllCars() {
		return carRepository.findAll();
	}
	
	@GetMapping(path="/cars/{id}")
	public Car getCar(@PathVariable Long id) {
		return carRepository.findById(id).get();
	}
		
	@PostMapping("/cars")
	public Car createCar(@Valid @RequestBody Car car) {
		return carRepository.save(car);
	}
	
	@PutMapping("/cars/{id}")
	public Car updateCar(@PathVariable(value = "id") Long id,
            @Valid @RequestBody Car carDetails) {
		Car car = carRepository.findById(id).get();
		car.setName(carDetails.getName());
		car.setFuelType(carDetails.getFuelType());
		car.setModel(carDetails.getModel());
		return carRepository.save(car);
	}
	
	@DeleteMapping("/cars/{id}")
	public ResponseEntity<?> deleteCar(@PathVariable Long id) {
	    Car car = carRepository.findById(id).get();
	    carRepository.delete(car);
	    return ResponseEntity.ok().build();
	}
}

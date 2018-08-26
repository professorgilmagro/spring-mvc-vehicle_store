/**
 * 
 */
package br.codespace.restapi.actions;

import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.codespace.restapi.domain.Car;
import br.codespace.restapi.repo.CarRepository;

/**
 * Controlador responsável pela api de acesso/manutenção de carros
 * 
 * @author Gilmar Soares <professorgilmagro@gmail.com>
 * @category api
 * @version 1.0
 */
@RestController
@RequestMapping("/api")
public class HomeController {
	
	@Autowired
	private CarRepository carRepository;
	
	/**
	 * Retorna todos os registros de carros existentes na base (Paginados)
	 * <pre>
	 * 		GET localhost:8080/api/cars
	 * </pre>
	 * @return Coleção de carros
	 */
	@GetMapping(path="/cars")
	public @ResponseBody Iterable<Car> getAllCars() {
		Iterable<Car> cars = carRepository.findAll();
		return cars;
	}
	
	@GetMapping(path="/cars/{id}")
	public ResponseEntity<Car> getCar(@PathVariable Long id) {
		try {
			Optional<Car> car = carRepository.findById(id);
			if (car.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(car.get());
			}
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/cars")
	public Car createCar(@Valid @RequestBody Car car) {
		return carRepository.save(car);
	}
	
	@PutMapping("/cars/{id}")
	public Car updateCar(@PathVariable(value = "id") Long id,
            @Valid @RequestBody Car carDetails) {
		Car car = carRepository.findById(id).get();
		BeanUtils.copyProperties(carDetails, car);
		return carRepository.save(car);
	}
	
	@DeleteMapping("/cars/{id}")
	public ResponseEntity<?> deleteCar(@PathVariable Long id) {
	    Car car = carRepository.findById(id).get();
	    carRepository.delete(car);
	    return ResponseEntity.ok().build();
	}
}

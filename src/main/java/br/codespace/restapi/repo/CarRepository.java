package br.codespace.restapi.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.codespace.restapi.domain.Car;

@RepositoryRestResource(path="carro", collectionResourceRel = "cars" )
public interface CarRepository extends PagingAndSortingRepository<Car, Long> {

}

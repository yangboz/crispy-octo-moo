package info.smartkit.crispy_octo_moo.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import info.smartkit.crispy_octo_moo.vo.Person;

/**
 * The Interface PersonRepository.
 */
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, String> {

    Person findByName(String name);

    Iterable<Person> findByTeammatesName(String name);

}
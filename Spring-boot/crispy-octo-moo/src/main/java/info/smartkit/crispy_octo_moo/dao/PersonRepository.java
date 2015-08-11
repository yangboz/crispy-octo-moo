package info.smartkit.crispy_octo_moo.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import info.smartkit.crispy_octo_moo.vo.Person;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

    // TODO: Replace with @Param("name") when Spring Data Neo4j supports names vs. positional arguments
    List<Person> findByLastName(@Param("0") String name);

}
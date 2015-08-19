package info.smartkit.crispy_octo_moo.dao;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import info.smartkit.crispy_octo_moo.domain.User;

@Repository
public interface UserRepository extends GraphRepository<User> {
}

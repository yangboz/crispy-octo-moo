package crispy_octo_moo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import crispy_octo_moo.domain.Snap415UserPosts;


public interface Snap415UserPostsRepository extends MongoRepository<Snap415UserPosts, String> {

    Snap415UserPosts findBySnap415ID(String Snap415ID);
}

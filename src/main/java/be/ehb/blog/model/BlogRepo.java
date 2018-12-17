package be.ehb.blog.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BlogRepo extends CrudRepository<BlogPost, Integer> {

    @Query("SELECT b FROM BlogPost b ORDER BY b.dateCreated DESC ")
    List<BlogPost> findAllChronological();

    //of alternatief
   // List<BlogPost> findAllOrOrderByDateCreated();

}

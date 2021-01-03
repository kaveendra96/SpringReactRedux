package ucsc.cmb.ac.lk.projectPlaner.repositories;

import org.springframework.data.repository.CrudRepository;
import ucsc.cmb.ac.lk.projectPlaner.domain.Backlog;

public interface BacklogRepository extends CrudRepository<Backlog,Long> {
    Backlog findByProjectIdentifier(String id);
}

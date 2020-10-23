package ucsc.cmb.ac.lk.projectPlaner.repositories;

import org.springframework.data.repository.CrudRepository;
import ucsc.cmb.ac.lk.projectPlaner.domain.Project;

public interface ProjectRepository extends CrudRepository<Project,Long> {
    @Override
    Iterable<Project> findAllById(Iterable<Long> iterable);
}

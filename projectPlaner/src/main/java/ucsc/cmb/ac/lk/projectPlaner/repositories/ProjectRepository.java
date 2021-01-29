package ucsc.cmb.ac.lk.projectPlaner.repositories;

import org.springframework.data.repository.CrudRepository;
import ucsc.cmb.ac.lk.projectPlaner.domain.Project;

public interface ProjectRepository extends CrudRepository<Project,Long> {

    Project findByProjectIdentifier(String projectId);

    @Override
    Iterable<Project> findAll();
    Iterable<Project> findAllByProjectLeader(String username);
}

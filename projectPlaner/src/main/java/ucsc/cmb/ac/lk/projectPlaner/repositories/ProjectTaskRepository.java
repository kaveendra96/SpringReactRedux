package ucsc.cmb.ac.lk.projectPlaner.repositories;

import org.springframework.data.repository.CrudRepository;
import ucsc.cmb.ac.lk.projectPlaner.domain.ProjectTask;

import java.util.List;

public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {

    List<ProjectTask> findByProjectIdentifierOrderByPriority(String id);

    ProjectTask findByProjectSequence(String sequence);
}

package ucsc.cmb.ac.lk.projectPlaner.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucsc.cmb.ac.lk.projectPlaner.domain.Project;
import ucsc.cmb.ac.lk.projectPlaner.repositories.ProjectRepository;


@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    public Project saveOrUpdateProject(Project project){
        return projectRepository.save(project);
    }
}

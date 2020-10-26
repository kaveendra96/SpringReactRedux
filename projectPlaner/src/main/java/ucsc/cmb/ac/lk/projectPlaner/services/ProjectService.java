package ucsc.cmb.ac.lk.projectPlaner.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucsc.cmb.ac.lk.projectPlaner.domain.Project;
import ucsc.cmb.ac.lk.projectPlaner.exceptions.ProjectIdException;
import ucsc.cmb.ac.lk.projectPlaner.repositories.ProjectRepository;


@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;


    public Project saveOrUpdateProject(Project project){
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }
        catch (Exception e){
            throw new ProjectIdException("Project Id :" + project.getProjectIdentifier().toUpperCase()+ "  already Exists");
        }
    }
    public Project findProjectByIdentifer(String projectId){
        Project project= projectRepository.findByProjectIdentifier(projectId);
        if(project == null)
            throw new ProjectIdException("Project Id :" + projectId.toUpperCase()+ "  does not Exists");

        return project;
    }
    public Iterable<Project> findAllProject(){
        return projectRepository.findAll();
    }
    public void deleteProjectByIdentifier(String projectId){
        Project project=projectRepository.findByProjectIdentifier(projectId);
        if(project ==null){
            throw new ProjectIdException("cannot find project with id: "+projectId+" this project does not exist");
        }
        projectRepository.delete(project);
    }

}

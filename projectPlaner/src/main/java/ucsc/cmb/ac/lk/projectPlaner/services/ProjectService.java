package ucsc.cmb.ac.lk.projectPlaner.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucsc.cmb.ac.lk.projectPlaner.domain.Backlog;
import ucsc.cmb.ac.lk.projectPlaner.domain.Project;
import ucsc.cmb.ac.lk.projectPlaner.domain.User;
import ucsc.cmb.ac.lk.projectPlaner.exceptions.ProjectIdException;
import ucsc.cmb.ac.lk.projectPlaner.exceptions.ProjectNotFoundException;
import ucsc.cmb.ac.lk.projectPlaner.repositories.BacklogRepository;
import ucsc.cmb.ac.lk.projectPlaner.repositories.ProjectRepository;
import ucsc.cmb.ac.lk.projectPlaner.repositories.UserRepository;


@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private UserRepository userRepository;

    public Project saveOrUpdateProject(Project project,String username) {

        if (project.getId() != null) {
            Project exsitingProject = projectRepository.findByProjectIdentifier(project.getProjectIdentifier());
            if (exsitingProject !=null && (!exsitingProject.getProjectLeader().equals(username))) {
                throw new ProjectNotFoundException("Project not found in your account");
            } else if (exsitingProject==null) {
                throw new ProjectNotFoundException("Project with ID: "+project.getProjectIdentifier()+" cannot be updated beacuse it doesn`t exist");
            }
        }

        try {

            User user = userRepository.findByUsername(username);
            project.setUser(user);
            project.setProjectLeader(user.getUsername());
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

            if (project.getId() == null) {
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            }

            if (project.getId() != null) {
                project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
            }

            return projectRepository.save(project);

        } catch (Exception e) {
            throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
        }

    }


    public Project findProjectByIdentifier(String projectId,String username) {

        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if (project == null) {
            throw new ProjectIdException("Project ID '" + projectId + "' does not exist");
        }
        if (project.getProjectLeader().equals(username)){
            throw new ProjectNotFoundException("Project Not Found in your account");
        }


        return project;
    }

    public Iterable<Project> findAllProjects(String username ) {
        return projectRepository.findAllByProjectLeader(username);
    }


    public void deleteProjectByIdentifier(String projectid,String username) {
        projectRepository.delete(findProjectByIdentifier(projectid,username));
    }

}
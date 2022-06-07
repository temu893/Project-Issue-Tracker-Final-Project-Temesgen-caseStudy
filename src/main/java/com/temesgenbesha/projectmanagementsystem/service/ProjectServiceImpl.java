package com.temesgenbesha.projectmanagementsystem.service;

import com.temesgenbesha.projectmanagementsystem.dto.ProjectDTO;
import com.temesgenbesha.projectmanagementsystem.entity.Project;
import com.temesgenbesha.projectmanagementsystem.entity.User;
import com.temesgenbesha.projectmanagementsystem.exception.ProjectNotFoundException;
import com.temesgenbesha.projectmanagementsystem.repository.ProjectRepository;
import com.temesgenbesha.projectmanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;



//for all service class we have to use @service annotation otherWise spring will not recognize this class as a service layer
//@RequiredArgsConstructor we have to annotate this since we are using lombok

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    private final UserRepository userRepository;

    private final ProjectRepository projectRepository;

    @Override
    public List<ProjectDTO> getAllProjects() {
        //return projectRepository.findAll(); // this will return all project in the list
        return projectRepository.findAll().stream().map(Project::toDTO).collect(Collectors.toList());
    }

    @Override
    public ProjectDTO getProjectById(Long id) throws Exception {
//        Optional<ProjectDto> projectOptional = projectRepository.findById(id);
//        if(projectOptional.isEmpty()) throw new Exception();
//        return projectOptional.get();

//
        Project project = projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
        return project.toDTO();
    }

    @Override
    public ProjectDTO addProject(ProjectDTO projectDTO) {
        User admin = userRepository.findByUsername("temub").orElseThrow(() -> new UsernameNotFoundException("User with username temub not found!"));



        Project project = projectDTO.toEntity();
        project.setCreatedBy(admin);
        project.setCreatedOn(LocalDateTime.now());


        project = projectRepository.save(project);

        //loging will help what is goin on in each step of this application // and for troubleshooting
        log.info("Created new project: {}", project);

        return project.toDTO();
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);

    }

    @Override
    public ProjectDTO updateProject(Long id, ProjectDTO projectDTO) throws ProjectNotFoundException {
        User admin = userRepository.findByUsername("temub").orElseThrow(() -> new UsernameNotFoundException("User with username temub not found!"));

        Project project = projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));

        project.setName(projectDTO.getName());
        project.setProjectDescription(projectDTO.getProjectDescription());
        project.setActualEndDate(projectDTO.getActualEndDate());
        // TODO: user current user;
        project.setModifiedBy(admin);
        project.setModifiedOn(LocalDateTime.now());

        project = projectRepository.save(project);
        log.info("Project updated: {}", project);

        return project.toDTO();
    }


}

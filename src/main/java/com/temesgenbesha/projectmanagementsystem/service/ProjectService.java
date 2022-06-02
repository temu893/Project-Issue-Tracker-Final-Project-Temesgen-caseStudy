package com.temesgenbesha.projectmanagementsystem.service;

import com.temesgenbesha.projectmanagementsystem.dto.ProjectDTO;

import java.util.List;

public interface ProjectService {
    List<ProjectDTO> getAllProjects();

    ProjectDTO getProjectById(Long id) throws Exception;

    ProjectDTO addProject(ProjectDTO projectDTO);

    void deleteProject(Long id);
}

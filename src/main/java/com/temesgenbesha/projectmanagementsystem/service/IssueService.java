package com.temesgenbesha.projectmanagementsystem.service;

import com.temesgenbesha.projectmanagementsystem.dto.IssueDTO;
import com.temesgenbesha.projectmanagementsystem.exception.ProjectNotFoundException;

import java.util.List;

public interface IssueService {
    List<IssueDTO> getAllIssues();

    List<IssueDTO> getIssuesFromProject(Long projectId) throws ProjectNotFoundException;

    List<IssueDTO> getIssueById(long id) throws Exception;




    IssueDTO addIssue(IssueDTO issueDTO) throws Exception;

    void deleteIssue(Long id);
}

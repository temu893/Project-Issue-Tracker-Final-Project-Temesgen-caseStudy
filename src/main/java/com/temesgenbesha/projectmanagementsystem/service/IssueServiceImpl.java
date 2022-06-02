package com.temesgenbesha.projectmanagementsystem.service;

import com.temesgenbesha.projectmanagementsystem.dto.IssueDTO;
import com.temesgenbesha.projectmanagementsystem.entity.*;
import com.temesgenbesha.projectmanagementsystem.exception.IssueNotFoundException;
import com.temesgenbesha.projectmanagementsystem.exception.ProjectNotFoundException;
import com.temesgenbesha.projectmanagementsystem.repository.IssueRepository;
import com.temesgenbesha.projectmanagementsystem.repository.ProjectRepository;
import com.temesgenbesha.projectmanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
@Slf4j
public class IssueServiceImpl implements IssueService {

   private final UserRepository userRepository;

    private final IssueRepository issueRepository;
    private final ProjectRepository projectRepository;


    @Override
    public List<IssueDTO> getAllIssues() {
        return issueRepository.findAll().stream().map(Issue::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<IssueDTO> getIssuesFromProject(Long projectId) throws ProjectNotFoundException {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        return issueRepository.findAllByProject(project).stream().map(Issue::toDTO).collect(Collectors.toList());
    }

    @Override
    public IssueDTO getIssueById(long id) throws Exception {
        Issue issue = issueRepository.findById(id).orElseThrow(() -> new IssueNotFoundException(id));
        return issue.toDTO();
    }

    @Override
    public IssueDTO addIssue(IssueDTO issueDTO) {
        User admin = userRepository.findByUsername("temub").orElseThrow(() -> new UsernameNotFoundException("User with username temub not found!"));
        User user1 = userRepository.findByUsername("mm").orElseThrow(() -> new UsernameNotFoundException("User with username mm not found!"));

        Issue issue = issueDTO.toEntity();
        issue.getId();
        issue.setSummary("this is a summery for this Issue");
        issue.setDescription("this is description for the issue");
        issue.setCreatedBy(admin);
        issue.getCreatedOn();
        issue.setAssignedTo(user1);
        issue.setAssignedOn(LocalDate.now());
        issue.setStatus(Status.OPEN);
        issue.setPriority(Priority.LOW);
        issue.getTargetResolutionDate();
        issue.setResolutionSummary("this is resolution summery");

        issue = issueRepository.save(issue);
        log.info("Created new issue ", issue);
        return issue.toDTO();

    }
}

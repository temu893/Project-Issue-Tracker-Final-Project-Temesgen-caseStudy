package com.temesgenbesha.projectmanagementsystem.dto;

import com.temesgenbesha.projectmanagementsystem.entity.Issue;
import com.temesgenbesha.projectmanagementsystem.entity.Priority;
import com.temesgenbesha.projectmanagementsystem.entity.Status;
import lombok.Data;

import java.time.LocalDate;

@Data
public class IssueDTO {
    private Long id;
    private String summary;
    private String description;
    private UserDTO createdBy;
    private LocalDate createdOn;
    private UserDTO assignedTo;
    private LocalDate assignedOn;
    private Status status;
    private Priority priority;
    private LocalDate targetResolutionDate;
    private String resolutionSummary;
    private ProjectDTO project;

    // The Mapper implemented inside the DTO
    // This method will return Issue entity
    public Issue toEntity() {
        Issue issue = new Issue();
        issue.setId(id);
        issue.setSummary(summary);
        issue.setDescription(description);
        if (createdBy != null) {
            issue.setCreatedBy(createdBy.toEntity());
        }
        issue.setCreatedOn(createdOn);
        if (assignedTo != null) {
            issue.setAssignedTo(assignedTo.toEntity());
        }
        issue.setStatus(status);
        issue.setPriority(priority);
        setTargetResolutionDate(targetResolutionDate);
        setResolutionSummary(resolutionSummary);
        if (priority != null) {
            issue.setProject(project.toEntity());
        }

        return issue;
    }
}

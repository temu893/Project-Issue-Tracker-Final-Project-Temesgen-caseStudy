package com.temesgenbesha.projectmanagementsystem.dto;

import com.temesgenbesha.projectmanagementsystem.entity.Issue;
import com.temesgenbesha.projectmanagementsystem.entity.Priority;
import com.temesgenbesha.projectmanagementsystem.entity.Status;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
//@Transient
public class IssueDTO {
    private Long id;
    private String summary;
    private String description;
    private UserDTO createdBy;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)

    private LocalDateTime createdOn;
    private UserDTO assignedTo;
    // New Transient attribute to bind assignedToId from select component

//    private String assignedToId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)

    private LocalDateTime assignedOn;
    private Status status;
    private Priority priority;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)

    private LocalDateTime targetResolutionDate;
    private String resolutionSummary;
    private ProjectDTO project;

    // The Mapper implemented inside the DTO
    // This method will return Issue entity
    public Issue toEntity() {
        Issue issue = new Issue();
        issue.setId(id);
        issue.setSummary(summary);
        issue.setDescription(description);
//        issue.setAssignedToId(assignedToId);
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

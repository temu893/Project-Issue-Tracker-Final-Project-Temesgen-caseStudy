package com.temesgenbesha.projectmanagementsystem.entity;

import com.temesgenbesha.projectmanagementsystem.dto.IssueDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String summary;
    private String description;

    @OneToOne
    private User createdBy ;
    private LocalDateTime createdOn;

    @OneToOne
    private User assignedTo ;
//    private String assignedToId;

    private LocalDateTime assignedOn;
    private Status status;
    private Priority priority;
    private LocalDateTime targetResolutionDate;
    private String resolutionSummary;

    @OneToOne
    private User modifiedBy ;
    private LocalDateTime modifiedOn;

    @ManyToOne
    private Project project;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Issue issue = (Issue) o;
        return id != null && Objects.equals(id, issue.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


    //DTO values
    public IssueDTO toDTO() {
        IssueDTO issueDTO = new IssueDTO();
        issueDTO.setId(id);
        issueDTO.setSummary(summary);
//        issueDTO.getAssignedToId();
        issueDTO.setDescription(description);
        if (createdBy != null) {
            issueDTO.setCreatedBy(createdBy.toDTO());
        }

        issueDTO.setCreatedOn(createdOn);
        if (assignedOn != null) {
            issueDTO.setAssignedTo(assignedTo.toDTO());
        }
        issueDTO.setAssignedOn(assignedOn);
        issueDTO.setStatus(status);
        issueDTO.setPriority(priority);
        issueDTO.setTargetResolutionDate(targetResolutionDate);
        issueDTO.setResolutionSummary(resolutionSummary);
        if (project != null) {
            issueDTO.setProject(project.toDTO());
        }

        return issueDTO;
    }
}

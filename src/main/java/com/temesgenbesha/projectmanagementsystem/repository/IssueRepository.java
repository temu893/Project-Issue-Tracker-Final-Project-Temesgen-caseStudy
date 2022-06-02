package com.temesgenbesha.projectmanagementsystem.repository;

import com.temesgenbesha.projectmanagementsystem.entity.Issue;
import com.temesgenbesha.projectmanagementsystem.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findAllByProject(Project project);
}

package com.temesgenbesha.projectmanagementsystem.controller;

import com.temesgenbesha.projectmanagementsystem.dto.IssueDTO;
import com.temesgenbesha.projectmanagementsystem.dto.ProjectDTO;
import com.temesgenbesha.projectmanagementsystem.entity.Status;
import com.temesgenbesha.projectmanagementsystem.repository.ProjectRepository;
import com.temesgenbesha.projectmanagementsystem.service.IssueService;
import com.temesgenbesha.projectmanagementsystem.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
@Slf4j
@Controller
public class ProjectController {

    private final ProjectService projectService;
    private final IssueService issueService;

    @Autowired
    private ProjectRepository pRepo;
    //Adding new project
    //FIXME not redirecting to "/api/Project page properly
    @PostMapping(value = "/api/project")
    public ResponseEntity<Object> createNewProject(@ModelAttribute("project") ProjectDTO projectDTO) throws URISyntaxException {
        ProjectDTO createdProject = projectService.addProject(projectDTO);
        return  ResponseEntity.created(new URI("/api/project/" + createdProject.getId() )).build() ;
        //dss
    }

//    @PostMapping("/sageroject")
//    public String saveNewProject(@ModelAttribute Project project) {
//        pRepo.save(project);
//        return "redirect:/api/project";
//    }

    @RequestMapping(value =  "/api/issue")
    public String displayEnums(Model model){
        model.addAttribute("status", Status.values());

        return "issue/new" ;

    }

    @PostMapping(value = "/api/issue")
    public ResponseEntity<IssueDTO> createNewIssue(@ModelAttribute("issue") IssueDTO issueDTO) throws URISyntaxException{
        IssueDTO createdIssue = issueService.addIssue(issueDTO);
        return ResponseEntity.created(new URI("/api/issue/" + createdIssue.getId())).build();
    }

    @InitBinder
    public void initBinder( WebDataBinder binder )
    {
        binder.registerCustomEditor( LocalDate.class, new PropertyEditorSupport()
        {
            @Override
            public void setAsText( String text ) throws IllegalArgumentException
            {
                LocalDate.parse( text.replaceAll("-", ""), DateTimeFormatter.ofPattern("yyyyMMdd") );
            }
        } );
    }
}

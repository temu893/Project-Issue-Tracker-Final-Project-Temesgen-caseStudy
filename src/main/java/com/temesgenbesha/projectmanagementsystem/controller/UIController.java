package com.temesgenbesha.projectmanagementsystem.controller;

import com.temesgenbesha.projectmanagementsystem.dto.IssueDTO;
import com.temesgenbesha.projectmanagementsystem.dto.ProjectDTO;
import com.temesgenbesha.projectmanagementsystem.entity.Project;
import com.temesgenbesha.projectmanagementsystem.exception.ProjectNotFoundException;
import com.temesgenbesha.projectmanagementsystem.repository.ProjectRepository;
import com.temesgenbesha.projectmanagementsystem.service.IssueService;
import com.temesgenbesha.projectmanagementsystem.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class UIController {
    @Autowired
    private ProjectRepository pRepo;

    private final ProjectService projectService;
    private final IssueService issueService;


    //PROJECT CONTROLLER
    @GetMapping({"/api/project", "/"})
    public String getProjectOverviewPage(Model model) {
        //returning list of project
        model.addAttribute("projects", projectService.getAllProjects());

        return "projectOverview";
    }

    //return single project by the id
    @GetMapping("/project/{id}")
    public String getProjectDetailPage(Model model, @PathVariable Long id) throws Exception {
        model.addAttribute("project", projectService.getProjectById(id));

        return "projectDetail";
    }
    @GetMapping("/showUpdateProject")
    public ModelAndView showProjectUpdate(@RequestParam Long id) throws Exception {
        ModelAndView update = new ModelAndView("add-project-form");
        Project project = pRepo.findById(id).get();
        update.addObject("project",project);
        return update ;
    }
    @DeleteMapping(path = "/project/{id}")
    public  String deleteProject(@PathVariable("/project/{id}") Long id){
        projectService.deleteProject(id);
        return "redirect:/api/project";

    }

    @GetMapping("/project/{id}/issue")
    public String getIssueFromSpecificProject(Model model, @PathVariable Long id) {
        try {
            model.addAttribute("issues", issueService.getIssuesFromProject(id));
        } catch (ProjectNotFoundException e) {
            return "redirect:/project/" + id + "/issue?error";
        }

        return "issueOverview";
    }

    @GetMapping("/project/new")
    public String createNewProject(Model model) {
        model.addAttribute("project", new ProjectDTO());

        return "createNewProject";
    }

    @GetMapping("/issue/new")
    public String createNewIssue(Model model) {
        model.addAttribute("issue", new IssueDTO());

        return "createNewIssue";
    }




//    @GetMapping
//    public String getIssueDetailPage(Model model,@PathVariable Long id) throws Exception{
//        model.addAttribute("issue",issueService.getIssueById(id));
//        return "issueDetail";
//
//    }

}

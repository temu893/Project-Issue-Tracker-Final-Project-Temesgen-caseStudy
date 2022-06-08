package com.temesgenbesha.projectmanagementsystem.controller;

import com.temesgenbesha.projectmanagementsystem.dto.IssueDTO;
import com.temesgenbesha.projectmanagementsystem.dto.ProjectDTO;
import com.temesgenbesha.projectmanagementsystem.exception.ProjectNotFoundException;
import com.temesgenbesha.projectmanagementsystem.service.IssueService;
import com.temesgenbesha.projectmanagementsystem.service.ProjectService;
import com.temesgenbesha.projectmanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UIController {


    private final ProjectService projectService;
    private final IssueService issueService;

    private final UserService userService;


    //PROJECT CONTROLLER
    @GetMapping
    public String index() {
        return "redirect:/overview";
    }
    @GetMapping("/overview")
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

    @GetMapping("/project/{id}/update")
    public String showUpdateProject(Model model, @PathVariable Long id) throws Exception {
        model.addAttribute("project", projectService.getProjectById(id));
        return "updateProject";
    }



    @GetMapping("/project/new")
    public String createNewProject(Model model) {
        model.addAttribute("project", new ProjectDTO());

        return "createNewProject";
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
    //return single issue by the id
//    @GetMapping("/project/{id}")
//    public String getIssueDetailPage(Model model, @PathVariable Long id) throws Exception {
//        model.addAttribute("issue", issueService.getIssueById(id));
//
//        return "issueDetail";
//    }
    @GetMapping("/issue/new")
    public String createNewIssue(Model model) {
        model.addAttribute("issue", new IssueDTO());
        model.addAttribute("users",userService.getAllUsers());

        return "createNewIssue";
    }






//    @GetMapping
//    public String getIssueDetailPage(Model model,@PathVariable Long id) throws Exception{
//        model.addAttribute("issue",issueService.getIssueById(id));
//        return "issueDetail";
//
//    }

}

package com.ukrposhta.inject;

import com.ukrposhta.model.Manager;
import com.ukrposhta.model.Programmer;
import com.ukrposhta.model.Project;
import com.ukrposhta.model.Technology;
import com.ukrposhta.model.parent.Employee;
import com.ukrposhta.service.ManagerService;
import com.ukrposhta.service.ProgrammerService;
import com.ukrposhta.service.ProjectService;
import com.ukrposhta.service.TechnologyService;
import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class InjectController {
    private final ProgrammerService programmerService;
    private final ManagerService managerService;
    private final TechnologyService technologyService;
    private final ProjectService projectService;

    @PostConstruct
    public void inject() {
        List<Technology> technologies = generateTechnologies();
        technologies.forEach(technologyService::save);
        List<Programmer> programmers = generateProgrammers();
        programmers.forEach(programmer -> {
            for (int i = 0; i < 4; i++) {
                programmer.addTechnology(technologies.get(new Random().nextInt(9)));
            }
            programmerService.save(programmer);
        });
        List<Manager> managers = generateManagers();
        managers.forEach(managerService::save);
        List<Project> projects = generateProjects();
        projects.forEach(project -> {
            for (int i = 0; i < 4; i++) {
                project.addProgrammer(programmers.get(new Random().nextInt(4)));
            }
            for (int i = 0; i < 4; i++) {
                project.addManager(managers.get(new Random().nextInt(3)));
            }
            projectService.save(project);
        });
    }

    private List<Technology> generateTechnologies() {
        Technology java = new Technology();
        java.setName("Java");
        Technology python = new Technology();
        python.setName("Python");
        Technology javascript = new Technology();
        javascript.setName("JavaScript");
        Technology sql = new Technology();
        sql.setName("SQL");
        Technology jira = new Technology();
        jira.setName("Jira");
        Technology rdb = new Technology();
        rdb.setName("RDB");
        Technology linux = new Technology();
        linux.setName("Linux");
        Technology cicd = new Technology();
        cicd.setName("CI/CD");
        Technology aws = new Technology();
        aws.setName("AWS");
        return Arrays.asList(java, python, javascript, sql,
                jira, rdb, linux, cicd, aws);
    }

    private List<Programmer> generateProgrammers() {
        Programmer ivan = new Programmer();
        ivan.setFullName("Ivanenko Ivan Ivanovych");
        ivan.setEmail("ivan90@ukr.net");
        ivan.setGender(Employee.Gender.MALE);
        ivan.setBirthDate(LocalDate.of(1990, 9, 19));
        ivan.setSalary(BigDecimal.valueOf(99000));
        ivan.setStatus(Employee.Status.WORKING);
        ivan.setLevel(Programmer.Level.SENIOR);
        ivan.setType(Programmer.Type.DEVELOPER);
        Programmer petro = new Programmer();
        petro.setFullName("Petrenko Petro Petrovych");
        petro.setEmail("petro96@gmail.com");
        petro.setGender(Employee.Gender.MALE);
        petro.setBirthDate(LocalDate.of(1996, 1, 11));
        petro.setSalary(BigDecimal.valueOf(60000));
        petro.setStatus(Employee.Status.WORKING);
        petro.setLevel(Programmer.Level.MIDDLE);
        petro.setType(Programmer.Type.DEVELOPER);
        Programmer kate = new Programmer();
        kate.setFullName("Sydorova Kateryna Petrivna");
        kate.setEmail("kate92@ukr.net");
        kate.setGender(Employee.Gender.FEMALE);
        kate.setBirthDate(LocalDate.of(1992, 2, 12));
        kate.setSalary(BigDecimal.valueOf(97000));
        kate.setStatus(Employee.Status.WORKING);
        kate.setLevel(Programmer.Level.SENIOR);
        kate.setType(Programmer.Type.QA);
        Programmer valentyna = new Programmer();
        valentyna.setFullName("Sydorenko Valentyna Ivanivna");
        valentyna.setEmail("valya99@gmail.com");
        valentyna.setGender(Employee.Gender.FEMALE);
        valentyna.setBirthDate(LocalDate.of(1999, 3, 13));
        valentyna.setSalary(BigDecimal.valueOf(30000));
        valentyna.setStatus(Employee.Status.WORKING);
        valentyna.setLevel(Programmer.Level.JUNIOR);
        valentyna.setType(Programmer.Type.DEV_OPS);
        return Arrays.asList(ivan, petro, kate, valentyna);
    }

    private List<Manager> generateManagers() {
        Manager zlatoslava = new Manager();
        zlatoslava.setFullName("Myronenko Zlatoslava Romanivna");
        zlatoslava.setEmail("zlatoslava@gmail.com");
        zlatoslava.setGender(Employee.Gender.FEMALE);
        zlatoslava.setBirthDate(LocalDate.of(1978, 8, 18));
        zlatoslava.setSalary(BigDecimal.valueOf(101000));
        zlatoslava.setStatus(Employee.Status.WORKING);
        zlatoslava.setCategory(Manager.Category.TOP);
        Manager oleg = new Manager();
        oleg.setFullName("Titov Oleg Ivanovych");
        oleg.setEmail("oleg85@gmail.com");
        oleg.setGender(Employee.Gender.MALE);
        oleg.setBirthDate(LocalDate.of(1985, 5, 15));
        oleg.setSalary(BigDecimal.valueOf(25000));
        oleg.setStatus(Employee.Status.WORKING);
        oleg.setCategory(Manager.Category.TECH);
        Manager lee = new Manager();
        lee.setFullName("Lee Choo");
        lee.setEmail("LeeChoo@ukr.net");
        lee.setGender(Employee.Gender.MALE);
        lee.setBirthDate(LocalDate.of(1990, 4, 14));
        lee.setSalary(BigDecimal.valueOf(50000));
        lee.setStatus(Employee.Status.WORKING);
        lee.setCategory(Manager.Category.MIDDLE);
        return Arrays.asList(zlatoslava, oleg, lee);
    }

    private List<Project> generateProjects() {
        Project button = new Project();
        button.setDescription("Add 'Favourite' button to the company`s site.");
        button.setDeadline(LocalDate.of(2024, 5, 7));
        button.setStatus(Project.Status.NEW);
        Project micro = new Project();
        micro.setDescription("Transition to microservice architecture.");
        micro.setDeadline(LocalDate.of(2024, 5, 8));
        micro.setStatus(Project.Status.NEW);
        Project base = new Project();
        base.setDescription("Knowledge base creation.");
        base.setDeadline(LocalDate.of(2023, 11, 7));
        base.setStatus(Project.Status.EXECUTION);
        return Arrays.asList(button, micro, base);
    }
}

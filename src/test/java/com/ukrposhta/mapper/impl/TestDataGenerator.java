package com.ukrposhta.mapper.impl;

import com.ukrposhta.dto.parent.EmployeeRequestDto;
import com.ukrposhta.dto.parent.EmployeeResponseDto;
import com.ukrposhta.dto.request.ProgrammerRequestDto;
import com.ukrposhta.dto.request.ManagerRequestDto;
import com.ukrposhta.dto.request.ProjectRequestDto;
import com.ukrposhta.dto.request.TechnologyRequestDto;
import com.ukrposhta.dto.responce.ProgrammerResponseDto;
import com.ukrposhta.dto.responce.ManagerResponseDto;
import com.ukrposhta.dto.responce.ProjectResponseDto;
import com.ukrposhta.dto.responce.TechnologyResponseDto;
import com.ukrposhta.model.Programmer;
import com.ukrposhta.model.Manager;
import com.ukrposhta.model.Project;
import com.ukrposhta.model.Technology;
import com.ukrposhta.model.parent.Employee;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TestDataGenerator {
    private static Employee employee;
    private static EmployeeRequestDto employeeRequestDto;
    private static EmployeeResponseDto employeeResponseDto;
    private static Programmer programmer;
    private static ProgrammerRequestDto programmerRequestDto;
    private static ProgrammerResponseDto programmerResponseDto;
    private static Manager manager;
    private static ManagerRequestDto managerRequestDto;
    private static ManagerResponseDto managerResponseDto;
    private static Technology technology;
    private static TechnologyRequestDto technologyRequestDto;
    private static TechnologyResponseDto technologyResponseDto;
    private static Project  project;
    private static ProjectRequestDto projectRequestDto;
    private static ProjectResponseDto projectResponseDto;

    public void generateEmployeeData() {
        employee = new Employee();
        employee.setFullName("Myronenko Zlatoslava Romanivna");
        employee.setEmail("zlatoslava@gmail.com");
        employee.setGender(Employee.Gender.FEMALE);
        employee.setBirthDate(LocalDate.of(1990, 1, 1));
        employee.setSalary(BigDecimal.valueOf(60000));
        employee.setStatus(Employee.Status.WORKING);
        employeeRequestDto = new EmployeeRequestDto();
        employeeRequestDto.setFullName("Myronenko Zlatoslava Romanivnaz");
        employeeRequestDto.setEmail("zlatoslava@gmail.com");
        employeeRequestDto.setGender("FEMALE");
        employeeRequestDto.setBirthDate(LocalDate.of(1990, 1, 1));
        employeeRequestDto.setSalary(BigDecimal.valueOf(60000));
        employeeRequestDto.setStatus("WORKING");
        employeeResponseDto = new EmployeeResponseDto();
        employeeResponseDto.setFullName("Myronenko Zlatoslava Romanivna");
        employeeResponseDto.setEmail("zlatoslava@gmail.com");
        employeeResponseDto.setGender("FEMALE");
        employeeResponseDto.setBirthDate(LocalDate.of(1990, 1, 1));
        employeeResponseDto.setSalary(BigDecimal.valueOf(60000));
        employeeResponseDto.setStatus("WORKING");
    }

    public void generateTechnologyData() {
        technology = new Technology();
        technology.setName("Java");
        technologyRequestDto = new TechnologyRequestDto();
        technologyRequestDto.setName("Java");
        technologyResponseDto = new TechnologyResponseDto();
        technologyResponseDto.setId(1L);
        technologyResponseDto.setName("Java");
    }

    public void generateProgrammerData() {
        programmer = new Programmer(employee);
        programmer.setLevel(Programmer.Level.SENIOR);
        programmer.setType(Programmer.Type.DEVELOPER);
        programmerRequestDto = new ProgrammerRequestDto();
        programmerRequestDto.setFullName("Ivanenko Ivan Ivanovych");
        programmerRequestDto.setEmail("ivanenko74@gmail.com");
        programmerRequestDto.setGender("MALE");
        programmerRequestDto.setBirthDate(LocalDate.of(1974, 6, 16));
        programmerRequestDto.setSalary(BigDecimal.valueOf(90000));
        programmerRequestDto.setStatus("WORKING");
        programmerRequestDto.setLevel("SENIOR");
        programmerRequestDto.setType("DEVELOPER");
        programmerResponseDto = new ProgrammerResponseDto(employeeResponseDto);
        programmerResponseDto.setLevel("SENIOR");
        programmerResponseDto.setType("DEVELOPER");
    }

    public void generateManagerData() {
        manager = new Manager(employee);
        manager.setCategory(Manager.Category.TOP);
        managerRequestDto = new ManagerRequestDto();
        managerRequestDto.setFullName("Petrenko Petro Petrovych");
        managerRequestDto.setEmail("petrenko95@gmail.com");
        managerRequestDto.setGender("MALE");
        managerRequestDto.setBirthDate(LocalDate.of(1995, 1, 1));
        managerRequestDto.setSalary(BigDecimal.valueOf(80000));
        managerRequestDto.setStatus("WORKING");
        managerRequestDto.setCategory("TOP");
        managerResponseDto = new ManagerResponseDto(employeeResponseDto);
        managerResponseDto.setCategory("TOP");
    }

    public void generateProjectData() {
        project = new Project();
        project.setDescription("Test project");
        project.setDeadline(LocalDate.of(2023, 6, 1));
        project.setStatus(Project.Status.EXECUTION);
        projectRequestDto = new ProjectRequestDto();
        projectRequestDto.setDescription("Test project");
        projectRequestDto.setDeadline(LocalDate.of(2023, 6, 1));
        projectResponseDto = new ProjectResponseDto();
        projectResponseDto.setDescription("Test project");
        projectResponseDto.setDeadline(LocalDate.of(2023, 6, 1));
        projectResponseDto.setStatus("EXECUTION");
    }

    public Employee getEmployee() {
        return employee;
    }

    public EmployeeRequestDto getEmployeeRequestDto() {
        return employeeRequestDto;
    }

    public EmployeeResponseDto getEmployeeResponseDto() {
        return employeeResponseDto;
    }

    public Programmer getProgrammer() {
        return programmer;
    }

    public ProgrammerRequestDto getProgrammerRequestDto() {
        return programmerRequestDto;
    }

    public ProgrammerResponseDto getProgrammerResponseDto() {
        return programmerResponseDto;
    }

    public Manager getManager() {
        return manager;
    }

    public ManagerRequestDto getManagerRequestDto() {
        return managerRequestDto;
    }

    public ManagerResponseDto getManagerResponseDto() {
        return managerResponseDto;
    }

    public Technology getTechnology() {
        return technology;
    }

    public TechnologyRequestDto getTechnologyRequestDto() {
        return technologyRequestDto;
    }

    public TechnologyResponseDto getTechnologyResponseDto() {
        return technologyResponseDto;
    }

    public Project getProject() {
        return project;
    }

    public ProjectRequestDto getProjectRequestDto() {
        return projectRequestDto;
    }

    public ProjectResponseDto getProjectResponseDto() {
        return projectResponseDto;
    }
}

package com.ukrposhta.model;

import com.ukrposhta.exception.InvalidValueException;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "projects")
@EqualsAndHashCode
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalDate deadline;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToMany
    @JoinTable(name = "projects_managers",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "manager_id"))
    private Set<Manager> managers = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "projects_programmers",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "programmer_id"))
    private Set<Programmer> programmers = new HashSet<>();

    public void addManager(Manager manager) {
        managers.add(manager);
    }

    public void addProgrammer(Programmer programmer) {
        programmers.add(programmer);
    }

    public void addAllManagers(List<Manager> managers) {
        managers.forEach(this::addManager);
    }

    public void addAllProgrammers(List<Programmer> programmers) {
        programmers.forEach(this::addProgrammer);
    }

    @PrePersist
    public void prePersist() {
        status = Status.NEW;
    }

    public enum Status {
        NEW("new"),
        EXECUTION("execution"),
        FINISHED("finished");

        private final String statusName;

        Status(String statusName) {
            this.statusName = statusName;
        }

        public static Status getStatus(String statusName) {
            for (Status status : Status.values()) {
                if (statusName.equals(status.statusName)) {
                    return status;
                }
            }
            throw new InvalidValueException("Unable to find status by status name." + statusName);
        }
    }
}

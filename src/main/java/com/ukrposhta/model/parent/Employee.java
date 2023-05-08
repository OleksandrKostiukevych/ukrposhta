package com.ukrposhta.model.parent;

import com.ukrposhta.exception.InvalidValueException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "employees")
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate birthDate;
    private LocalDate hiringDate;
    private BigDecimal salary;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Employee(Employee employee) {
        this.id = employee.getId();
        this.fullName = employee.getFullName();
        this.email = employee.getEmail();
        this.gender = employee.getGender();
        this.birthDate = employee.getBirthDate();
        this.hiringDate = employee.getHiringDate();
        this.salary = employee.getSalary();
        this.status = employee.getStatus();
    }

    @PrePersist
    public void prePersist() {
        hiringDate = LocalDate.now();
    }

    public enum Gender {
        MALE("male"),
        FEMALE("female");

        private final String genderName;

        Gender(String genderName) {
            this.genderName = genderName;
        }

        public static Gender getGender(String genderName) {
            for (Gender gender : Gender.values()) {
                if (genderName.equals(gender.genderName)) {
                    return gender;
                }
            }
            throw new InvalidValueException("Unable to find gender: " + genderName);
        }
    }

    public enum Status {
        WORKING("working"),
        VACATION("vacation"),
        FIRED("fired");

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
            throw new InvalidValueException("Unable to find status: " + statusName);
        }
    }
}

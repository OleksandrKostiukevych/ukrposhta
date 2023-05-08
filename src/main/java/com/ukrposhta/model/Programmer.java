package com.ukrposhta.model;

import com.ukrposhta.exception.InvalidValueException;
import com.ukrposhta.model.parent.Employee;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "programmers")
@EqualsAndHashCode(callSuper = false)
public class Programmer extends Employee {
    @Enumerated(EnumType.STRING)
    private Level level;
    @Enumerated(EnumType.STRING)
    private Type type;
    @ManyToMany
    @JoinTable(name = "programmers_technologies",
            joinColumns = @JoinColumn(name = "programmer_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id"))
    private Set<Technology> technologies = new HashSet<>();

    public Programmer(Employee employee) {
        super(employee);
    }

    public void addTechnology(Technology technology) {
        technologies.add(technology);
    }

    public void addAllTechnologies(List<Technology> technologies) {
        technologies.forEach(this::addTechnology);
    }

    public enum Type {
        DEVELOPER("developer"),
        QA("qa"),
        DEV_OPS("devops");

        private final String typeName;

        Type(String typeName) {
            this.typeName = typeName;
        }

        public static Type getType(String typeName) {
            for (Type type : Type.values()) {
                if (typeName.equals(type.typeName)) {
                    return type;
                }
            }
            throw new InvalidValueException("Unable to find programmer type: " + typeName);
        }
    }

    public enum Level {
        JUNIOR("junior"),
        MIDDLE("middle"),
        SENIOR("senior");

        private final String levelName;

        Level(String levelName) {
            this.levelName = levelName;
        }

        public static Level getLevel(String levelName) {
            for (Level level : Level.values()) {
                if (levelName.equals(level.levelName.toLowerCase())) {
                    return level;
                }
            }
            throw new InvalidValueException("Unable to find level: " + levelName);
        }
    }
}

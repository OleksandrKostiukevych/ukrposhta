package com.ukrposhta.model;

import com.ukrposhta.exception.InvalidValueException;
import com.ukrposhta.model.parent.Employee;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "managers")
@EqualsAndHashCode(callSuper = false)
public class Manager extends Employee {
    @Enumerated(EnumType.STRING)
    private Manager.Category category;

    public enum Category {
        TOP("top"),
        MIDDLE("middle"),
        TECH("tech");

        private final String categoryName;

        Category(String categoryName) {
            this.categoryName = categoryName;
        }

        public static Category getCategory(String categoryName) {
            for (Category category : Category.values()) {
                if (categoryName.equals(category.categoryName)) {
                    return category;
                }
            }
            throw new InvalidValueException("Unable to find category: " + categoryName);
        }
    }

    public Manager(Employee employee) {
        super(employee);
    }
}

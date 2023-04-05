package gpo.TestingSystem.Models;



import gpo.TestingSystem.Enumeration.EnumComplexity;
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "complexity")
public class Complexity {

    @Id
    @Enumerated(EnumType.STRING)
    private EnumComplexity enumComplexity;


    public EnumComplexity getEnumComplexity() {
        return enumComplexity;
    }

    public void setEnumComplexity(EnumComplexity enumComplexity) {
        this.enumComplexity = enumComplexity;
    }
}

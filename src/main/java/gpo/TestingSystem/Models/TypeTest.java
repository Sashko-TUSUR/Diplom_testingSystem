package gpo.TestingSystem.Models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import gpo.TestingSystem.Enumeration.EnumTypeIntegral;
import gpo.TestingSystem.Enumeration.EnumTypeTest;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name = "typeTest")
public class TypeTest {

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("label")
    @Enumerated(EnumType.STRING)
    private EnumTypeTest name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnumTypeTest getName() {
        return name;
    }

    public void setName(EnumTypeTest name) {
        this.name = name;
    }
}

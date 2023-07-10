package gpo.TestingSystem.Models;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import gpo.TestingSystem.Enumeration.EnumComplexity;
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name = "complexity")
public class Complexity {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long complexityId;

    @JsonProperty("label")
    @Enumerated(EnumType.STRING)
    private EnumComplexity name;


    public Long getComplexityId() {
        return complexityId;
    }

    public void setComplexityId(Long complexityId) {
        this.complexityId = complexityId;
    }

    public EnumComplexity getName() {
        return name;
    }

    public void setName(EnumComplexity name) {
        this.name = name;
    }
}

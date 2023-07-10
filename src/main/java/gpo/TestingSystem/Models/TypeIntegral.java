package gpo.TestingSystem.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gpo.TestingSystem.Enumeration.EnumRole;
import gpo.TestingSystem.Enumeration.EnumTypeIntegral;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name = "TypeIntegral")
public class TypeIntegral {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EnumTypeIntegral name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnumTypeIntegral getName() {
        return name;
    }

    public void setName(EnumTypeIntegral name) {
        this.name = name;
    }
}

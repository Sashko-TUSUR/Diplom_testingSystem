package gpo.TestingSystem.Models;


import com.sun.istack.NotNull;
import gpo.TestingSystem.Enumeration.EnumComplexity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "complexity")
public class Complexity {

    @Id
    @Enumerated(EnumType.STRING)
    private EnumComplexity enumComplexity;
}

package gpo.TestingSystem.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "template")
public class TemplateTest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long templateId;

    private String typeTest;
    private Long startTest;
    private Long endTest;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Complexity complexities ;

}

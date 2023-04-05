package gpo.TestingSystem.Models;

import gpo.TestingSystem.Enumeration.EnumRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleId;

    @Enumerated(EnumType.STRING)
    private EnumRole name;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public EnumRole getName() {
        return name;
    }

    public void setName(EnumRole name) {
        this.name = name;
    }
}

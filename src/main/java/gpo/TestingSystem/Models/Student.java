package gpo.TestingSystem.Models;


import com.fasterxml.jackson.annotation.*;
import gpo.TestingSystem.Payload.Views;
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;



@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")

@Entity
public class Student {

    @JsonIgnore
    @Id
    private Long userId;

    @JsonUnwrapped
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    @MapsId
    private User user;


    @JsonProperty("Group")
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "idGroup")
    private Groups idGroup ;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Groups getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Groups idGroup) {
        this.idGroup = idGroup;
    }
}

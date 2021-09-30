package com.elysee.dukachallenge.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_tbl")
public class User {
    @Id
    private String username;
    @Column
    private String password;
}

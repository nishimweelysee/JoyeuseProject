package com.elysee.dukachallenge.domain;

/**
* @author Nishimwe Elysee
* @version 0.1
* @since 0.0.1
* */

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Todo {
    @Id
    private UUID id;
    @NotNull
    private String name;
    @Length(min = 4)
    private String description;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @CreationTimestamp
    private LocalDateTime createDate;
    @UpdateTimestamp
    private LocalDateTime modifiedDate;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;
}

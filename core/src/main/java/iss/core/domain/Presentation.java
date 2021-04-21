package iss.core.domain;

import lombok.*;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Presentation {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private Paper paper;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private Section section;

    private LocalDate startDate;
    private LocalDate endDate;

    private File presentation;
}

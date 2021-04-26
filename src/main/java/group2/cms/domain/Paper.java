package group2.cms.domain;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
//@NoArgsConstructor
//@AllArgsConstructor
@Data
@EqualsAndHashCode
//@ToString
public class Paper{
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String paperAbstract;
    @ManyToMany
    @JoinTable(name = "paperAuthors", joinColumns = @JoinColumn(name = "authorId",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "paperId",referencedColumnName = "id"))
    private List<Author> authorList;

    //private List<String> keywords; TODO: investigate how to store keywords
}

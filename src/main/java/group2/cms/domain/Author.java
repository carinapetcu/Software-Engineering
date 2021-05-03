package group2.cms.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
//@AllArgsConstructor
//@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
//@ToString(callSuper = true)
public class Author extends CMSUser{
    @ManyToOne
    protected Paper paper;

    public Author() {
    }

    public Author(CMSUser paperUploader, Paper uploadedPaper) {
        super(paperUploader.getId(),
                paperUploader.getFullName(),
                paperUploader.getEmail(),
                paperUploader.getUsername(),
                paperUploader.getPassword());
        this.authority = Authority.Author;
        this.paper = uploadedPaper;
    }
}

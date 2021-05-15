package group2.cms.service.DTO.Author;

import group2.cms.domain.Author;
import group2.cms.service.DTO.DTOConverter;
import org.springframework.stereotype.Component;

@Component
public class AuthorDTOConverter implements DTOConverter<Author, AuthorDTO> {
    @Override
    public Author dtoToEntity(AuthorDTO dto) {
        return new Author();
    }

    @Override
    public AuthorDTO entityToDto(Author author) {
        return AuthorDTO.builder()
                .userID(author.getId())
                .fullName(author.getFullName())
                .username(author.getUsername())
                .email(author.getEmail())
                .password(author.getPassword())
                .paperID(author.getPaper().getId())
                .build();
    }
}

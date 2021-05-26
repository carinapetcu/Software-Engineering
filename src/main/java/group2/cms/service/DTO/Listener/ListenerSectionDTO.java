package group2.cms.service.DTO.Listener;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
public class ListenerSectionDTO implements Serializable {
    private final Long listenerId;
    private final Long sectionId;
}

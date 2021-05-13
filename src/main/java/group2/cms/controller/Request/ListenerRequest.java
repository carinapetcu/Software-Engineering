package group2.cms.controller.Request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@ToString
public class ListenerRequest implements Serializable {
    Long listenerID;
}

package laufyeconomybot.activity.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="950872624299409459.member" )
@Data
public class MemberData {
    @Id
    private long id;

    private int level;
}

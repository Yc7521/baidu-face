package cn.edu.njuit.web.server.domain;

import lombok.*;
import org.yc.orm.sql.annotations.Id;
import org.yc.orm.sql.annotations.Length;
import org.yc.orm.sql.annotations.NotNull;
import org.yc.orm.sql.annotations.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "user")
public class User {
    @Id
    Integer id;
    @NotNull
    @Length(64)
    String username;
    @NotNull
    @Length(64)
    String password;
}

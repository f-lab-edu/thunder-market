package com.github.thundermarket.thundermarket.domain;

import com.github.thundermarket.thundermarket.Util.Email;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class User {

    @Id
    private Long id;
    private String email;
    private String password;

    public boolean isEmailValid() {
        return Email.isValid(this.email);
    }
}

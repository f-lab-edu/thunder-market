package com.github.thundermarket.thundermarket.domain;

import com.github.thundermarket.thundermarket.Util.Email;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder(toBuilder = true)
@EqualsAndHashCode
@ToString
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;

    public boolean isEmailValid() {
        return Email.isValid(this.email);
    }
}

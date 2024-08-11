package com.github.thundermarket.thundermarket.domain;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class SessionUser implements Serializable {

    private Long id;
    private String email;
}

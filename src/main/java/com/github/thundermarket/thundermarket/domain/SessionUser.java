package com.github.thundermarket.thundermarket.domain;

import java.io.Serializable;
import java.util.Objects;

public class SessionUser implements Serializable {

    private Long id;
    private String email;

    public SessionUser() {}

    private SessionUser(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public static class Builder {
        private Long id;
        private String email;

        public Builder() {}

        public Builder(SessionUser sessionUser) {
            this.id = sessionUser.id;
            this.email = sessionUser.email;
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withEmail(String userEmail) {
            this.email = userEmail;
            return this;
        }

        public SessionUser build() {
            return new SessionUser(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionUser that = (SessionUser) o;
        return Objects.equals(id, that.id) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @Override
    public String toString() {
        return "SessionUser{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}

package com.github.thundermarket.thundermarket.domain;

import com.github.thundermarket.thundermarket.Util.Email;

import java.util.Objects;

public class User {

    private Long id;
    private String email;
    private String password;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEmailValid() {
        return Email.isValid(this.email);
    }

    private User(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.password = builder.password;
    }

    public static class Builder {
        private Long id;
        private String email;
        private String password;

        public User.Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public User.Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public User.Builder withPassword(String password) {
            this.password = password;
            return this;
        }
        public User build() {
            return new User(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

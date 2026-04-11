package com.dwes.rest.users;

public record NewUserCommand(
        String username, String email, String password
) {
}

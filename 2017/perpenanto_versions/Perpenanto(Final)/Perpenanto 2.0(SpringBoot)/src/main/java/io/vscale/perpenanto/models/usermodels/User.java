package io.vscale.perpenanto.models.usermodels;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.ToString;
import lombok.NoArgsConstructor;

import io.vscale.perpenanto.security.role.Role;
import io.vscale.perpenanto.security.states.UserState;

/**
 * 01.01.2018
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class User {

    private Long id;
    private String login;
    private String password;
    private String tempPassword;
    private Role role;
    private UserState userState;
    private String confirmHash;

}

package ru.itis.romanov_andrey.perpenanto.forms.admin;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.ToString;
import lombok.NoArgsConstructor;

/**
 * 26.11.2017
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
public class ProfileForm {

    private Long id;
    private String personName;
    private String personSurname;
    private String email;
    private Long userId;
    private Long addressId;

}

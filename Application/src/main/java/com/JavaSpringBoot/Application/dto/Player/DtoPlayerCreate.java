package com.JavaSpringBoot.Application.dto.Player;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import static com.JavaSpringBoot.Application.patterns.DateOfBirth.DATE_OF_BIRTH;

public class DtoPlayerCreate {
    @NotBlank(message = "A player must have a firstname!")
    @Size(max = 255)
    private String firstName;

    @NotBlank(message = "A player must have a lastname!")
    @Size(max = 255)
    private String lastName;

    @NotBlank(message = "A player must have a nickname!")
    @Size(max = 255)
    private String nickname;

    @NotBlank(message = "An artist must have a date of birth!")
    @Pattern(regexp = DATE_OF_BIRTH, message = "The format must be dd-mmm-yyyy, with year between 1800-2099!")
    private String dateBirth;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }
}

package ru.hse.vmoroke;

import java.time.LocalDate;

public class Request {
    private String login;
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate birthday;
    private String email;
    private String secretQuestion;
    private String answer;
    private int percent;

    public Request(String login, String firstName, String lastName, String middleName, LocalDate birthday, String email, String secretQuestion, String answer, int percent) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthday = birthday;
        this.email = email;
        this.secretQuestion = secretQuestion;
        this.answer = answer;
        this.percent = percent;
    }

    public int getPercent() {
        return percent;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getSecretQuestion() {
        return secretQuestion;
    }

    public String getAnswer() {
        return answer;
    }
}

package ru.hse.vmoroke;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Класс, представляющий сущность "Персона".
 */
public class Person {
    private String role;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate birthday;
    private String email;
    private String secretQuestion;
    private String answer;

    /**
     * Конструктор по умолчанию.
     */
    public Person() {
    }
    /**
     * Получить роль персоны.
     *
     * @return роль персоны
     */
    public String getRole() {
        return role;
    }
    /**
     * Установить роль персоны.
     *
     * @param role роль персоны
     */

    public void setRole(String role) {
        this.role = role;
    }
    /**
     * Получить логин персоны.
     *
     * @return логин персоны
     */

    public String getLogin() {
        return login;
    }
    /**
     * Установить логин персоны.
     *
     * @param login логин персоны
     */

    public void setLogin(String login) {
        this.login = login;
    }
    /**
     * Получить пароль персоны.
     *
     * @return пароль персоны
     */

    public String getPassword() {
        return password;
    }
    /**
     * Установить пароль персоны.
     *
     * @param password пароль персоны
     */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Получить имя персоны.
     *
     * @return имя персоны
     */

    public String getFirstName() {
        return firstName;
    }
    /**
     * Установить имя персоны.
     *
     * @param firstName имя персоны
     */

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Получить фамилию персоны.
     *
     * @return фамилия персоны
     */

    public String getLastName() {
        return lastName;
    }
    /**
     * Установить фамилию персоны.
     *
     * @param lastName фамилия персоны
     */

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * Получить отчество персоны.
     *
     * @return отчество персоны
     */

    public String getMiddleName() {
        return middleName;
    }
    /**
     * Установить отчество персоны.
     *
     * @param middleName отчество персоны
     */

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    /**
     * Получить дату рождения персоны.
     *
     * @return дата рождения персоны
     */

    public LocalDate getBirthday() {
        return birthday;
    }
    /**
     * Установить дату рождения персоны.
     *
     * @param birthday дата рождения персоны
     */

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    /**
     * Получить электронную почту персоны.
     *
     * @return электронная почта персоны
     */

    public String getEmail() {
        return email;
    }
    /**
     * Установить электронную почту персоны.
     *
     * @param email электронная почта персоны
     */

    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Получить секретный вопрос персоны.
     *
     * @return секретный вопрос персоны
     */

    public String getSecretQuestion() {
        return secretQuestion;
    }
    /**
     * Установить секретный вопрос персоны.
     *
     * @param secretQuestion секретный вопрос персоны
     */

    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }
    /**
     * Получить ответ на секретный вопрос персоны.
     *
     * @return ответ на секретный вопрос персоны
     */

    public String getAnswer() {
        return answer;
    }
    /**
     * Установить ответ на секретный вопрос персоны.
     *
     * @param answer ответ на секретный вопрос персоны
     */

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

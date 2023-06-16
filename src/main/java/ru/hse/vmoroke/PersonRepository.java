package ru.hse.vmoroke;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository {

    private static PersonRepository instance;
    private List<Person> personList;

    public static PersonRepository getInstance(){
        if (instance == null) {
            instance = new PersonRepository();
            instance.loadPersons();
        }
        return instance;
    }

    private PersonRepository() {}

    private void loadPersons() {
        personList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FilesDirectory.getFileName() + "Login_Data.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] t = line.split(";");
                String login = t[0];
                String password = t[1];
                String role = t[2];
                String surname = t[3];
                String name = t[4];
                String middlename = t[5];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(t[6], formatter);
                String email = t[7];
                String secretQuestion = t[8];
                String answer = t[9];
                boolean isBlocked = Boolean.parseBoolean(t[10]);

                Person person = new Person();
                person.setLogin(login);
                person.setPassword(password);
                person.setRole(role);
                person.setLastName(surname);
                person.setFirstName(name);
                person.setMiddleName(middlename);
                person.setBirthday(date);
                person.setEmail(email);
                person.setSecretQuestion(secretQuestion);
                person.setAnswer(answer);
                person.setBlocked(isBlocked);
                personList.add(person);

            }
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    public List<Person> getPersons() {
        return personList;
    }

    public void add(Person p) {
        p.setPassword(Util.hashPassword(p.getPassword()));
        personList.add(p);
    }

    public void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FilesDirectory.getFileName() + "Login_Data.txt"))) {
            for (Person p : personList) {
                writer.write(p.getLogin() + ";" + p.getPassword() + ";" + p.getRole() + ";" + p.getLastName() + ";" + p.getFirstName() + ";"
                        + p.getMiddleName() + ";" + p.getBirthday().toString() + ";" + p.getEmail() + ";" + p.getSecretQuestion() + ";" +
                        p.getAnswer() + ";" + p.isBlocked());
                writer.newLine();
            }
        } catch (IOException exc) {

        }
    }

    public void saveRequest(Request p) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FilesDirectory.getFileName() + "requests.txt", true))) {
            writer.write(p.getLogin() + ";" + p.getLastName() + ";" + p.getFirstName() + ";" + p.getMiddleName() +
                    ";" + p.getBirthday().toString() + ";" + p.getEmail() + ";" + p.getSecretQuestion() + ";" +
                    p.getAnswer() + ";" + p.getPercent());
            writer.newLine();
        } catch (IOException exc) {

        }
    }
}

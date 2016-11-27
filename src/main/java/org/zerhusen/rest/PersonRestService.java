package org.zerhusen.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zerhusen.security.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin()
public class PersonRestService {
    private static final List<Person> persons;

    static {
        persons = new ArrayList<>();
        persons.add(new Person("Hello", "World"));
        persons.add(new Person("Foo", "Bar"));
    }

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(path = "/persons", method = RequestMethod.GET)
    public static List<Person> getPersons() {

        return persons;
    }

    @RequestMapping(path = "/persons/{name}", method = RequestMethod.GET)
    public static Person getPerson(@PathVariable("name") String name) {
        return persons.stream()
                .filter(person -> name.equalsIgnoreCase(person.getName()))
                .findAny().orElse(null);
    }
}

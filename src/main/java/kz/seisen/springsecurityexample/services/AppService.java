package kz.seisen.springsecurityexample.services;

import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import kz.seisen.springsecurityexample.models.Application;
import kz.seisen.springsecurityexample.models.MyUser;
import kz.seisen.springsecurityexample.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;


@Service
@AllArgsConstructor
public class AppService {

    private List<Application> applications;
    private UserRepository userRepository;





    @PostConstruct
    public void loadAppInDB() {

        Faker faker = new Faker();

        applications = IntStream.rangeClosed(0, 100)
                .mapToObj(i -> Application.builder()
                        .id(i)
                        .name(faker.app().name())
                        .author(faker.app().author())
                        .version(faker.app().version())
                        .build())
                .toList();

    }


    public List<Application> allApplications() {
        return applications;
    }


    public Application applicationById(int id) {
        return applications.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }


    public void addUser(MyUser user) {
        userRepository.save(user);
    }
}

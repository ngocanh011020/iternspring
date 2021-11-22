package com.company;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
//    @Autowired private UserRepository repo;
//
//    @Test
//    public void testAddNew() {
//        Employee employee = new Employee();
//        employee.setEmail("phamngocanh011020@gmail.com");
//        employee.setPassword("123456");
//        employee.setFirstName("Pham");
//        employee.setLastName("Anh");
//        employee savedUser = repo.save(user);
//        assertThat(savedUser).isNotNull();
//        assertThat(savedUser.getId()).isGreaterThan(0);
//    }
//
//    @Test
//    public void testListAll() {
//        Iterable<User> users = repo.findAll();
//        assertThat(users).hasSizeGreaterThan(0);
//        for (User user : users) {
//            System.out.println(user);
//        }
//    }
//
//    @Test
//    public void testUpdate() {
//        Integer userId = 1;
//        Optional<User> optionalUser = repo.findById(userId);
//        User user = optionalUser.get();
//        user.setPassword("anh");
//        repo.save(user);
//        User updatedUser = repo.findById(userId).get();
//        assertThat(updatedUser.getPassword()).isEqualTo("anh");
//    }
//
//    @Test
//    public void testGet() {
//        Integer userId = 1;
//        Optional<User> optionalUser = repo.findById(userId);
//        assertThat(optionalUser).isPresent();
//        System.out.println(optionalUser.get());
//    }
//
//    @Test
//    public void testDelete() {
//        Integer userId = 1;
//        repo.deleteById(userId);
//        Optional<User> optionalUser = repo.findById(userId);
//        assertThat(optionalUser).isPresent();
//    }
}

package com.nextgened.dnd.diceroller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Created by Administrator on 8/24/2016.
 */
public class MockUserDAO implements UserDAO {
    private static Map<Long, User> users = new HashMap<>();
    private static Long lastPrimaryKey = 0L;

    static {
        User harry = new User();
        harry.setEmail("harry@hogwarts.ac.uk");
        harry.setFirstName("Harry Potter");
        harry.setCreatedDate(new Date());
        harry.setCustomerId(++lastPrimaryKey);
        users.put(harry.getCustomerId(), harry);

        User ron = new User();
        ron.setEmail("ron@hogwarts.ac.uk");
        ron.setFirstName("Ron Weasley");
        ron.setCreatedDate(new Date());
        ron.setCustomerId(++lastPrimaryKey);
        users.put(ron.getCustomerId(), ron);

        User hermione = new User();
        hermione.setEmail("hermione@hogwarts.ac.uk");
        hermione.setFirstName("Hermione Granger");
        hermione.setCreatedDate(new Date());
        hermione.setCustomerId(++lastPrimaryKey);
        users.put(hermione.getCustomerId(), hermione);
    }

    @Override
    public User insert(User user) {
        user.setCustomerId(++lastPrimaryKey);
        users.put(user.getCustomerId(), user);
        return user;
    }

    @Override
    public User delete(User user) {
        users.remove(user.getCustomerId());
        return user;
    }

    @Override
    public User findById(Long id) {
        return users.get(id);
    }

    @Override
    public List<User> findAll() {
        List<User> results = new Vector<>();
        results.addAll(users.values());
        return results;
    }

    @Override
    public List<User> findByEmail(String email) {
        List<User> results = new Vector<>();
        for (User u : users.values()) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                results.add(u);
            }
        }
        return results;
    }

    @Override
    public User update(User user) {
        users.put(user.getCustomerId(), user);
        return user;
    }
}

package com.nextgened.dnd.diceroller;

import java.util.List;

/**
 * Created by Administrator on 8/24/2016.
 */
public interface UserDAO {
    User insert(User user);

    User delete(User user);

    User findById(Long id);

    List<User> findAll();

    List<User> findByEmail(String email);

    User update(User user);
}

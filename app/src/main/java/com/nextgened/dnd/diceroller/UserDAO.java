package com.nextgened.dnd.diceroller;

import java.util.List;

/**
 * Created by Administrator on 8/24/2016.
 */
public interface UserDAO {
    /**
     *
     * @param user - the user to insert into the database (it will ignore a primary key)
     * @return the modified user with its new primary key
     */
    User insert(User user);

    /**
     *
     * @param user to delete from the database
     * @return the last known copy of the user that was just deleted
     */
    User delete(User user);

    /**
     *
     * @param id - customerId as the primary key
     * @return - the found user or null
     */
    User findById(Long id);

    List<User> findAll();

    List<User> findByEmail(String email);

    /**
     *
     * @param user to update (merged into the managed data context), should be considered "stale" as an object afterwards
     * @return the "merged" user that will continue to be updated
     */
    User update(User user);
}

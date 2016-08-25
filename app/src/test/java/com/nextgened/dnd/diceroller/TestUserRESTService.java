package com.nextgened.dnd.diceroller;

import org.junit.Test;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.junit.Assert.*;

/**
 * Created by Administrator on 8/25/2016.
 */
public class TestUserRESTService {

    @Test
    public void testFindAll() {
        // import from javax.ws.rs.* folders!
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://nextgened.com/weasley/customers.json")
                                    .request(MediaType.APPLICATION_JSON)
                                    .get();
        assertEquals("Wrong Data Type: ",
                MediaType.APPLICATION_JSON,
                response.getHeaderString(HttpHeaders.CONTENT_TYPE));

        try {
//            List<User> usersFound = response.readEntity(new GenericType<List>(){});
            List<User> usersFound = response.readEntity(List.class);

            assertTrue("No users found!", usersFound.size() > 0);
            System.out.println("#Users: " + usersFound.size());
            User firstUser = usersFound.get(0);
            System.out.println(firstUser.getFirstName());
            System.out.println(firstUser.getClass().getName());
            //System.out.println(usersFound.get(0).getFirstName());
            System.out.println("Users: " + usersFound);
        } catch (Exception e) {
            System.out.println("Failed to read Entity Object as List<User>: " + e.getMessage());
            fail("Exception blown on User List Entity Read");
            e.printStackTrace();
        }

    }

//    public static void main(String[] args) {
//        TestUserRESTService test = new TestUserRESTService();
//        test.testFindAll();
//    }
}

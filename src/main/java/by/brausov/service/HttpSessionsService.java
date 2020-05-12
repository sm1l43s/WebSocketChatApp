package by.brausov.service;

import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Service
public class HttpSessionsService {

    HashMap<Integer, HttpSession> userAndSession = new HashMap<>();

    public void add(Integer userId, HttpSession session) {
        this.userAndSession.put(userId, session);
        System.out.println(userAndSession);
    }

    public HashMap<Integer, HttpSession> getUserAndSession() {
        return userAndSession;
    }
}

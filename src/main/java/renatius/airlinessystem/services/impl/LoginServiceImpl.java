package renatius.airlinessystem.services.impl;

import renatius.airlinessystem.services.LoginService;

public class LoginServiceImpl implements LoginService {
    public boolean login(String username, String password) {
        if (username.equals("adminYan") && password.equals("hoksMachine")) {
            return true;
        }
        if(username.equals("adminRenat") && password.equals("VolkswagenGolf")) {
            return true;
        }
        else{
        return false;
        }
    }
}

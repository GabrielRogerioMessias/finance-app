package com.integratingProject.financeapp.controllersJSF;

import com.integratingProject.financeapp.models.User;
import com.integratingProject.financeapp.services.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
@Data

public class UserControllerJSF implements Serializable {
    @Autowired
    private UserService userService;
    private User user = new User();
    private List<User> userList;


    public void insert() {
        userService.insert(user);
        Messages.addFlashGlobalInfo("Registro salvo com sucesso");
        user = new User();
    }

    @PostConstruct
    public List<User> findAll() {
    return userList = userService.findAll();
    }
}

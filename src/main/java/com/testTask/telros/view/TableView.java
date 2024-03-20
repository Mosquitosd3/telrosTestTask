package com.testTask.telros.view;

import com.testTask.telros.model.User;
import com.testTask.telros.service.UserRepositoryService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;


@Route("")
@RolesAllowed("ADMIN")
public class TableView extends VerticalLayout {
    private final UserRepositoryService service;
    private Grid<User> grid;

    public TableView(UserRepositoryService service) {
        this.service = service;
        this.grid = new Grid<>(User.class);
        add(grid);
        grid.setItems(service.findAllUser());
    }
}

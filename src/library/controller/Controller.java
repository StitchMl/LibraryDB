package library.controller;

import library.model.domain.User;

public abstract class Controller{

    /** Type of Controller*/
    protected MainController app;

    /** User*/
    protected User user = null;

    public void setApp(MainController app) {
        this.app = app;
    }

    /** Is called to set user.*/
    public void setUser(User user) {
        this.user = user;
    }

    public MainController getApp() {
        return this.app;
    }

    /** Is called to get user.*/
    public User getUser() {
        return this.user;
    }
}
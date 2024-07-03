package library.controller;

import library.model.domain.User;

public class BookListController extends Controller {
    /** Is called to set user.*/
    @Override
    public void setUser(User user) {
        this.user = user;
    }

}

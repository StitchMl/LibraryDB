package library.controller;

import library.model.dao.Submit;

public abstract class AccessController extends Controller{

    /** Reference to submit.*/
    protected Submit submit;

    @Override
    public void setApp(MainController app) {
        this.app = app;
        this.submit = new Submit();
    }

    /** Is called to set submit.*/
    public void setSubmit(Submit submit) {
        this.submit = submit;
    }

    public void home(){
        this.app.setSubmit(this.submit);
        this.app.showHomeOverview();
    }
}

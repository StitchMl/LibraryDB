package library.bean;

import javafx.scene.control.TextField;


public class SignUpBean {

    public boolean checkEmpty(TextField str){
        return str.getText().equals("");
    }
}

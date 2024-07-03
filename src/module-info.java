module library {
    requires javafx.media;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires javafx.web;
    requires org.apache.commons.lang3;
    requires org.json;
    requires mysql.connector.java;
    requires commons.validator;

    opens library to javafx.fxml, javafx.graphics, javafx.media, javafx.web;
    exports library;
    opens library.model.dao to javafx.fxml, javafx.graphics, javafx.media, javafx.web;
    exports library.model.dao;
    opens library.model.domain to javafx.fxml, javafx.graphics, javafx.media, javafx.web;
    exports library.model.domain;
    opens library.controller to javafx.fxml, javafx.graphics, javafx.media, javafx.web;
    exports library.controller;
    opens library.controller.graphic to javafx.fxml, javafx.graphics, javafx.media, javafx.web;
    exports library.controller.graphic;
    opens library.bean to javafx.fxml, javafx.graphics, javafx.media, javafx.web;
    exports library.bean;
    opens library.exception to javafx.fxml, javafx.graphics, javafx.media, javafx.web;
    exports library.exception;
    exports library.utilities;
    opens library.utilities to javafx.fxml, javafx.graphics, javafx.media, javafx.web;
}

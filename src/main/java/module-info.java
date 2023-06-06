module ru.hse.vmoroke {
    requires javafx.controls;
    requires javafx.fxml;
    requires sdk;
    requires java.sql;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient;
    requires mail;

    opens ru.hse.vmoroke to javafx.fxml;
    exports ru.hse.vmoroke;
}
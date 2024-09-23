module co.edu.uniquindio.bd1.proyectobd1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires spring.beans;
    requires spring.context;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.data.jpa;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires spring.core;

    // Abrir el paquete donde están tus entidades para Hibernate
    opens co.edu.uniquindio.bd1.proyectobd1.model to spring.core, spring.beans, spring.data.jpa, org.hibernate.orm.core;
    opens co.edu.uniquindio.bd1.proyectobd1.service to spring.core, spring.beans;
    opens co.edu.uniquindio.bd1.proyectobd1.application to spring.core, spring.beans,javafx.fxml; // Agrega este paquete si es necesario para la reflexión

    // Exportar los paquetes necesarios para Spring
    exports co.edu.uniquindio.bd1.proyectobd1.service;
    exports co.edu.uniquindio.bd1.proyectobd1.application;
    exports co.edu.uniquindio.bd1.proyectobd1;
    opens co.edu.uniquindio.bd1.proyectobd1 to javafx.fxml, spring.beans, spring.core;
    exports co.edu.uniquindio.bd1.proyectobd1.controllers to javafx.fxml;
    opens co.edu.uniquindio.bd1.proyectobd1.controllers to javafx.fxml;
}
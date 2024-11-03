module co.edu.uniquindio.bd1.proyectobd1 {
    // Requiere de JavaFX
    requires javafx.controls;
    requires javafx.fxml;

    // Requiere de Spring y Hibernate
    requires spring.beans;
    requires spring.context;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.data.jpa;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires spring.core;
    requires static lombok;
    requires spring.data.commons;
    requires org.aspectj.weaver;
    requires openhtmltopdf.pdfbox;

    // Abre paquetes a Spring y Hibernate para la reflexión
    opens co.edu.uniquindio.bd1.proyectobd1.model.entities to org.hibernate.orm.core, spring.core, spring.beans,spring.data.jpa;
    opens co.edu.uniquindio.bd1.proyectobd1.application to spring.core, spring.beans, javafx.fxml;

    // Exporta paquetes necesarios para otros módulos y JavaFX
    exports co.edu.uniquindio.bd1.proyectobd1;
    exports co.edu.uniquindio.bd1.proyectobd1.service.interfaces;
    exports co.edu.uniquindio.bd1.proyectobd1.service.implementations;
    exports co.edu.uniquindio.bd1.proyectobd1.application;
    exports co.edu.uniquindio.bd1.proyectobd1.controllers to javafx.fxml;

    // Abre controladores para JavaFX
    opens co.edu.uniquindio.bd1.proyectobd1.controllers to javafx.fxml;

    // Abre modelo para Spring
    opens co.edu.uniquindio.bd1.proyectobd1 to spring.beans, spring.core, javafx.fxml;
    opens co.edu.uniquindio.bd1.proyectobd1.service.interfaces to spring.beans, spring.core;
    opens co.edu.uniquindio.bd1.proyectobd1.service.implementations to spring.beans, spring.core;
}

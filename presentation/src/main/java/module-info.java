module com.olexxxxandr.carrepair.presentation {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.olexxxxandr.carrepair.domain;
    requires MaterialFX;

    exports com.olexxxxandr.carrepair.presentation;
    exports com.olexxxxandr.carrepair.presentation.controller;
    exports com.olexxxxandr.carrepair.presentation.model.impl;
    exports com.olexxxxandr.carrepair.presentation.mapper.impl;

    opens com.olexxxxandr.carrepair.presentation.controller to
            javafx.fxml;

    exports com.olexxxxandr.carrepair.presentation.controller.client;
    exports com.olexxxxandr.carrepair.presentation.controller.workroom;
    exports com.olexxxxandr.carrepair.presentation.controller.position;
    exports com.olexxxxandr.carrepair.presentation.controller.service;
    exports com.olexxxxandr.carrepair.presentation.controller.spare;
    exports com.olexxxxandr.carrepair.presentation.controller.car;
    exports com.olexxxxandr.carrepair.presentation.controller.employee;
    exports com.olexxxxandr.carrepair.presentation.controller.payroll;
    exports com.olexxxxandr.carrepair.presentation.controller.order;

    opens com.olexxxxandr.carrepair.presentation.controller.client to
            javafx.fxml;
    opens com.olexxxxandr.carrepair.presentation.controller.workroom to
            javafx.fxml;
    opens com.olexxxxandr.carrepair.presentation.controller.position to
            javafx.fxml;
    opens com.olexxxxandr.carrepair.presentation.controller.service to
            javafx.fxml;
    opens com.olexxxxandr.carrepair.presentation.controller.spare to
            javafx.fxml;
    opens com.olexxxxandr.carrepair.presentation.controller.car to
            javafx.fxml;
    opens com.olexxxxandr.carrepair.presentation.controller.employee to
            javafx.fxml;
    opens com.olexxxxandr.carrepair.presentation.controller.payroll to
            javafx.fxml;
    opens com.olexxxxandr.carrepair.presentation.controller.order to
            javafx.fxml;
}

package com.qam2.view;

import com.qam2.model.Appointment;
import com.qam2.utils.AppointmentManager;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Provides tabular view of Appointment records.
 * @author Alex Hanson
 */
public final class AppointmentView extends VBox {

    private final TableView<Appointment> table;
    private final ToggleGroup group;
    private final AppointmentManager manager;

    public AppointmentView() {
        table = new TableView<>();
        manager = AppointmentManager.getInstance();
        group = new ToggleGroup();
        buildFilters();
        buildView();
    }

    private void buildFilters() {

        var week = new RadioButton("Week");
        var month = new RadioButton("Month");
        var all = new RadioButton("All");

        week.setToggleGroup(group);
        month.setToggleGroup(group);
        all.setToggleGroup(group);

        group.selectedToggleProperty().addListener((ob, ovl, nvl) -> {
           filterAppointments( ( (RadioButton) group.getSelectedToggle() ).getText() );
        });

        group.selectToggle(all);

        var filters = new HBox(5, all, week, month);
        VBox.setMargin(filters, new Insets(5));
        getChildren().add(filters);
    }

    private void filterAppointments(String filter) {

        switch(filter) {
            case "Week":
                table.setItems(FXCollections.observableList(manager.getAppointmentsForCurrentWeek()));
                break;
            case "Month":
                table.setItems(FXCollections.observableList(manager.getAppointmentsForCurrentMonth()));
                break;
            case "All":
                table.setItems(FXCollections.observableList(manager.getAll()));
                break;
        }
    }

    private void buildView() {

        var id = new TableColumn<Appointment, Integer>("ID");
        var title = new TableColumn<Appointment, String>("Title");
        var description = new TableColumn<Appointment, String>("Description");
        var location = new TableColumn<Appointment, String>("Location");
        var type = new TableColumn<Appointment, String>("Type");
        var start = new TableColumn<Appointment, String>("Start Time");
        var end = new TableColumn<Appointment, String>("End Time");
        var customer = new TableColumn<Appointment, String>("Customer");
        var customerID = new TableColumn<Appointment, Integer>("Customer ID");
        var contact = new TableColumn<Appointment, String>("Contact");
        var userID = new TableColumn<Appointment, Integer>("User ID");

        id.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        location.setCellValueFactory(new PropertyValueFactory<>("location"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        start.setCellValueFactory(new PropertyValueFactory<>("startDisplay"));
        end.setCellValueFactory(new PropertyValueFactory<>("endDisplay"));
        customer.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        contact.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        userID.setCellValueFactory(new PropertyValueFactory<>("userID"));

        id.setResizable(false);
        id.setReorderable(false);
        id.setPrefWidth(50);

        title.setResizable(false);
        title.setReorderable(false);
        title.setPrefWidth(100);

        description.setResizable(false);
        description.setReorderable(false);
        description.setPrefWidth(120);

        location.setResizable(false);
        location.setReorderable(false);
        location.setPrefWidth(100);

        type.setResizable(false);
        type.setReorderable(false);
        type.setPrefWidth(175);

        start.setResizable(false);
        start.setReorderable(false);
        start.setPrefWidth(125);

        end.setResizable(false);
        end.setReorderable(false);
        end.setPrefWidth(125);

        customer.setResizable(false);
        customer.setReorderable(false);
        customer.setPrefWidth(140);

        customerID.setResizable(false);
        customerID.setReorderable(false);
        customerID.setPrefWidth(75);

        contact.setResizable(false);
        contact.setReorderable(false);
        contact.setPrefWidth(130);

        userID.setResizable(false);
        userID.setReorderable(false);
        userID.setPrefWidth(50);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(id, title, description, location, type, start, end, customer, customerID, contact, userID);
        getChildren().add(table);
    }

    public Appointment getSelected() { return table.getSelectionModel().getSelectedItem(); }

    public void refreshView() {
        filterAppointments( ( (RadioButton) group.getSelectedToggle() ).getText() );
        table.refresh();
    }

}

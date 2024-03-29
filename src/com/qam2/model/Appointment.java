package com.qam2.model;

import com.qam2.utils.ContactManager;
import com.qam2.utils.CustomerManager;
import com.qam2.utils.time.TimeUtil;
import com.qam2.utils.time.TimeZone;
import com.qam2.utils.UserManager;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

/**
 * Immutable class representing a single Appointment record.
 * @author Alex Hanson
 */
public final class Appointment {

    private final int appointmentID;
    private final String title;
    private final String description;
    private final String location;
    private final String type;
    private final ZonedDateTime start;
    private final ZonedDateTime end;
    private final ZonedDateTime createDate;
    private final String createdBy;
    private final ZonedDateTime lastUpdate;
    private final String lastUpdatedBy;
    private final int customerID;
    private final int userID;
    private final int contactID;

    /**
     * Copy constructor.
     * @param a The Appointment to copy.
     */
    public Appointment(Appointment a) {
        this(a.appointmentID, a.title, a.description, a.location, a.type, a.start, a.end, a.createDate,
                a.createdBy, a.lastUpdate, a.lastUpdatedBy, a.customerID, a.userID, a.contactID);
    }

    /**
     * Allows the creation of an Appointment without an ID.
     * IDs are auto-generated by database. To initially add an Appointment, one must be created without an ID.
     * @param title Appointment title.
     * @param description Appointment description.
     * @param location Appointment location.
     * @param type Appointment type.
     * @param start UTC Zoned start date and time for Appointment.
     * @param end UTC Zoned end date and time for Appointment.
     * @param createDate UTC Zoned creation date and time for Appointment.
     * @param createdBy User that created the Appointment.
     * @param lastUpdate UTC Zoned date and time for last Appointment update.
     * @param lastUpdatedBy User that last updated the Appointment.
     * @param customerID ID of the Customer for whom the Appointment is for.
     * @param userID ID of the User who created the Appointment.
     * @param contactID ID of the company employee/contact assigned the Appointment.
     */
    public Appointment( String title, String description, String location, String type, ZonedDateTime start,
                        ZonedDateTime end, ZonedDateTime createDate, String createdBy, ZonedDateTime lastUpdate,
                        String lastUpdatedBy, int customerID, int userID, int contactID) {

        this(-1, title, description, location, type, start, end, createDate, createdBy, lastUpdate, lastUpdatedBy, customerID, userID, contactID);
    }

    /**
     * Creates an obj representing a complete Appointment record.
     * @param appointmentID Appointment ID.
     * @param title Appointment title.
     * @param description Appointment description.
     * @param location Appointment location.
     * @param type Appointment type.
     * @param start UTC Zoned start date and time for Appointment.
     * @param end UTC Zoned end date and time for Appointment.
     * @param createDate UTC Zoned creation date and time for Appointment.
     * @param createdBy User that created the Appointment.
     * @param lastUpdate UTC Zoned date and time for last Appointment update.
     * @param lastUpdatedBy User that last updated the Appointment.
     * @param customerID ID of the Customer for whom the Appointment is for.
     * @param userID ID of the User who created the Appointment.
     * @param contactID ID of the company employee/contact assigned the Appointment.
     */
    public Appointment(int appointmentID, String title, String description, String location,
                       String type, ZonedDateTime start, ZonedDateTime end, ZonedDateTime createDate, String createdBy,
                       ZonedDateTime lastUpdate, String lastUpdatedBy, int customerID, int userID, int contactID) {

        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = ZonedDateTime.of(start.toLocalDateTime(), TimeZone.UTC.getID());
        this.end = ZonedDateTime.of(end.toLocalDateTime(), TimeZone.UTC.getID());
        this.createDate = ZonedDateTime.of(createDate.toLocalDateTime(), TimeZone.UTC.getID());
        this.createdBy = createdBy;
        this.lastUpdate = ZonedDateTime.of(lastUpdate.toLocalDateTime(), TimeZone.UTC.getID());
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    /**
     * @return Appointment ID.
     */
    public int getAppointmentID() { return appointmentID; }

    /**
     * @return Appointment title.
     */
    public String getTitle() { return title; }

    /**
     * @return Appointment description.
     */
    public String getDescription() { return description; }

    /**
     * @return Appointment location.
     */
    public String getLocation() { return location; }

    /**
     * @return Appointment type.
     */
    public String getType() { return type; }

    /**
     * Returns the UTC zoned date and time for the start of the Appointment.
     * @return The UTC zoned start date and time.
     */
    public ZonedDateTime getStart() { return ZonedDateTime.of(start.toLocalDateTime(), TimeZone.UTC.getID()); }

    /**
     * Returns the start date of the Appointment converted from UTC to the systems' local time zone.
     * @return The start date according to system's local time zone.
     */
    public LocalDate getLocalStartDate() { return TimeUtil.convertTime(start, TimeZone.LOCAL).toLocalDate(); }

    /**
     * Returns the start time of the Appointment converted from UTC to the system's local time zone.
     * @return The start time according to system's local time zone.
     */
    public LocalTime getLocalStartTime() { return TimeUtil.convertTime(start, TimeZone.LOCAL).toLocalTime(); }

    /**
     * Formats the local start time of the appointment into a String.
     * @return The String representation of the local start time.
     */
    public String getLocalStartString() { return TimeUtil.toTimeString(TimeUtil.convertTime(start, TimeZone.LOCAL)); }

    /**
     * Formats the local start time and date for tabular display.
     * @return The String representation of the local start time and date.
     */
    public String getStartDisplay() {
        return TimeUtil.toAppointmentTimeDisplay(TimeUtil.convertTime(start, TimeZone.LOCAL));
    }

    /**
     * Returns the UTC zoned date and time for the end of the Appointment.
     * @return The UTC zoned end date and time.
     */
    public ZonedDateTime getEnd() { return ZonedDateTime.of(end.toLocalDateTime(), TimeZone.UTC.getID()); }

    /**
     * Returns the end date of the Appointment converted from UTC to the systems' local time zone.
     * @return The end date according to system's local time zone.
     */
    public LocalDate getLocalEndDate() { return TimeUtil.convertTime(end, TimeZone.LOCAL).toLocalDate(); }

    /**
     * Returns the end time of the Appointment converted from UTC to the system's local time zone.
     * @return The end time according to system's local time zone.
     */
    public LocalTime getLocalEndTime() { return TimeUtil.convertTime(end, TimeZone.LOCAL).toLocalTime(); }

    /**
     * Formats the local end time of the appointment into a String.
     * @return The String representation of the local end time.
     */
    public String getLocalEndString() { return TimeUtil.toTimeString(TimeUtil.convertTime(end, TimeZone.LOCAL)); }

    /**
     * Formats the local end time and date for tabular display.
     * @return The String representation of the local end time and date.
     */
    public String getEndDisplay() {
        return TimeUtil.toAppointmentTimeDisplay(TimeUtil.convertTime(end, TimeZone.LOCAL));
    }

    /**
     * @return The UTC date and time the Appointment was created.
     */
    public ZonedDateTime getCreateDate() { return ZonedDateTime.of(createDate.toLocalDateTime(), TimeZone.UTC.getID()); }

    /**
     * @return The username who created the Appointment.
     */
    public String getCreatedBy() { return createdBy; }

    /**
     * @return The UTC date and time the Appointment was last updated.
     */
    public ZonedDateTime getLastUpdate() { return ZonedDateTime.of(lastUpdate.toLocalDateTime(), TimeZone.UTC.getID()); }

    /**
     * @return The username that last updated the Appointment.
     */
    public String getLastUpdatedBy() { return lastUpdatedBy; }

    /**
     * @return The Customer ID for whom the Appointment is for.
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * @return The User ID for the user that created the Appointment.
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @return The Contact ID associated with the Appointment.
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * @return The Customer name for the Appointment's Customer ID.
     */
    public String getCustomerName() { return CustomerManager.getInstance().getCustomerName(customerID); }

    /**
     * @return The Contact name for the Appointment's Contact ID.
     */
    public String getContactName() { return ContactManager.getContactName(contactID); }

    /**
     * @return The username for the Appointment's User ID.
     */
    public String getUserName() { return UserManager.getUserName(userID); }

    /**
     * Overrides Object equals. Compares based on Appointment ID not reference variable value.
     * @param o The Appointment to compare (this) Appointment with.
     * @return True if Appointment IDs are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {

        if(o instanceof Appointment)
            return this.appointmentID == ((Appointment) o).getAppointmentID();

        return false;
    }
}

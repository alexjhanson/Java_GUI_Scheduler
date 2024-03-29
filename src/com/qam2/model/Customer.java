package com.qam2.model;

import com.qam2.utils.CountriesAndDivisions;
import com.qam2.utils.time.TimeZone;

import java.time.ZonedDateTime;

/**
 * Immutable class representing a Customer record.
 * @author Alex Hanson
 */
public final class Customer {

    private final int customerID;
    private final String customerName;
    private final String address;
    private final String postalCode;
    private final String phone;
    private final ZonedDateTime createDate;
    private final String createdBy;
    private final ZonedDateTime lastUpdate;
    private final String lastUpdatedBy;
    private final int divisionID;

    /**
     * Copy constructor.
     * @param c The Customer to copy.
     */
    public Customer(Customer c) {
        this(c.customerID, c.customerName, c.address, c.postalCode, c.phone,
                c.createDate, c.createdBy, c.lastUpdate, c.lastUpdatedBy, c.divisionID);
    }

    /**
     * Allows the creation of a Customer without an ID.
     * IDs are auto-generated by database. To initially add a Customer, one must be created without an ID.
     * @param customerName The Customer's name.
     * @param address The Customer's address.
     * @param postalCode The Customer's postal code.
     * @param phone The Customer's phone number.
     * @param createDate The UTC Zoned date and time of the Customer record's creation.
     * @param createdBy The username who created the Customer record.
     * @param lastUpdate the UTC Zoned date and time the Customer record was last updated.
     * @param lastUpdatedBy The username that last updated the Customer record.
     * @param divisionID The first level division ID for the Customer's address.
     */
    public Customer(String customerName, String address, String postalCode, String phone, ZonedDateTime createDate,
                    String createdBy, ZonedDateTime lastUpdate, String lastUpdatedBy, int divisionID) {
        this(-1, customerName, address, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdatedBy, divisionID);
    }

    /**
     * Creates an obj representing a complete Customer record.
     * @param customerID Customer record ID.
     * @param customerName The Customer's name.
     * @param address The Customer's address.
     * @param postalCode The Customer's postal code.
     * @param phone The Customer's phone number.
     * @param createDate The UTC Zoned date and time of the Customer record's creation.
     * @param createdBy The username who created the Customer record.
     * @param lastUpdate the UTC Zoned date and time the Customer record was last updated.
     * @param lastUpdatedBy The username that last updated the Customer record.
     * @param divisionID The first level division ID for the Customer's address.
     */
    public Customer(int customerID, String customerName, String address, String postalCode, String phone, ZonedDateTime createDate, String createdBy,
                    ZonedDateTime lastUpdate, String lastUpdatedBy, int divisionID) {

        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = ZonedDateTime.of(createDate.toLocalDateTime(), TimeZone.UTC.getID());
        this.createdBy = createdBy;
        this.lastUpdate = ZonedDateTime.of(lastUpdate.toLocalDateTime(), TimeZone.UTC.getID());
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;
    }

    /**
     * @return The Customer record ID.
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * @return The Customer's name.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @return The Customer's address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return The Customer's postal code.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @return The Customer's phone number.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @return UTC Zoned date and time the Customer record was created.
     */
    public ZonedDateTime getCreateDate() {
        return ZonedDateTime.of(createDate.toLocalDateTime(), TimeZone.UTC.getID());
    }

    /**
     * @return The username that created the Customer record.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @return UTC Zoned date and time the Customer record was last updated.
     */
    public ZonedDateTime getLastUpdate() {
        return ZonedDateTime.of(lastUpdate.toLocalDateTime(), TimeZone.UTC.getID());
    }

    /**
     * @return The username that last updated the Customer record.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * @return The first-level division ID for the Customer's address.
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * @return The Country ID for the Customer's address.
     */
    public int getCountryID() { return CountriesAndDivisions.getCountryIDForDivision(divisionID); }

    /**
     * @return The Country name for the Customer's address.
     */
    public String getCountryName() { return CountriesAndDivisions.getCountryName(this.getCountryID()); }

    /**
     * @return The first-level division name for the Customer's address.
     */
    public String getDivisionName() { return CountriesAndDivisions.getDivisionName(divisionID); }

    /**
     * Overrides Object equals. Compares based on Customer ID not reference variable value.
     * @param obj The Customer to compare (this) Customer with.
     * @return True if Customer IDs are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {

        if(obj instanceof Customer)
            return customerID == ((Customer) obj).getCustomerID();

        return false;
    }
}

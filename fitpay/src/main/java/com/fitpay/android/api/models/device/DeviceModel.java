package com.fitpay.android.api.models.device;

import com.fitpay.android.api.models.card.CreditCard;

import java.util.List;

/**
 * Created by Vlad on 14.03.2016.
 */
abstract class DeviceModel extends PaymentDevice {

    protected String deviceIdentifier;

    /**
     * description : The serial number for a particular instance of the device
     */
    protected String serialNumber;

    /**
     * description : The model number that is assigned by the device vendor.
     */
    protected String modelNumber;

    /**
     * description : The hardware revision for the hardware within the device.
     */
    protected String hardwareRevision;

    /**
     * description : The firmware revision for the firmware within the device.
     */
    protected String firmwareRevision;

    /**
     * description : The software revision for the software within the device.
     */
    protected String softwareRevision;

    protected String createdTs;

    protected Long createdTsEpoch;

    /**
     * description : The name of the operating system
     */
    protected String osName;

    /**
     * description : A structure containing an Organizationally Unique Identifier (OUI)
     * followed by a manufacturer-defined identifier and is unique for each individual instance of the product.
     */
    protected String systemId;

    /**
     * description : The license key parameter is used to read or write the license key of the device
     */
    protected String licenseKey;

    /**
     * description : The BD address parameter is used to read the Bluetooth device address
     */
    protected String bdAddress;

    /**
     * description : The time the device was paired
     */
    protected String pairingTs;


    protected List<CreditCard> cardRelationships;

    protected String hostDeviceId;

    protected DeviceModel(){
    }

    public String getDeviceIdentifier() {
        return deviceIdentifier;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public String getHardwareRevision() {
        return hardwareRevision;
    }

    public String getFirmwareRevision() {
        return firmwareRevision;
    }

    public String getSoftwareRevision() {
        return softwareRevision;
    }

    public String getCreatedTs() {
        return createdTs;
    }

    public long getCreatedTsEpoch() {
        return createdTsEpoch;
    }

    public String getOsName() {
        return osName;
    }

    public String getSystemId() {
        return systemId;
    }

    public String getLicenseKey() {
        return licenseKey;
    }

    public String getBdAddress() {
        return bdAddress;
    }

    public String getPairingTs() {
        return pairingTs;
    }

    public List<CreditCard> getCardRelationships() {
        return cardRelationships;
    }

    public String getHostDeviceId() {
        return hostDeviceId;
    }
}

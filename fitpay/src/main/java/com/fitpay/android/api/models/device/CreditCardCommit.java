package com.fitpay.android.api.models.device;

import com.fitpay.android.api.enums.CardInitiators;
import com.fitpay.android.api.enums.ProvisioningFailedReasons;
import com.fitpay.android.api.models.AssetReference;
import com.fitpay.android.api.models.card.Address;
import com.fitpay.android.api.models.card.CardMetaData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Commit API credit card model
 */
public class CreditCardCommit {

    protected String creditCardId;
    protected String userId;

    /**
     * @deprecated as of v1.1.0
     */
    @Deprecated
    @SerializedName("default")
    protected Boolean defaultX;

    protected Long createdTsEpoch;
    protected Long lastModifiedTsEpoch;
    protected String state;

    @CardInitiators.Initiator
    protected String causedBy;
    protected String cardType;
    protected CardMetaData cardMetaData;
    protected String targetDeviceId;
    protected String targetDeviceType;
    protected String externalTokenReference;
    protected String termsAssetId;
    protected Long eligibilityExpirationEpoch;

    @SerializedName("reason")
    @ProvisioningFailedReasons.Reason
    protected String provisioningFailedReason;

    protected List<AssetReference> termsAssetReferences;

    // TODO: eliminate the duplicates between these and creditCard
    private String pan;
    private int expMonth;
    private int expYear;
    private String cvv;
    private String name;

    private Address address;

    protected CreditCardCommit() {
    }

    public String getCreditCardId() {
        return creditCardId;
    }

    public String getUserId() {
        return userId;
    }

    /**
     * @deprecated as of v1.1.0 - will stop being returned from the server
     */
    @Deprecated
    public boolean isDefault() {
        if (null == defaultX) {
            return false;
        }
        return defaultX;
    }

    public long getCreatedTsEpoch() {
        return createdTsEpoch;
    }

    public long getLastModifiedTsEpoch() {
        return lastModifiedTsEpoch;
    }

    public String getState() {
        return state;
    }

    public String getCausedBy() {
        return causedBy;
    }

    public String getCardType() {
        return cardType;
    }

    public CardMetaData getCardMetaData() {
        return cardMetaData;
    }

    public String getTargetDeviceId() {
        return targetDeviceId;
    }

    public String getTargetDeviceType() {
        return targetDeviceType;
    }

    public String getExternalTokenReference() {
        return externalTokenReference;
    }

    public Long getEligibilityExpirationEpoch() {
        return eligibilityExpirationEpoch;
    }

    public Address getAddress() {
        return address;
    }

    public String getCvv() {
        return cvv;
    }

    public int getExpMonth() {
        return expMonth;
    }

    public int getExpYear() {
        return expYear;
    }

    public String getName() {
        return name;
    }

    public String getPan() {
        return pan;
    }

    public String getTermsAssetId() {
        return termsAssetId;
    }

    public List<AssetReference> getTermsAssetReferences() {
        return termsAssetReferences;
    }

    /**
     * The reason a card provisioning failed. Returned in the payload of a non-apdu commit
     *
     * @return provisioningFailedReason
     */
    public String getProvisioningFailedReason() {
        return provisioningFailedReason;
    }

}

package com.fitpay.android.api.models.card;

import com.fitpay.android.TestActions;
import com.fitpay.android.api.models.collection.Collections;
import com.fitpay.android.api.models.device.Device;
import com.fitpay.android.utils.NamedResource;

import org.junit.ClassRule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class CreditCardTest3 extends TestActions {

    @ClassRule
    public static NamedResource rule = new NamedResource(CreditCardTest3.class);

    @Test
    public void testCanAcceptTerms() throws Exception {
        Device device = getTestDevice();
        Device createdDevice = createDevice(user, device);
        assertNotNull("created device", createdDevice);

        Collections.DeviceCollection devices = getDevices(user);
        assertNotNull("devices collection should not be null", devices);
        assertEquals("should have one device", 1, devices.getTotalResults());

        String pan = "9999445454545454";
        CreditCardInfo creditCardInfo = getTestCreditCardInfo(pan);

        CreditCard creditCard = createCreditCard(user, creditCardInfo);
        assertNotNull("card not created", creditCard);
        assertEquals("card not in expected state", "ELIGIBLE", creditCard.getState());

        //save acceptTerms
        final String acceptTermsUrl = creditCard.getAcceptTermsUrl();

        //get the same card but without sessionData
        creditCard = getCreditCard(creditCard);

        //update acceptTerms url
        creditCard.setAcceptTermsUrl(acceptTermsUrl);

        //accept terms
        creditCard = acceptTerms(creditCard);

        assertNotNull("card not successfully updated by accept terms", creditCard);
        assertEquals("card state", "PENDING_VERIFICATION", creditCard.getState());
    }
}


/* Copyright 2016 Urban Airship and Contributors */

package com.urbanairship.extension.events;

import com.urbanairship.UAirship;
import com.urbanairship.UrbanAirshipUtils;
import com.urbanairship.analytics.Analytics;
import com.urbanairship.analytics.CustomEvent;
import com.urbanairship.analytics.EventTestUtils;
import com.urbanairship.extension.BuildConfig;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.math.BigDecimal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = 21, constants = BuildConfig.class)
public class AccountEventTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        UAirship airship = UrbanAirshipUtils.mockAirship();
        Analytics analytics = mock(Analytics.class);
        when(airship.getAnalytics()).thenReturn(analytics);
    }

    /**
     * Test basic AccountEvent with no optional value or properties.
     *
     * @throws JSONException
     */
    @Test
    public void testBasicAccountEvent() throws JSONException {
        CustomEvent event = AccountEvent.createRegisteredEvent().track();

        EventTestUtils.validateEventValue(event, "event_name", AccountEvent.REGISTERED_ACCOUNT_EVENT);
        EventTestUtils.validateNestedEventValue(event, "properties", "ltv", "false");
    }

    /**
     * Test AccountEvent with optional value and properties.
     * @throws JSONException
     */
    @Test
    public void testAccountEvent() throws JSONException {
        CustomEvent event = AccountEvent.createRegisteredEvent()
                                        .setValue(new BigDecimal(123))
                                        .setTransactionId("Wednesday 11/4/2015")
                                        .setCategory("Premium")
                                        .track();

        EventTestUtils.validateEventValue(event, "event_name", AccountEvent.REGISTERED_ACCOUNT_EVENT);
        EventTestUtils.validateEventValue(event, "event_value", 123000000L);
        EventTestUtils.validateEventValue(event, "transaction_id", "Wednesday 11/4/2015");
        EventTestUtils.validateNestedEventValue(event, "properties", "ltv", "true");
        EventTestUtils.validateNestedEventValue(event, "properties", "category", "\"Premium\"");
    }

}

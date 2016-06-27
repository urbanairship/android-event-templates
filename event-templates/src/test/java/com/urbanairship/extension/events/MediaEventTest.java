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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = 21, constants = BuildConfig.class)
public class MediaEventTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        UAirship airship = UrbanAirshipUtils.mockAirship();
        Analytics analytics = mock(Analytics.class);
        when(airship.getAnalytics()).thenReturn(analytics);
    }

    /**
     * Test browsed event.
     *
     * @throws JSONException
     */
    @Test
    public void testBrowsedEventBasic() throws JSONException {
        CustomEvent event = MediaEvent.createBrowsedEvent().track();

        EventTestUtils.validateEventValue(event, "event_name", MediaEvent.BROWSED_CONTENT_EVENT);
        EventTestUtils.validateNestedEventValue(event, "properties", "ltv", "false");
    }

    /**
     * Test browsed content event with optional properties.
     *
     * @throws JSONException
     */
    @Test
    public void testBrowsedEvent() throws JSONException {
        CustomEvent event = MediaEvent.createBrowsedEvent()
                .setCategory("media-category")
                .setId("starred-content-ID 1")
                .setDescription("This is a starred content media event.")
                .setType("audio type")
                .setAuthor("The Cool UA")
                .setFeature(true)
                .setPublishedDate("November 4, 2015")
                .track();

        EventTestUtils.validateEventValue(event, "event_name", MediaEvent.BROWSED_CONTENT_EVENT);
        EventTestUtils.validateNestedEventValue(event, "properties", "ltv", "false");
        EventTestUtils.validateNestedEventValue(event, "properties", "category", "\"media-category\"");
        EventTestUtils.validateNestedEventValue(event, "properties", "id", "\"starred-content-ID 1\"");
        EventTestUtils.validateNestedEventValue(event, "properties", "description", "\"This is a starred content media event.\"");
        EventTestUtils.validateNestedEventValue(event, "properties", "type", "\"audio type\"");
        EventTestUtils.validateNestedEventValue(event, "properties", "author", "\"The Cool UA\"");
        EventTestUtils.validateNestedEventValue(event, "properties", "feature", true);
        EventTestUtils.validateNestedEventValue(event, "properties", "published_date", "\"November 4, 2015\"");
    }

    /**
     * Test starred content event.
     *
     * @throws JSONException
     */
    @Test
    public void testStarredEventBasic() throws JSONException {
        CustomEvent event = MediaEvent.createStarredEvent().track();

        EventTestUtils.validateEventValue(event, "event_name", MediaEvent.STARRED_CONTENT_EVENT);
        EventTestUtils.validateNestedEventValue(event, "properties", "ltv", "false");
    }

    /**
     * Test starred content event with optional properties.
     *
     * @throws JSONException
     */
    @Test
    public void testStarredEvent() throws JSONException {
        CustomEvent event = MediaEvent.createStarredEvent()
                                      .setCategory("media-category")
                                      .setId("starred-content-ID 1")
                                      .setDescription("This is a starred content media event.")
                                      .setType("audio type")
                                      .setAuthor("The Cool UA")
                                      .setFeature(true)
                                      .setPublishedDate("November 4, 2015")
                                      .track();

        EventTestUtils.validateEventValue(event, "event_name", MediaEvent.STARRED_CONTENT_EVENT);
        EventTestUtils.validateNestedEventValue(event, "properties", "ltv", "false");
        EventTestUtils.validateNestedEventValue(event, "properties", "category", "\"media-category\"");
        EventTestUtils.validateNestedEventValue(event, "properties", "id", "\"starred-content-ID 1\"");
        EventTestUtils.validateNestedEventValue(event, "properties", "description", "\"This is a starred content media event.\"");
        EventTestUtils.validateNestedEventValue(event, "properties", "type", "\"audio type\"");
        EventTestUtils.validateNestedEventValue(event, "properties", "author", "\"The Cool UA\"");
        EventTestUtils.validateNestedEventValue(event, "properties", "feature", true);
        EventTestUtils.validateNestedEventValue(event, "properties", "published_date", "\"November 4, 2015\"");
    }

    /**
     * Test shared content event.
     *
     * @throws JSONException
     */
    @Test
    public void testSharedEventBasic() throws JSONException {
        CustomEvent event = MediaEvent.createSharedEvent().track();

        EventTestUtils.validateEventValue(event, "event_name", MediaEvent.SHARED_CONTENT_EVENT);
        EventTestUtils.validateNestedEventValue(event, "properties", "ltv", "false");
    }

    /**
     * Test shared content event with optional properties.
     *
     * @throws JSONException
     */
    @Test
    public void testSharedEvent() throws JSONException {
        CustomEvent event = MediaEvent.createSharedEvent("facebook", "social")
                                      .setCategory("media-category")
                                      .setId("shared-content-ID 2")
                                      .setDescription("This is a shared content media event.")
                                      .setType("video type")
                                      .setAuthor("The Cool UA")
                                      .setFeature(true)
                                      .setPublishedDate("November 4, 2015")
                                      .track();

        EventTestUtils.validateEventValue(event, "event_name", MediaEvent.SHARED_CONTENT_EVENT);
        EventTestUtils.validateNestedEventValue(event, "properties", "ltv", "false");
        EventTestUtils.validateNestedEventValue(event, "properties", "source", "\"facebook\"");
        EventTestUtils.validateNestedEventValue(event, "properties", "medium", "\"social\"");
        EventTestUtils.validateNestedEventValue(event, "properties", "category", "\"media-category\"");
        EventTestUtils.validateNestedEventValue(event, "properties", "id", "\"shared-content-ID 2\"");
        EventTestUtils.validateNestedEventValue(event, "properties", "description", "\"This is a shared content media event.\"");
        EventTestUtils.validateNestedEventValue(event, "properties", "type", "\"video type\"");
        EventTestUtils.validateNestedEventValue(event, "properties", "author", "\"The Cool UA\"");
        EventTestUtils.validateNestedEventValue(event, "properties", "feature", true);
        EventTestUtils.validateNestedEventValue(event, "properties", "published_date", "\"November 4, 2015\"");
    }

    /**
     * Test consumed content event.
     *
     * @throws JSONException
     */
    @Test
    public void testConsumedEventBasic() throws JSONException {
        CustomEvent event = MediaEvent.createConsumedEvent().track();

        EventTestUtils.validateEventValue(event, "event_name", MediaEvent.CONSUMED_CONTENT_EVENT);
        EventTestUtils.validateNestedEventValue(event, "properties", "ltv", "false");
    }

    /**
     * Test consumed content event with optional properties.
     *
     * @throws JSONException
     */
    @Test
    public void testConsumedEvent() throws JSONException {
        CustomEvent event = MediaEvent.createConsumedEvent(2.99)
                                      .setCategory("media-category")
                                      .setId("consumed-content-ID 1")
                                      .setDescription("This is a consumed content media event.")
                                      .setType("audio type")
                                      .setAuthor("The Cool UA")
                                      .setFeature(true)
                                      .setPublishedDate("November 4, 2015")
                                      .track();

        EventTestUtils.validateEventValue(event, "event_name", MediaEvent.CONSUMED_CONTENT_EVENT);
        EventTestUtils.validateEventValue(event, "event_value", 2990000.0);
        EventTestUtils.validateNestedEventValue(event, "properties", "ltv", "true");
        EventTestUtils.validateNestedEventValue(event, "properties", "category", "\"media-category\"");
        EventTestUtils.validateNestedEventValue(event, "properties", "id", "\"consumed-content-ID 1\"");
        EventTestUtils.validateNestedEventValue(event, "properties", "description", "\"This is a consumed content media event.\"");
        EventTestUtils.validateNestedEventValue(event, "properties", "type", "\"audio type\"");
        EventTestUtils.validateNestedEventValue(event, "properties", "author", "\"The Cool UA\"");
        EventTestUtils.validateNestedEventValue(event, "properties", "feature", true);
        EventTestUtils.validateNestedEventValue(event, "properties", "published_date", "\"November 4, 2015\"");
    }
}

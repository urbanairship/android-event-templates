/* Copyright 2016 Urban Airship and Contributors */

package com.urbanairship.analytics;

import org.json.JSONException;
import org.json.JSONObject;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class EventTestUtils {

    /**
     * Verifies an event value.
     *
     * @param event The event to verify.
     * @param key The event's data field to check.
     * @param expectedValue The expected value.
     * @throws JSONException
     */
    public static void validateEventValue(Event event, String key, String expectedValue) throws JSONException {
        if (expectedValue == null) {
            assertNull("Event's value should not be set.", event.getEventData().opt(key));
        } else {
            assertEquals("Event's value for " + key + " is unexpected.", expectedValue, event.getEventData().getString(key));
        }
    }

    /**
     * Verifies an event value.
     *
     * @param event The event to verify.
     * @param key The event's data field to check.
     * @param expectedValue The expected value.
     * @throws JSONException
     */
    public static void validateEventValue(Event event, String key, long expectedValue) throws JSONException {
        assertEquals("Event's value for " + key + " is unexpected.", expectedValue, event.getEventData().getLong(key));
    }

    /**
     * Verifies an event value.
     *
     * @param event The event to verify.
     * @param key The event's data field to check.
     * @param expectedValue The expected value.
     * @throws JSONException
     */
    public static void validateEventValue(Event event, String key, double expectedValue) throws JSONException {
        assertEquals("Event's value for " + key + " is unexpected.", expectedValue, event.getEventData().getDouble(key));
    }

    /**
     * Verifies an event value.
     *
     * @param event The event to verify.
     * @param key The event's data field to check.
     * @param expectedValue The expected value.
     * @throws JSONException
     */
    public static void validateEventValue(Event event, String key, boolean expectedValue) throws JSONException {
        assertEquals("Event's value for " + key + " is unexpected.", expectedValue, event.getEventData().getBoolean(key));
    }

    /**
     * Verifies a nested event value.
     *
     * @param event The event to verify.
     * @param key The event's data field to check.
     * @param nestedKey the nested data field to check.
     * @param expectedValue The expected value.
     * @throws JSONException
     */
    public static void validateNestedEventValue(Event event, String key, String nestedKey, boolean expectedValue) throws JSONException {
        assertEquals("Event's value for " + key + " is unexpected.", expectedValue, event.getEventData().getJSONObject(key).getBoolean(nestedKey));
    }

    /**
     * Verifies a nested event value.
     *
     * @param event The event to verify.
     * @param key The event's data field to check.
     * @param nestedKey the nested data field to check.
     * @param expectedValue The expected value.
     * @throws JSONException
     */
    public static void validateNestedEventValue(Event event, String key, String nestedKey, long expectedValue) throws JSONException {
        assertEquals("Event's value for " + key + " is unexpected.", expectedValue, event.getEventData().getJSONObject(key).getLong(nestedKey));
    }

    /**
     * Verifies a nested event value.
     *
     * @param event The event to verify.
     * @param key The event's data field to check.
     * @param nestedKey the nested data field to check.
     * @param expectedValue The expected value.
     * @throws JSONException
     */
    public static void validateNestedEventValue(Event event, String key, String nestedKey, String expectedValue) throws JSONException {
        assertEquals("Event's value for " + key + " is unexpected.", expectedValue, event.getEventData().getJSONObject(key).getString(nestedKey));
    }

    /**
     * Verifies a nested event value.
     *
     * @param event The event to verify.
     * @param key The event's data field to check.
     * @param nestedKey the nested data field to check.
     * @param expectedValue The expected value.
     * @throws JSONException
     */
    public static void validateNestedEventValue(Event event, String key, String nestedKey, double expectedValue) throws JSONException {
        assertEquals("Event's value for " + key + " is unexpected.", expectedValue, event.getEventData().getJSONObject(key).getDouble(nestedKey));
    }

    /**
     * Gets the event's data.
     * @param event The event.
     * @return The event's data.
     */
    public static JSONObject getEventData(Event event) {
        return event.getEventData();
    }
}

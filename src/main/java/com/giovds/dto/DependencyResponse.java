package com.giovds.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Map;

/**
 * The dependency returned by the Maven Central response
 *
 * @param id        GAV (groupId:artifactId:version)
 * @param g         groupId
 * @param a         artifactId
 * @param v         version
 * @param timestamp The date this GAV id was released to Maven Central
 */
public record DependencyResponse(String id, String g, String a, String v, long timestamp) {

    /**
     * Map the response from Maven Central to a {@link DependencyResponse}
     *
     * @param doc The response from Maven Central
     * @return A {@link DependencyResponse} object
     */
    public static DependencyResponse mapResponseToDependency(final Map<String, Object> doc) {
        return new DependencyResponse(
                (String) doc.get("id"),
                (String) doc.get("g"),
                (String) doc.get("a"),
                (String) doc.get("v"),
                (long) doc.get("timestamp")
        );
    }

    /**
     * Return the timestamp as {@link LocalDate}
     *
     * @return the {@link LocalDate}
     */
    public LocalDate getDateTime() {
        return Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDate();
    }
}

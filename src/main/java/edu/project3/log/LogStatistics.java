package edu.project3.log;

import java.util.Map;

public class LogStatistics {
    private Long totalRequests;
    private Double avgResponseSize;
    private Map<String, Long> resourceCounts;
    private Map<Integer, Long> responseCodeCount;
    private Map<String, Long> uniqueIpCounts;
    private Map<String, Long> httpUserAgentCount;

    public Map<String, Long> getUniqueIpCounts() {
        return uniqueIpCounts;
    }

    public void setUniqueIpCounts(Map<String, Long> uniqueIpCounts) {
        this.uniqueIpCounts = uniqueIpCounts;
    }

    public Long getTotalRequests() {
        return totalRequests;
    }

    public void setTotalRequests(Long totalRequests) {
        this.totalRequests = totalRequests;
    }

    public Double getAvgResponseSize() {
        return avgResponseSize;
    }

    public void setAvgResponseSize(Double avgResponseSize) {
        this.avgResponseSize = avgResponseSize;
    }

    public Map<String, Long> getResourceCounts() {
        return resourceCounts;
    }

    public void setResourceCounts(Map<String, Long> resourceCounts) {
        this.resourceCounts = resourceCounts;
    }

    public Map<Integer, Long> getResponseCodeCounts() {
        return responseCodeCount;
    }

    public void setResponseCodeCount(Map<Integer, Long> responseCodeCount) {
        this.responseCodeCount = responseCodeCount;
    }

    public void setHttpUserAgentCount(Map<String, Long> httpUserAgentCount) {
        this.httpUserAgentCount = httpUserAgentCount;
    }

    public Map<Integer, Long> getResponseCodeCount() {
        return responseCodeCount;
    }

    public Map<String, Long> getHttpUserAgentCount() {
        return httpUserAgentCount;
    }
}

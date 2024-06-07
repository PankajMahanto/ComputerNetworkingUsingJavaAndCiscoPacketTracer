import java.util.ArrayList;
import java.util.List;

public class IterativeDNSQuery {
    private List<DNSRecord> dnsRecords;

    public IterativeDNSQuery() {
        dnsRecords = new ArrayList<>();
        dnsRecords.add(new DNSRecord("example.com", "93.184.216.34"));
        dnsRecords.add(new DNSRecord("example.org", "2606:2800:220:1:248:1893:25c8:1946"));
        dnsRecords.add(new DNSRecord("www.google.com", "8.8.8.8"));
        dnsRecords.add(new DNSRecord("www.bing.com", "202.89.233.101"));
    }

    public String findIpAddress(String domain) {
        for (DNSRecord record : dnsRecords) {
            if (record.getDomain().equals(domain)) {
                return record.getIpAddress();
            }
        }
        return "Not found";
    }

    public static void main(String[] args) {
        IterativeDNSQuery query = new IterativeDNSQuery();
        System.out.println("example.com: " + query.findIpAddress("example.com"));
        System.out.println("example.org: " + query.findIpAddress("example.org"));
        System.out.println("google.com: " + query.findIpAddress("www.google.com"));
        System.out.println("bing.com: " + query.findIpAddress("www.bing.com"));
    }

}

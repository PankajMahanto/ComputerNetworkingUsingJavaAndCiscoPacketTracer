import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSIterativeDNSQuery {
    private List<DNSRecord> dnsRecords;

    public BFSIterativeDNSQuery() {
        dnsRecords = new ArrayList<>();
        dnsRecords.add(new DNSRecord("example.com", "93.184.216.34"));
        dnsRecords.add(new DNSRecord("example.org", "2606:2800:220:1:248:1893:25c8:1946"));
    }

    public String findIpAddress(String domain) {
        Queue<DNSRecord> queue = new LinkedList<>(dnsRecords);

        while (!queue.isEmpty()) {
            DNSRecord current = queue.poll();
            if (current.getDomain().equals(domain)) {
                return current.getIpAddress();
            }
        }
        return "Not found";
    }

    public static void main(String[] args) {
        BFSIterativeDNSQuery query = new BFSIterativeDNSQuery();
        System.out.println("example.com: " + query.findIpAddress("example.com"));
        System.out.println("example.org: " + query.findIpAddress("example.org"));
    }
}



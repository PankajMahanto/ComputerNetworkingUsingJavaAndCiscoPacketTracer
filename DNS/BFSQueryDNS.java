import java.util.LinkedList;
import java.util.Queue;

public class BFSQueryDNS {
    private DNSRecord[] dnsRecords;

    public BFSQueryDNS() {
        dnsRecords = new DNSRecord[2];
        dnsRecords[0] = new DNSRecord("example.com", "93.184.216.34");
        dnsRecords[1] = new DNSRecord("example.org", "2606:2800:220:1:248:1893:25c8:1946");
    }

    public String findIpAddress(String domain) {
        Queue<DNSRecord> queue = new LinkedList<>();
        for (DNSRecord record : dnsRecords) {
            queue.add(record);
        }

        while (!queue.isEmpty()) {
            DNSRecord current = queue.poll();
            if (current.getDomain().equals(domain)) {
                return current.getIpAddress();
            }
        }
        return "Not found";
    }

    public static void main(String[] args) {
        BFSQueryDNS query = new BFSQueryDNS();
        System.out.println("example.com: " + query.findIpAddress("example.com"));
        System.out.println("example.org: " + query.findIpAddress("example.org"));
    }
}



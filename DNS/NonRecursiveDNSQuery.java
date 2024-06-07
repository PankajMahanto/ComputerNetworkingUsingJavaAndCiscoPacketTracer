
import java.util.Arrays;
import java.util.List;

public class NonRecursiveDNSQuery {
    private DNSRecord[] dnsRecords;

    public NonRecursiveDNSQuery() {
        dnsRecords = new DNSRecord[2];
        dnsRecords[0] = new DNSRecord("example.com", "93.184.216.34");
        dnsRecords[1] = new DNSRecord("example.org", "2606:2800:220:1:248:1893:25c8:1946");
    }

    public String findIpAddress(String domain) {
        // Start the recursive BFS with the initial list of DNS records
        return findIpAddressRecursive(Arrays.asList(dnsRecords), domain);
    }

    private String findIpAddressRecursive(List<DNSRecord> records, String domain) {
        if (records.isEmpty()) {
            return "Not found";
        }

        // Process the current record
        DNSRecord current = records.get(0);
        if (current.getDomain().equals(domain)) {
            return current.getIpAddress();
        }

        // Recurse with the rest of the records
        return findIpAddressRecursive(records.subList(1, records.size()), domain);
    }

    public static void main(String[] args) {
        NonRecursiveDNSQuery query = new NonRecursiveDNSQuery();
        System.out.println("example.com: " + query.findIpAddress("example.com"));
        System.out.println("example.org: " + query.findIpAddress("example.org"));
    }
}



//Non Recursive Part Two

/* 
import java.util.Stack;

public class NonRecursiveDNSQuery {
    private DNSRecord[] dnsRecords;

    public NonRecursiveDNSQuery() {
        dnsRecords = new DNSRecord[2];
        dnsRecords[0] = new DNSRecord("example.com", "93.184.216.34");
        dnsRecords[1] = new DNSRecord("example.org", "2606:2800:220:1:248:1893:25c8:1946");
    }

    public String findIpAddress(String domain) {
        // Create a stack to simulate the recursion
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        while (!stack.isEmpty()) {
            int index = stack.pop();

            if (index >= dnsRecords.length) {
                return "Not found";
            }

            if (dnsRecords[index].getDomain().equals(domain)) {
                return dnsRecords[index].getIpAddress();
            }

            stack.push(index + 1);
        }

        return "Not found";
    }

    public static void main(String[] args) {
        NonRecursiveDNSQuery query = new NonRecursiveDNSQuery();
        System.out.println("example.com: " + query.findIpAddress("example.com"));
        System.out.println("example.org: " + query.findIpAddress("example.org"));
    }
}
*/
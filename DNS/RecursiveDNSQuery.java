public class RecursiveDNSQuery {
    private DNSRecord[] dnsRecords;

    public RecursiveDNSQuery() {
        dnsRecords = new DNSRecord[2];
        dnsRecords[0] = new DNSRecord("example.com", "93.184.216.34");
        dnsRecords[1] = new DNSRecord("example.org", "2606:2800:220:1:248:1893:25c8:1946");
    }

    public String findIpAddress(String domain, int index) {
        if (index >= dnsRecords.length) {
            return "Not found";
        }
        if (dnsRecords[index].getDomain().equals(domain)) {
            return dnsRecords[index].getIpAddress();
        }
        return findIpAddress(domain, index + 1);
    }
    public static void main(String[] args) {
        RecursiveDNSQuery query=new RecursiveDNSQuery();
        System.out.println("example.com: " + query.findIpAddress("example.com",0));
        System.out.println("example.org: " + query.findIpAddress("example.org",0));
    }

}

package works.weave.socks.orders.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URI;

@ConfigurationProperties
public class OrdersConfigurationProperties {
    private String domain = "";

    public URI getPaymentUri() {
//return new ServiceUri(new Hostname("payment"), new Domain(domain), "/paymentAuth").toUri();
	return new ServiceUri(new Hostname("sock-shop-payment-03a9e0580eb5eb2b7.7d67968.vpc-lattice-svcs.us-west-2.on.aws"), new Domain(domain), "/").toUri();
    }

    public URI getShippingUri() {
        return new ServiceUri(new Hostname("shipping"), new Domain(domain), "/shipping").toUri();
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    private class Hostname {
        private final String hostname;

        private Hostname(String hostname) {
            this.hostname = hostname;
        }

        @Override
        public String toString() {
            if (hostname != null && !hostname.equals("")) {
                return hostname;
            } else {
                return "";
            }
        }
    }

    private class Domain {
        private final String domain;

        private Domain(String domain) {
            this.domain = domain;
        }

        @Override
        public String toString() {
            if (domain != null && !domain.equals("")) {
                return "." + domain;
            } else {
                return "";
            }
        }
    }

    private class ServiceUri {
        private final Hostname hostname;
        private final Domain domain;
        private final String endpoint;

        private ServiceUri(Hostname hostname, Domain domain, String endpoint) {
            this.hostname = hostname;
            this.domain = domain;
            this.endpoint = endpoint;
        }

        public URI toUri() {
            return URI.create(wrapHTTP(hostname.toString() + domain.toString()) + endpoint);
        }

        private String wrapHTTP(String host) {
            return "http://" + host;
        }

        @Override
        public String toString() {
            return "ServiceUri{" +
                    "hostname=" + hostname +
                    ", domain=" + domain +
                    '}';
        }
    }
}

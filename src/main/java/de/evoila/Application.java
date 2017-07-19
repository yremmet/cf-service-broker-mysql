/**
 * 
 */
package de.evoila;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.util.Assert;

import de.evoila.cf.cpi.custom.props.DomainBasedCustomPropertyHandler;
import de.evoila.cf.cpi.custom.props.MySQLCustomPropertyHandler;

/**
 * 
 * @author Johannes Hiemer.
 *
 */

@RefreshScope
@SpringBootApplication
@EnableMongoRepositories(basePackages={"de.evoila.cf.cpi.openstack.custom", "de.evoila.cf.broker.persistence.mongodb.repository"})
public class Application {

	@Bean(name = "customProperties")
	public Map<String, String> customProperties() {
		Map<String, String> customProperties = new HashMap<String, String>();

		return customProperties;
	}

	@Value("${openstack.log_port:0}")
	private String logPort;

	@Value("${openstack.log_host:localhost}")
	private String logHost;

	@Bean
	public DomainBasedCustomPropertyHandler domainPropertyHandler() {
		return new MySQLCustomPropertyHandler(logHost, logPort);
	}

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(Application.class);
		springApplication.addListeners(new ApplicationPidFileWriter());
		ApplicationContext ctx = springApplication.run(args);

		Assert.notNull(ctx);
	}

}
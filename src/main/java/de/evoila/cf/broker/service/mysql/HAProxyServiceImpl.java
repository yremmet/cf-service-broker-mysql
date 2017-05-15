/**
 * 
 */
package de.evoila.cf.broker.service.mysql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import de.evoila.cf.broker.model.Mode;
import de.evoila.cf.broker.model.ServerAddress;
import de.evoila.cf.broker.service.HAProxyService;

/**
 * @author Rene Schollmeyer
 *
 */

@Service
public class HAProxyServiceImpl extends HAProxyService {

	@Override
	public Mode getMode(ServerAddress serverAddress) {
		return Mode.TCP;
	}
	
	@Override
	public List<String> getOptions(ServerAddress serverAddress) {
		return new ArrayList<String>();
	}
}

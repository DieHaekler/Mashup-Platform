package ch.ffhs.inf09.pa.mashup_platform.web.view;

import ch.ffhs.inf09.pa.mashup_platform.common.db.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;
import ch.ffhs.inf09.pa.mashup_platform.web.Environment;
import ch.ffhs.inf09.pa.mashup_platform.web.model.*;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

public class ViewMashupOverviewJSONTest
{	
	@Test
	public void mainTest() throws ExceptionMP{
		MockHttpServletRequest request = new MockHttpServletRequest();
		Environment env = new Environment(request, Config.getFilepathSystem() + "WebContent");		
		ViewMashupOverviewJSON view = new ViewMashupOverviewJSON(new ModelMashupOverview(env));
		assertNotNull(view.getContent());
		env.close();
	}
	
}
package com.goldcow.emanage.juntity.com;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.goldcow.emanage.system.web.LoginController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/root-context.xml","classpath:spring/appServlet/servlet-context.xml"})  
@ActiveProfiles("dev")
public abstract class JUnitTestBase<T> {
	
    @Resource
    private LoginController loginController;

    private HttpServletRequest request = null;
	
	private HttpServletResponse response = null;

	private HttpSession session = null;

	private MockServletConfig serletConfig;


    public void initHttpServlet(){ 
    	this.serletConfig = new MockServletConfig();
    	this.request = new MockHttpServletRequest();
    	this.response = new MockHttpServletResponse();
    	this.session = request.getSession();
    }

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//System.out.println("类之前执行");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//System.out.println("类之后执行");
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		//System.out.println("之后执行");
	}
	

    public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

}

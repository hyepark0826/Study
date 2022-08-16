package com.my.control;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//@WebMvcTest(DemoController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class DemoControllerTest {
	@Autowired
	private MockMvc mockMvc; // 모의 객체 : "흉내"내는 "가짜" 모듈
	@Test
	void testGreeting() throws Exception {
		MockHttpServletRequestBuilder  mockRequestBuilder = MockMvcRequestBuilders.get("/greeting")
				                            .accept(org.springframework.http.MediaType.APPLICATION_JSON);
		ResultActions resultActions = mockMvc.perform(mockRequestBuilder);
		resultActions.andExpect(MockMvcResultMatchers.status().isOk());
		resultActions.andExpect(MockMvcResultMatchers.content().string("welcome"));
	}
	
	@Test
	void testUseRepository() throws Exception {
		MockHttpServletRequestBuilder  mockRequestBuilder = MockMvcRequestBuilders.get("/userepository")
                .accept(org.springframework.http.MediaType.APPLICATION_JSON);
		int expectedCount = 7;
		ResultActions resultActions = mockMvc.perform(mockRequestBuilder);
		resultActions.andExpect(MockMvcResultMatchers.status().isOk());
		org.hamcrest.Matcher<Integer> matcher;
		ResultMatcher resultMatcher;
		
		matcher = org.hamcrest.CoreMatchers.is(expectedCount);
		resultMatcher = jsonPath("totalCnt", matcher);
		resultActions.andExpect(resultMatcher);
	}
}

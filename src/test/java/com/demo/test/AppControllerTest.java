package com.demo.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.demo.controller.AppController;
import com.demo.profiles.WeatherService;
import com.demo.repository.IRepository;
import com.demo.repository.PersonRepository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value=AppController.class)
public class AppControllerTest {
	
	@Autowired 
	private MockMvc mockMvc;
	@MockBean 
	private javax.sql.DataSource datasource;
	@MockBean 
	private PersonRepository personRepository;
	@MockBean 
	private IRepository iRepository;
	@MockBean 
	private WeatherService weatherService;
	
	/*Check all mock/objects are not null and controller initializedCorrectly  */
	@Test
    public void controllerInitializedCorrectly() {
		
        assertThat(personRepository).isNotNull();
        assertThat(iRepository).isNotNull();
        assertThat(datasource).isNotNull();
        assertThat(weatherService).isNotNull();
        assertThat(mockMvc).isNotNull();
    }
	@Test
    public void testPing() throws Exception {
        this.mockMvc.perform(get("/api/ping")
        		.accept(MediaType.TEXT_PLAIN))
        		//.andDo(print())  //When test pass and you want see response use this.
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN+";charset=UTF-8"))
                .andExpect(content().string("Hello World"));

    }
	@Test
    public void testIsTrue() throws Exception {
		mockMvc.perform(get("/api/isTrue"))
				//.andDo(print())  //When test pass and you want see response use this.
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(content().string("false"));
	}
	@Test
    public void showData() throws Exception {
		mockMvc.perform(get("/api/showData"))
				.andDo(print());  //When test pass and you want see response use this.
				//.andExpect(status().isOk())
				//.andExpect(content().contentType("application/json;charset=UTF-8"))
				//.andExpect(content().string("false"));
	}
}

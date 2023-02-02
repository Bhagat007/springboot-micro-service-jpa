package com.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.exception.BaseException;
import com.demo.model.Person;
import com.demo.profiles.WeatherService;
import com.demo.repository.IRepository;
import com.demo.repository.PersonRepository;

@RestController
@RequestMapping("/api")
public class AppController {
	private static final org.slf4j.Logger logger = LoggerFactory
			.getLogger(AppController.class);

	@Autowired
	public DataSource dataSource;

	@Autowired
	public IRepository iRepository;

	PersonRepository personRepository;

	@Autowired
	private WeatherService weatherService;

	/*
	 * Remember Autowiring happens after coinstruction of object therefore they
	 * will not be set until after the constructor has completed.
	 * 
	 * That means above class level @Autowired will contain null until
	 * contructor exec.
	 * 
	 * You can not use above @Autowired object within constructor exec cycle.
	 * 
	 * But @Autowired PersonRepository personRepository will be fine as its
	 * inside constructor.
	 */
	public AppController(@Autowired PersonRepository personRepository) {
		if (personRepository == null) {
			throw new IllegalArgumentException(
					"PersonRepository cannot be null");
		} else {
			this.personRepository = personRepository;
		}
	}

	@RequestMapping("/ping")
	public String ping() {
		return "Hello World";
	}

	/*
	 * @RequestMapping("/isTrue") public Boolean isTrue() { return false; }
	 */

	
	  @RequestMapping("/isFalse") public Boolean isFalse() { return true; }
	 


	@RequestMapping("/showData")
	public List<Map<String, Object>> getProcCallResult() {
		final String timestamp = "timestamp";
		SimpleJdbcCall call = new SimpleJdbcCall(dataSource)
				.withProcedureName("showData");
		MapSqlParameterSource inputParams = new MapSqlParameterSource();
		Map<String, Object> rs = call.execute(inputParams);
		List<Map<String, Object>> map = (List<Map<String, Object>>) rs
				.get("#result-set-1");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map1 : map) {
			HashMap<String, Object> localMap = new HashMap<String, Object>();
			localMap.put("id", map1.get("id"));
			localMap.put("firstname", map1.get("firstname"));
			localMap.put("lastname", map1.get("lastname"));
			// toString convert the unix time to "2017-11-04 13:27:19.367"
			localMap.put(timestamp,
					map1.get(timestamp) != null ? map1.get(timestamp)
							.toString() : "");
			list.add(localMap);
		}
		return list;
	}

	@RequestMapping("/showDataMysql")
	public List<Map<String, Object>> getResult() {

		SimpleJdbcCall call = new SimpleJdbcCall(dataSource)
				.withProcedureName("showData");
		MapSqlParameterSource inputParams = new MapSqlParameterSource();
		Map<String, Object> rs = call.execute(inputParams);
		List<Map<String, Object>> map = (List<Map<String, Object>>) rs
				.get("#result-set-1");

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map2 : map) {
			Map<String, Object> map3 = new HashMap<String, Object>();
			// toString convert the unix time to "2017-11-04 13:27:19.367"
			map3.put("date", map2.get("date").toString());
			map3.put("name", map2.get("name"));
			list.add(map3);
		}
		return list;
	}

	@RequestMapping("/getPersonRepoResult")
	public @ResponseBody List<com.demo.model.Person> getPersonRepoResult()
			throws IllegalAccessException {
		logger.info("getPersonRepoResult $$$$$$$$$$$$$$$$$$");
		List<com.demo.model.Person> list = personRepository.findAll();
		if (iRepository == null) {
			throw new IllegalAccessException("iRepository can not be null");
		}

		logger.info("iRepository.findByAddress() :: {0} ",
				iRepository.findByName("BTML"));
		return list;
	}

	@RequestMapping("/getAddressRepoResult")
	public @ResponseBody List<com.demo.model.Address> getAddressRepoResult() {
		return iRepository.findAll();
	}

	@RequestMapping("/getByAddress")
	public @ResponseBody List<com.demo.model.Address> getByAddress() {
		return iRepository.findByName("BTML");
	}

	@RequestMapping("/savePerson")
	public Person savePerson(
			@RequestParam(value = "firstName") String firstName,
			@RequestParam(value = "lastName") String lastName) {

		com.demo.model.Person person = new com.demo.model.Person(firstName,
				lastName, new Date());
		personRepository.save(person);
		return person;
	}

	@RequestMapping("/testProfiler")
	public String testProfiler(
			@AuthenticationPrincipal final UserDetails userDetails) {
		userDetails.getUsername();
		userDetails.getPassword();

		return weatherService.forecast() + " UserName is :"
				+ userDetails.getUsername() + "Password is : "
				+ userDetails.getPassword();
	}

}
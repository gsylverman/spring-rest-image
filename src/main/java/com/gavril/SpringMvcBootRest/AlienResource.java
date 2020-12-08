package com.gavril.SpringMvcBootRest;

import java.io.IOException;
import java.util.LinkedList;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
public class AlienResource {

	@RequestMapping(value="list",method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public LinkedList<SomeItem> getAliens() {

		LinkedList<SomeItem> list = new LinkedList<SomeItem>();

		String url ="http://localhost:8080/image/";
		list.add(new SomeItem("George", 36, url + 1));
		list.add(new SomeItem("Mihai", 37, url + 2));
		list.add(new SomeItem("Daniel", 39, url + 3));
		list.add(new SomeItem("Ionel", 26, url + 4));
		list.add(new SomeItem("Cristian", 35, url + 5));

		return list;
	}

	@RequestMapping(value = "/image/{id}", method = RequestMethod.GET,
			produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public ResponseEntity<InputStreamResource> getImage(@PathVariable("id") String id) throws IOException {

		var imgFile = new ClassPathResource("images/"+ id +".jpg");

		return ResponseEntity
				.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(new InputStreamResource(imgFile.getInputStream()));
	}
}

class SomeItem {
	private String name;
	private int age;
	private String image;
	public SomeItem(String name, int age, String image) {
		this.name = name;
		this.age = age;
		this.image = image;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}

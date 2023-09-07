package com.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dto.ImageUploadResDTO;
import com.entity.Image;
import com.repository.ImageRepository;
import com.util.ImageUtility;

@RestController
//@CrossOrigin(origins = "http://localhost:8082") open for specific port
@CrossOrigin() // open for all ports
@RequestMapping("/api")
public class ImageController {

	@Autowired
	ImageRepository imageRepository;

	@PostMapping("/uploadImage")
	public ResponseEntity<ImageUploadResDTO> uplaodImage(@RequestParam("image") MultipartFile file) throws IOException {

		Image img = imageRepository.save(Image.builder().name(file.getOriginalFilename()).type(file.getContentType())
				.image(ImageUtility.compressImage(file.getBytes())).build());
		return ResponseEntity.status(HttpStatus.OK).body(new ImageUploadResDTO(img.getImageId(), img.getName()));
	}

	@GetMapping(path = { "/get/image/info/{name}" })
	public Image getImageDetails(@PathVariable("name") String name) throws IOException {

		final Optional<Image> dbImage = imageRepository.findByName(name);

		return Image.builder().name(dbImage.get().getName()).type(dbImage.get().getType())
				.image(ImageUtility.decompressImage(dbImage.get().getImage())).build();
	}

	@GetMapping(path = { "/get/image/{name}" })
	public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException {

		final Optional<Image> dbImage = imageRepository.findByName(name);

		return ResponseEntity.ok().contentType(MediaType.valueOf(dbImage.get().getType()))
				.body(ImageUtility.decompressImage(dbImage.get().getImage()));
	}
}
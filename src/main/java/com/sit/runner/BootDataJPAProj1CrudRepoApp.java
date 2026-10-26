package com.sit.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sit.Service.IDoctorService;
import com.sit.entity.Doctor;

@Component
public class BootDataJPAProj1CrudRepoApp implements CommandLineRunner {
	@Autowired
	private IDoctorService service;
	
	
	@Override
	public void run(String... args) throws Exception {
		try {
			Doctor doc=new Doctor();
			doc.setDocName("sairam");
			doc.setSpecialization("MD_Cardio");
			doc.setIncome(90000.00);

			String Result=service.registerDoctor(doc);
			System.out.println(Result);

		}
		catch(Exception e) {e.printStackTrace();}

	}

}

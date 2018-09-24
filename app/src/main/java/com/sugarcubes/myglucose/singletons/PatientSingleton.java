package com.sugarcubes.myglucose.singletons;

import com.sugarcubes.myglucose.entities.ApplicationUser;
import com.sugarcubes.myglucose.entities.Doctor;
import com.sugarcubes.myglucose.entities.ExerciseEntry;
import com.sugarcubes.myglucose.entities.GlucoseEntry;
import com.sugarcubes.myglucose.entities.MealEntry;

import java.util.ArrayList;

public class PatientSingleton extends ApplicationUser
{
	private static PatientSingleton singleton;

	private String doctorEmail;
	protected Doctor doctor;

	public ArrayList<GlucoseEntry> glucoseEntries;
	public ArrayList<MealEntry> mealEntries;
	public ArrayList<ExerciseEntry> exerciseEntries;
	public String firstname, lastname, phonenumber, city, state, address;
	public double weight, height;


	public PatientSingleton()
	{
		// Instantiate the doctor:
		doctorEmail = "";
		doctor = new Doctor();
		// Instantiate all ArrayLists:
		glucoseEntries = new ArrayList<>();
		mealEntries = new ArrayList<>();
		exerciseEntries = new ArrayList<>();
		firstname = "";
		lastname = "";
		phonenumber = "";
		city = "";
		state = "";
		address = "";
		weight = 0;
		height = 0;

	} // constructor


	// Since there should only be 1 user, we make in impossible to create more than 1 instance
	//		of the class.
	public static PatientSingleton getInstance()
	{
		if( singleton == null )
			singleton = new PatientSingleton();

		return singleton;

	} // getInstance

	public Doctor getDoctor()
	{
		return doctor;
	}

	public void setDoctor( Doctor doctor )
	{
		this.doctor = doctor;
	}

	public ArrayList<GlucoseEntry> getGlucoseEntries()
	{
		return glucoseEntries;
	}

	public ArrayList<MealEntry> getMealEntries()
	{
		return mealEntries;
	}

	public ArrayList<ExerciseEntry> getExerciseEntries()
	{
		return exerciseEntries;
	}

	@Override
	public String toString()
	{
		String doctorString = doctor != null ? doctor.toString() : "";
		return super.toString() +
				"\nPatientSingleton{" +
				"doctor=" + doctorString +
				", glucoseEntries=" + glucoseEntries +
				", mealEntries=" + mealEntries +
				", exerciseEntries=" + exerciseEntries +
				'}';
	}

} // class

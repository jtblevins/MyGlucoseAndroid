package com.sugarcubes.myglucose.entities;

public class Doctor extends ApplicationUser
{
	protected String DegreeAbbreviation;

	public Doctor()
	{

	}

	public String getDegreeAbbreviation()
	{
		return DegreeAbbreviation;
	}

	public void setDegreeAbbreviation( String degreeAbbreviation )
	{
		DegreeAbbreviation = degreeAbbreviation;
	}

} // class
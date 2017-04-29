

public enum MedicalSpecialty {
	GENERAL_MEDICINE, PEDIATRICS, ONCOLOGY;
	
	public MedicalSpecialty getFromString(String specialty)
	{
		if (specialty == MedicalSpecialty.GENERAL_MEDICINE.toString())
		{
			return GENERAL_MEDICINE;
		}
		else if(specialty == MedicalSpecialty.PEDIATRICS.toString())
		{
			return PEDIATRICS;
		}
		else
		{
			return ONCOLOGY;
		}
	}
}

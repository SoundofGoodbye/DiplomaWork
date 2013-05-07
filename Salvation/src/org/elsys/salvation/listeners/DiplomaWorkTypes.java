package org.elsys.salvation.listeners;

public enum DiplomaWorkTypes
{
	SOFTWARE
	{
		public String toString()
		{
			return "Software";
		}
	},
	HARDWARE
	{
		public String toString()
		{
			return "Hardware";
		}
	},
	COMMUNICATION
	{
		public String toString()
		{
			return "Communications";
		}
	};
}

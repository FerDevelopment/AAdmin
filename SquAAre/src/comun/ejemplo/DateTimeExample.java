
package comun.ejemplo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;

public class DateTimeExample
{

	/*
	 * La clase Duration mide una cantidad de tiempo en segundos y
	 * nanosegundos, mientras que la clase Period mide el tiempo en a�os,
	 * meses y d�as. El m�todo atStartofDay() a�ade la hora de medianoche
	 * a la fecha local.
	 * 
	 * Obtenemos el objeto Period como la diferencia entre dos fechas,
	 * mientras que obtenemos la diferencia entre dos instantes como el
	 * objeto Duration utilizando el m�todo between(). Se prefiere el
	 * m�todo Duration para cantidades de tiempo m�s cortas.
	 * 
	 * La duraci�n diff se convierte en d�as utilizando toDays(). Del
	 * mismo modo, podemos obtener las unidades de fecha de Period
	 * utilizando getYears(), getMonths, y getDays().
	 */

	public static void main(String[] args)
	{

		// LocalDate
		localDateExample();

		// LocalTime
		localTimeExample();

		// LocalDateTime
		localDateTimeExample();

		// ZonedDateTime
		// zonedDateTimeExample();

		// Period
		periodExample();

		// Duration
		durationExample();

		// Date time zone
		//zonedDateTimeExample();

	}




	private static void localDateExample()
	{
		LocalDate localDate = LocalDate.now(); // Fecha Actual sin hora
		System.out.println(localDate.toString());// Imprimimos la fecha

		/*
		 * ****************
		 */
		LocalDate hoy = LocalDate.now();// Fecha Actual sin hora
		System.out.println(hoy.toString());// Imprimimos la fecha
		System.out.println(hoy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));// Establecemos formato
																					// 10/04/2024
		System.out.println(hoy.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)// Establecemos
																							// formato 10 abr
																							// 2024
		));

		/******************/
		LocalDate localDateOf = LocalDate.of(2024, 04, 1);// Fecha sin hora creada con par�metros (a�o, mes,
															// dia)
		System.out.println(localDateOf.toString()); // 2024-04-1

		LocalDate datePlus = localDateOf.plusDays(7);// Sumamos 7 d�as a la fecha creada anteriormente
		System.out.println(datePlus.toString()); // 2024-04-8

		LocalDate dateMinus = localDateOf.minusDays(7);// Restamos 7 d�as a la fecha creada anteriormente (
														// 2024-04-1)
		System.out.println(dateMinus.toString()); // 2024-03-25

		boolean isBefore = LocalDate.of(2024, 1, 10).isBefore(LocalDate.of(2024, 1, 1));
		System.out.println(isBefore); // false

		boolean isAfter = LocalDate.of(2024, 2, 10).isAfter(LocalDate.of(2024, 2, 1));
		System.out.println(isAfter); // true
	}




	private static void localTimeExample()
	{
		LocalTime localTime = LocalTime.now();
		System.out.println(localTime);

		LocalTime hour = LocalTime.of(6, 30);
		System.out.println(hour); // 06:30

		LocalTime localTimePlus = hour.plus(1, ChronoUnit.HOURS);
		System.out.println(localTimePlus); // 07:30

		LocalTime localTimeMinus = hour.minus(60, ChronoUnit.SECONDS);
		System.out.println(localTimeMinus); // 06:29

		boolean isBeforeHour = LocalTime.parse("08:30").isBefore(LocalTime.parse("10:20"));
		System.out.println(isBeforeHour); // true
	}




	private static void localDateTimeExample()
	{
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(localDateTime);

		LocalDateTime localDateTimeOf = LocalDateTime.of(2017, Month.AUGUST, 20, 8, 30);
		System.out.println(localDateTimeOf); // 2017-08-20T08:30

		LocalDateTime localDateTimePlus = localDateTimeOf.plusDays(5);
		System.out.println(localDateTimePlus); // 2017-08-25T08:30

		LocalDateTime localDateTimeMinus = localDateTimePlus.minusMinutes(10);
		System.out.println(localDateTimeMinus); // 2017-08-25T08:20
	}




	@SuppressWarnings( "unused" )
	private static void zonedDateTimeExample()
	{
		ZoneId.getAvailableZoneIds().forEach(z -> System.out.println(z)); // list of all zones

		ZoneId zoneId = ZoneId.of("America/Panama");
		LocalDateTime localDateTimeOf = LocalDateTime.of(2017, Month.AUGUST, 20, 8, 30);
		ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTimeOf, zoneId);
		System.out.println(zonedDateTime); // 2017-08-20T08:30-05:00[America/Panama]

		ZonedDateTime tokyoDateTime = localDateTimeOf.atZone(ZoneId.of("Asia/Tokyo"));
		System.out.println(tokyoDateTime); // 2017-08-20T08:30+09:00[Asia/Tokyo]
	}




	private static void periodExample()
	{
		LocalDate d3 = LocalDate.parse("2018-05-06", DateTimeFormatter.ISO_LOCAL_DATE);
		LocalDate d4 = LocalDate.parse("2020-01-23", DateTimeFormatter.ISO_LOCAL_DATE);

		LocalDate startLocalDate = LocalDate.of(2017, 10, 10);
		System.out.println(startLocalDate.toString());
		// LocalDate endLocalDate = startLocalDate.plus(Period.ofDays(10)); //
		// 2017-10-20
		LocalDate endLocalDateToday = LocalDate.now();
		System.out.println(endLocalDateToday.toString());
		// int diffDays = Period.between(startLocalDate,
		// endLocalDate).getDays();

		Duration diff = Duration.between(startLocalDate.atStartOfDay(), endLocalDateToday.atStartOfDay());
		Period period = Period.between(d3, d4);

		long diffDays_ = diff.toDays();
		int years = Math.abs(period.getYears());
		int months = Math.abs(period.getMonths());
		int days = Math.abs(period.getDays());
		System.out.println("Diffrence between dates is : " + diffDays_ + "days");
		System.out.println("Diffrence is : " + years + " year, " + months + " months, " + days + " days");

	}




	private static void durationExample()
	{
		LocalTime startLocalTime = LocalTime.of(8, 30);
		LocalTime endLocalTime = startLocalTime.plus(Duration.ofHours(3)); // 11:30

		long diffSeconds = Duration.between(startLocalTime, endLocalTime).getSeconds();
		System.out.println(diffSeconds); // 10800 seconds
	}

}

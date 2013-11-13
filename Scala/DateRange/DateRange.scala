package daterange

/**
 * Author: Adrian Fitzpatrick
 * Represents a range between two whole dates and defines methods on that range.
 * Object is initialized with string values in the format YYYY-MM-DD
 */
class DateRange(start:String, end:String) {

  private val format = new java.text.SimpleDateFormat("yyyy-MM-dd")
  private val start_millis = format.parse(start).getTime()
  private val end_millis = format.parse(end).getTime()

  require(start_millis < end_millis, "start is after end")
  
  /**
  * Returns days in the date range (inclusive)
  */
  val daysBetween = ((end_millis - start_millis) / (1000 * 60 * 60 * 24)).toInt + 1

  /**
  * Returns fractional months in the date range.  This is an approximation that assumes
  *  12 months per year of equal length
  */
  val monthsBetween = 
      new java.text.DecimalFormat("#.#").format(daysBetween.toFloat / (365 / 12)).toFloat
  
  /**
   * Returns true if the supplied date string is in the date range inclusive of start and end dates.
   */
  def inDateRange(date :String) = {
    val millis = format.parse(date).getTime()
    millis >= start_millis && millis <= end_millis
  }
  
}
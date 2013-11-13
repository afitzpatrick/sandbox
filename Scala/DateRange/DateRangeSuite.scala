package daterange

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * The scalatest/junit jars is not included in the project due to size limitations for emailing the project. 
 * The sbt build script will include these files during build phase. 
 */
@RunWith(classOf[JUnitRunner])
class DateRangeSuite extends FunSuite {

  test("start date afer end date"){
     intercept[IllegalArgumentException]{
       new DateRange("2010-10-03", "2010-09-01")
     }
  }
  
  test("invalid dates"){
    intercept[java.text.ParseException]{
      new DateRange("foo", "2010-09")
    }
  }
  
  test("calculate days between"){
    val dr = new DateRange("2010-09-01", "2010-09-15")
    assert(dr.daysBetween === 15)
  }
  
  test("calculate days between - truncate hours"){
    val dr = new DateRange("2010-09-01 18:00", "2010-09-15")
    assert(dr.daysBetween === 15)
  }
  
  test("calculate months between - one month"){
    val dr = new DateRange("2010-09-01", "2010-10-01")
    assert(dr.monthsBetween === 1)
  }
  
  test("calculate months between - one and a half months"){
    val dr = new DateRange("2010-09-01", "2010-10-15")
    assert(dr.monthsBetween === 1.5)
  }
  
  test("calculate months between - half month"){
    val dr = new DateRange("2010-09-01", "2010-09-15")
    assert(dr.monthsBetween === 0.5)
  }
  
  test("test in date range"){
     val dr = new DateRange("2010-09-01", "2010-09-15")
     assert(dr.inDateRange("2010-09-10"))
  }
  
  test("test not in date range"){
    val dr = new DateRange("2010-09-01", "2010-09-15")
    assert(!dr.inDateRange("2011-09-10"))
  }
}
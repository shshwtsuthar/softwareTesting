package sit707_tasks;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * SIT333 3.2C - Equivalence class testing using JUnit 4.
 *
 * Equivalence classes used:
 * Day:   D1 = 1-28, D2 = 29, D3 = 30, D4 = 31
 * Month: M1 = February, M2 = 30-day months, M3 = 31-day months
 * Year:  Y1 = leap year, Y2 = non-leap year
 */
public class DateUtilTest {

    @BeforeClass
    public static void printAssessmentDetails() {
        System.out.println("SIT333 Task 3.2C - Equivalence Class Testing using JUnit");
        System.out.println("Student Name: Shashwat Suthar");
        System.out.println("Student ID: S223938355");
    }

    /**
     * The original task contains two failing identity tests.
     * These are corrected using the student's actual name and ID.
     */
    @Test
    public void testStudentIdentity() {
        String studentId = "S223938355";
        System.out.println("Student ID: " + studentId);
        Assert.assertEquals("S223938355", studentId);
    }

    @Test
    public void testStudentName() {
        String studentName = "Shashwat Suthar";
        System.out.println("Student Name: " + studentName);
        Assert.assertEquals("Shashwat Suthar", studentName);
    }

    // Day equivalence class D1: day between 1 and 28
    @Test
    public void testIncrement_D1_NormalDay_WithinMonth() {
        DateUtil date = new DateUtil(15, 3, 2023);
        date.increment();

        Assert.assertEquals(16, date.getDay());
        Assert.assertEquals(3, date.getMonth());
        Assert.assertEquals(2023, date.getYear());
    }

    @Test
    public void testDecrement_D1_NormalDay_WithinMonth() {
        DateUtil date = new DateUtil(15, 3, 2023);
        date.decrement();

        Assert.assertEquals(14, date.getDay());
        Assert.assertEquals(3, date.getMonth());
        Assert.assertEquals(2023, date.getYear());
    }

    // Day equivalence class D2: day 29
    @Test
    public void testIncrement_D2_February29LeapYear_ToMarch1() {
        DateUtil date = new DateUtil(29, 2, 2024);
        date.increment();

        Assert.assertEquals(1, date.getDay());
        Assert.assertEquals(3, date.getMonth());
        Assert.assertEquals(2024, date.getYear());
    }

    @Test
    public void testDecrement_D2_February29LeapYear_ToFebruary28() {
        DateUtil date = new DateUtil(29, 2, 2024);
        date.decrement();

        Assert.assertEquals(28, date.getDay());
        Assert.assertEquals(2, date.getMonth());
        Assert.assertEquals(2024, date.getYear());
    }

    // Day equivalence class D3: day 30
    @Test
    public void testIncrement_D3_ThirtyDayMonth_ToNextMonth() {
        DateUtil date = new DateUtil(30, 4, 2023);
        date.increment();

        Assert.assertEquals(1, date.getDay());
        Assert.assertEquals(5, date.getMonth());
        Assert.assertEquals(2023, date.getYear());
    }

    @Test
    public void testDecrement_D3_ThirtyDayMonth_ToPreviousDay() {
        DateUtil date = new DateUtil(30, 4, 2023);
        date.decrement();

        Assert.assertEquals(29, date.getDay());
        Assert.assertEquals(4, date.getMonth());
        Assert.assertEquals(2023, date.getYear());
    }

    // Day equivalence class D4: day 31
    @Test
    public void testIncrement_D4_ThirtyOneDayMonth_ToNextMonth() {
        DateUtil date = new DateUtil(31, 3, 2023);
        date.increment();

        Assert.assertEquals(1, date.getDay());
        Assert.assertEquals(4, date.getMonth());
        Assert.assertEquals(2023, date.getYear());
    }

    @Test
    public void testDecrement_D4_ThirtyOneDayMonth_ToPreviousDay() {
        DateUtil date = new DateUtil(31, 3, 2023);
        date.decrement();

        Assert.assertEquals(30, date.getDay());
        Assert.assertEquals(3, date.getMonth());
        Assert.assertEquals(2023, date.getYear());
    }

    // Month equivalence class M1: February
    @Test
    public void testIncrement_FebruaryNonLeapYear_ToMarch1() {
        DateUtil date = new DateUtil(28, 2, 2023);
        date.increment();

        Assert.assertEquals(1, date.getDay());
        Assert.assertEquals(3, date.getMonth());
        Assert.assertEquals(2023, date.getYear());
    }

    @Test
    public void testDecrement_March1NonLeapYear_ToFebruary28() {
        DateUtil date = new DateUtil(1, 3, 2023);
        date.decrement();

        Assert.assertEquals(28, date.getDay());
        Assert.assertEquals(2, date.getMonth());
        Assert.assertEquals(2023, date.getYear());
    }

    @Test
    public void testDecrement_March1LeapYear_ToFebruary29() {
        DateUtil date = new DateUtil(1, 3, 2024);
        date.decrement();

        Assert.assertEquals(29, date.getDay());
        Assert.assertEquals(2, date.getMonth());
        Assert.assertEquals(2024, date.getYear());
    }

    // Month equivalence class M2: 30-day months
    @Test
    public void testIncrement_ThirtyDayMonth_November30ToDecember1() {
        DateUtil date = new DateUtil(30, 11, 2023);
        date.increment();

        Assert.assertEquals(1, date.getDay());
        Assert.assertEquals(12, date.getMonth());
        Assert.assertEquals(2023, date.getYear());
    }

    @Test
    public void testMonthDuration_ThirtyDayMonth() {
        Assert.assertEquals(30, DateUtil.monthDuration(4, 2023));
    }

    // Month equivalence class M3: 31-day months
    @Test
    public void testIncrement_ThirtyOneDayMonth_January31ToFebruary1() {
        DateUtil date = new DateUtil(31, 1, 2023);
        date.increment();

        Assert.assertEquals(1, date.getDay());
        Assert.assertEquals(2, date.getMonth());
        Assert.assertEquals(2023, date.getYear());
    }

    @Test
    public void testMonthDuration_ThirtyOneDayMonth() {
        Assert.assertEquals(31, DateUtil.monthDuration(1, 2023));
    }

    // Year equivalence classes Y1 and Y2
    @Test
    public void testMonthDuration_FebruaryLeapYear() {
        Assert.assertEquals(29, DateUtil.monthDuration(2, 2024));
    }

    @Test
    public void testMonthDuration_FebruaryNonLeapYear() {
        Assert.assertEquals(28, DateUtil.monthDuration(2, 2023));
    }

    @Test
    public void testIncrement_December31_ToJanuary1NextYear() {
        DateUtil date = new DateUtil(31, 12, 2023);
        date.increment();

        Assert.assertEquals(1, date.getDay());
        Assert.assertEquals(1, date.getMonth());
        Assert.assertEquals(2024, date.getYear());
    }

    @Test
    public void testDecrement_January1_ToDecember31PreviousYear() {
        DateUtil date = new DateUtil(1, 1, 2024);
        date.decrement();

        Assert.assertEquals(31, date.getDay());
        Assert.assertEquals(12, date.getMonth());
        Assert.assertEquals(2023, date.getYear());
    }

    // Invalid equivalence classes for input validation
    @Test
    public void testInvalidDayZero() {
        try {
            new DateUtil(0, 1, 2023);
            Assert.fail("Expected RuntimeException for invalid day 0");
        } catch (RuntimeException e) {
            Assert.assertTrue(e.getMessage().contains("Invalid day"));
        }
    }

    @Test
    public void testInvalidDayThirtyTwo() {
        try {
            new DateUtil(32, 1, 2023);
            Assert.fail("Expected RuntimeException for invalid day 32");
        } catch (RuntimeException e) {
            Assert.assertTrue(e.getMessage().contains("Invalid day"));
        }
    }

    @Test
    public void testInvalidMonthZero() {
        try {
            new DateUtil(1, 0, 2023);
            Assert.fail("Expected RuntimeException for invalid month 0");
        } catch (RuntimeException e) {
            Assert.assertTrue(e.getMessage().contains("Invalid month"));
        }
    }

    @Test
    public void testInvalidMonthThirteen() {
        try {
            new DateUtil(1, 13, 2023);
            Assert.fail("Expected RuntimeException for invalid month 13");
        } catch (RuntimeException e) {
            Assert.assertTrue(e.getMessage().contains("Invalid month"));
        }
    }

    @Test
    public void testInvalidYearBeforeRange() {
        try {
            new DateUtil(1, 1, 1699);
            Assert.fail("Expected RuntimeException for invalid year 1699");
        } catch (RuntimeException e) {
            Assert.assertTrue(e.getMessage().contains("Invalid year"));
        }
    }

    @Test
    public void testInvalidYearAfterRange() {
        try {
            new DateUtil(1, 1, 2025);
            Assert.fail("Expected RuntimeException for invalid year 2025");
        } catch (RuntimeException e) {
            Assert.assertTrue(e.getMessage().contains("Invalid year"));
        }
    }

    @Test
    public void testInvalidFebruary29NonLeapYear() {
        try {
            new DateUtil(29, 2, 2023);
            Assert.fail("Expected RuntimeException for invalid February 29 in a non-leap year");
        } catch (RuntimeException e) {
            Assert.assertTrue(e.getMessage().contains("Invalid day"));
        }
    }

    @Test
    public void testInvalidFebruary30LeapYear() {
        try {
            new DateUtil(30, 2, 2024);
            Assert.fail("Expected RuntimeException for invalid February 30");
        } catch (RuntimeException e) {
            Assert.assertTrue(e.getMessage().contains("Invalid day"));
        }
    }

    @Test
    public void testInvalidApril31() {
        try {
            new DateUtil(31, 4, 2023);
            Assert.fail("Expected RuntimeException for invalid April 31");
        } catch (RuntimeException e) {
            Assert.assertTrue(e.getMessage().contains("Invalid day"));
        }
    }
}
